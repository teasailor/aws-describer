package pikalova.aws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.CloudInstance;
import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.entity.CloudInstanceEntity;
import pikalova.aws.entity.SecurityGroupsEntity;
import pikalova.aws.mapping.CloudInstanceMapper;
import pikalova.aws.mapping.SecurityGroupMapper;
import pikalova.aws.repository.CloudInstanceRepository;
import pikalova.aws.repository.SecurityGroupRepository;

@Service
public class CloudInstanceService {
	private Ec2Client ec2Client;
	private CloudInstanceRepository cloudInstanceRepository;
	private CloudInstanceMapper cloudInstanceMapper;
	private SecurityGroupRepository securityGroupRepository;
	private SecurityGroupMapper securityGroupMapper;

	@Autowired
	public CloudInstanceService(Ec2Client ec2Client, CloudInstanceRepository cloudInstanceRepository, CloudInstanceMapper cloudInstanceMapper,
			SecurityGroupRepository securityGroupRepository, SecurityGroupMapper securityGroupMapper) {
		this.ec2Client = ec2Client;
		this.cloudInstanceRepository = cloudInstanceRepository;
		this.cloudInstanceMapper = cloudInstanceMapper;
		this.securityGroupRepository = securityGroupRepository;
		this.securityGroupMapper = securityGroupMapper;
	}

	public List<CloudInstance> collect() {
		return ec2Client.loadInstancesInfo();
	}

	@Transactional
	public List<CloudInstance> store(List<CloudInstance> cloudInstances) {
		List<SecurityGroup> securityGroups = ec2Client.loadSecurityGroups();
		List<CloudInstanceEntity> instanceEntities = cloudInstanceMapper.mapToEntities(cloudInstances);
		List<SecurityGroupsEntity> securityGroupEntities = storeSecurityGroups(securityGroupMapper.mapToEntities(securityGroups.stream().distinct().collect(Collectors.toList())));
		replaceSecurityGroups(instanceEntities, securityGroupEntities);
		return cloudInstanceMapper.map(cloudInstanceRepository.saveAll(instanceEntities));
	}

	private void replaceSecurityGroups(List<CloudInstanceEntity> cloudInstances, List<SecurityGroupsEntity> securityGroups) {
		cloudInstances.forEach(cloudInstance -> {
			List<SecurityGroupsEntity> instanceSecurityGroups = cloudInstance.getSecurityGroups();
			for (int i = 0; i < instanceSecurityGroups.size(); i++) {
				SecurityGroupsEntity securityGroup = instanceSecurityGroups.get(i);
				SecurityGroupsEntity describedGroup = securityGroups.stream()
						.filter(s -> s.getGroupId().equals(securityGroup.getGroupId()))
						.findAny().orElse(null);
				instanceSecurityGroups.set(i, describedGroup);
			}
		});
		System.out.println("cloudInstances = " + cloudInstances);
	}

	private List<SecurityGroupsEntity> storeSecurityGroups(List<SecurityGroupsEntity> securityGroupsEntities) {
		securityGroupRepository.findByGroupIdIn(securityGroupsEntities.stream().map(SecurityGroupsEntity::getGroupId).collect(Collectors.toList()));
		return securityGroupRepository.saveAll(securityGroupsEntities);
	}

}

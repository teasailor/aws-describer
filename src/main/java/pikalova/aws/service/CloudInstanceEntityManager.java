package pikalova.aws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.entity.CloudInstanceEntity;
import pikalova.aws.entity.SecurityGroupsEntity;
import pikalova.aws.repository.CloudInstanceRepository;

@Service
class CloudInstanceEntityManager {

	private final SecurityGroupEntityManager securityGroupEntityManager;
	private final CloudInstanceRepository cloudInstanceRepository;

	CloudInstanceEntityManager(SecurityGroupEntityManager securityGroupEntityManager, CloudInstanceRepository cloudInstanceRepository) {
		this.securityGroupEntityManager = securityGroupEntityManager;
		this.cloudInstanceRepository = cloudInstanceRepository;
	}

	List<CloudInstanceEntity> storeInstanceEntity(List<SecurityGroup> securityGroups, List<CloudInstanceEntity> instanceEntities) {
		List<SecurityGroupsEntity> securityGroupEntities = securityGroupEntityManager.storeSecurityGroups(securityGroups);
		replaceSecurityGroups(instanceEntities, securityGroupEntities);
		return cloudInstanceRepository.saveAll(instanceEntities);
	}

	private void replaceSecurityGroups(List<CloudInstanceEntity> cloudInstances, List<SecurityGroupsEntity> securityGroups) {
		cloudInstances.forEach(cloudInstance -> {
			List<SecurityGroupsEntity> instanceSecurityGroups = cloudInstance.getSecurityGroups();
			for (int i = 0; i < instanceSecurityGroups.size(); i++) {
				SecurityGroupsEntity securityGroup = instanceSecurityGroups.get(i);
				SecurityGroupsEntity describedGroup = securityGroups.stream()
						.filter(s -> s.getGroupId().equals(securityGroup.getGroupId()))
						.findFirst().orElse(null);
				instanceSecurityGroups.set(i, describedGroup);
			}
		});
		System.out.println("cloudInstances = " + cloudInstances);
	}
}

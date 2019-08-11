package pikalova.aws.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.mapping.SecurityGroupMapper;
import pikalova.aws.repository.SecurityGroupRepository;

@Service
public class SecurityGroupService {
	private Ec2Client ec2Client;
	private SecurityGroupRepository securityGroupRepository;
	private SecurityGroupMapper securityGroupMapper;

	public SecurityGroupService(Ec2Client ec2Client, SecurityGroupRepository securityGroupRepository, SecurityGroupMapper securityGroupMapper) {
		this.ec2Client = ec2Client;
		this.securityGroupRepository = securityGroupRepository;
		this.securityGroupMapper = securityGroupMapper;
	}

	@Transactional
	public List<SecurityGroup> collect() {
		List<SecurityGroup> cloudInstances = ec2Client.loadSecurityGroups();
		return securityGroupMapper.map(securityGroupRepository.saveAll(securityGroupMapper.mapToEntities(cloudInstances)));
	}
}

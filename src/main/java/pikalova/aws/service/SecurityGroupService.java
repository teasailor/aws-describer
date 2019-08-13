package pikalova.aws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.mapping.SecurityGroupMapper;

@Service
public class SecurityGroupService {
	private Ec2Client ec2Client;

	public SecurityGroupService(Ec2Client ec2Client, SecurityGroupMapper securityGroupMapper) {
		this.ec2Client = ec2Client;
	}

	public List<SecurityGroup> collect() {
		return ec2Client.loadSecurityGroups();
	}

}

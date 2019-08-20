package pikalova.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.CloudInstance;
import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.domain.Volume;

@RestController
@RequestMapping("testing/cloud")
public class CloudController {

	private Ec2Client ec2Client;

	@Autowired
	CloudController(Ec2Client ec2Client) {
		this.ec2Client = ec2Client;
	}

	@GetMapping("/instances")
	public List<CloudInstance> getInstances() {
		return ec2Client.loadInstancesInfo();
	}

	@GetMapping("/securityGroups")
	public List<SecurityGroup> getSecurityGroups() {
		return ec2Client.loadSecurityGroups();
	}

	@GetMapping("/volumes")
	public List<Volume> getVolumes() {
		return ec2Client.loadVolumes();
	}

}

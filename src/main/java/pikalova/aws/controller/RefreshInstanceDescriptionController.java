package pikalova.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pikalova.aws.domain.CloudInstance;
import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.service.CloudInstanceService;
import pikalova.aws.service.SecurityGroupService;

@RestController
@RequestMapping("cloud/refresh/")
public class RefreshInstanceDescriptionController {

	private CloudInstanceService cloudInstanceService;
	private SecurityGroupService securityGroupService;

	@Autowired
	RefreshInstanceDescriptionController(CloudInstanceService cloudInstanceService, SecurityGroupService securityGroupService) {
		this.cloudInstanceService = cloudInstanceService;
		this.securityGroupService = securityGroupService;
	}

	@GetMapping("instances")
	public List<CloudInstance> refreshInstances() {
		return cloudInstanceService.collect();
	}

	@GetMapping("securityGroup")
	public List<SecurityGroup> refreshSecurityGroups() {
		return securityGroupService.collect();
	}
}

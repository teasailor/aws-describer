package pikalova.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pikalova.aws.domain.CloudInstance;
import pikalova.aws.service.CloudInstanceService;

@RestController
@RequestMapping("refresh/cloud/")
public class RefreshInstanceDescriptionController {

	private CloudInstanceService cloudInstanceService;

	@Autowired
	RefreshInstanceDescriptionController(CloudInstanceService cloudInstanceService) {
		this.cloudInstanceService = cloudInstanceService;
	}

	@GetMapping("instances")
	public List<CloudInstance> refreshInstances() {
		return cloudInstanceService.store(cloudInstanceService.collect());
	}

}

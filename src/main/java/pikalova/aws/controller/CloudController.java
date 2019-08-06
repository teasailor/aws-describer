package pikalova.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;


public class CloudController {

	private AmazonEC2 amazonEc2Client;

	@Autowired
	CloudController(AmazonEC2 amazonEc2Client) {
		this.amazonEc2Client = amazonEc2Client;
	}

	@GetMapping("/instances")
	public DescribeInstancesResult getInstances() {
		return amazonEc2Client.describeInstances();
	}

	@GetMapping("/securityGroups")
	public DescribeSecurityGroupsResult getSecurityGroups() {
		return amazonEc2Client.describeSecurityGroups();
	}

	@GetMapping("/volumes")
	public DescribeVolumesResult getVolumes() {
		return amazonEc2Client.describeVolumes();
	}

}

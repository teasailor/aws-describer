package pikalova.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.ec2.AmazonEC2;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.CloudInstance;

@Service
public class CloudInstanceService {
	private Ec2Client ec2Client;

	@Autowired
	public CloudInstanceService(Ec2Client ec2Client) {
		this.ec2Client = ec2Client;
	}

	public List<CloudInstance> collect() {
		return ec2Client.loadInstancesInfo();

	}


}

package pikalova.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.CloudInstance;
import pikalova.aws.mapping.CloudInstanceMapper;
import pikalova.aws.repository.CloudInstanceRepository;

@Service
public class CloudInstanceService {
	private Ec2Client ec2Client;
	private CloudInstanceRepository cloudInstanceRepository;
	private CloudInstanceMapper cloudInstanceMapper;

	@Autowired
	public CloudInstanceService(Ec2Client ec2Client, CloudInstanceRepository cloudInstanceRepository, CloudInstanceMapper cloudInstanceMapper) {
		this.ec2Client = ec2Client;
		this.cloudInstanceRepository = cloudInstanceRepository;
		this.cloudInstanceMapper = cloudInstanceMapper;
	}

	@Transactional
	public List<CloudInstance> collect() {
		List<CloudInstance> cloudInstances = ec2Client.loadInstancesInfo();
		return cloudInstanceMapper.map(cloudInstanceRepository.saveAll(cloudInstanceMapper.mapToEntities(cloudInstances)));
	}


}

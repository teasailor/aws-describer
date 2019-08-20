package pikalova.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.CloudInstance;
import pikalova.aws.entity.CloudInstanceEntity;
import pikalova.aws.entity.manager.CloudInstanceEntityManager;
import pikalova.aws.mapping.CloudInstanceMapper;

@Service
public class CloudInstanceService {

	private Ec2Client ec2Client;
	private CloudInstanceMapper cloudInstanceMapper;
	private CloudInstanceEntityManager cloudInstanceEntityManager;

	@Autowired
	public CloudInstanceService(Ec2Client ec2Client, CloudInstanceMapper cloudInstanceMapper,
			CloudInstanceEntityManager cloudInstanceEntityManager) {
		this.ec2Client = ec2Client;
		this.cloudInstanceMapper = cloudInstanceMapper;
		this.cloudInstanceEntityManager = cloudInstanceEntityManager;
	}

	@Transactional
	public List<CloudInstance> store(List<CloudInstance> cloudInstances) {
		List<CloudInstanceEntity> instanceEntities = cloudInstanceMapper.mapToEntities(cloudInstances);
		return cloudInstanceMapper.map(cloudInstanceEntityManager.storeInstanceEntity(instanceEntities, ec2Client.loadSecurityGroups(), ec2Client.loadVolumes()));
	}

	// TODO: performance question
	@Transactional
	public List<CloudInstance> refresh() {
		cloudInstanceEntityManager.clean();
		return store(ec2Client.loadInstancesInfo());
	}
}

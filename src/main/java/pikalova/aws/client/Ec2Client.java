package pikalova.aws.client;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Reservation;

import pikalova.aws.domain.CloudInstance;
import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.domain.Volume;
import pikalova.aws.mapping.CloudInstanceMapper;
import pikalova.aws.mapping.SecurityGroupMapper;
import pikalova.aws.mapping.VolumeMapper;

public class Ec2Client {

	private AmazonEC2 amazonEC2;

	@Autowired
	private CloudInstanceMapper cloudInstanceMapper;
	@Autowired
	private SecurityGroupMapper securityGroupMapper;
	@Autowired
	private VolumeMapper volumeMapper;

	Ec2Client(AmazonEC2 amazonEC2) {
		this.amazonEC2 = amazonEC2;
	}

	public List<CloudInstance> loadInstancesInfo() {
		return amazonEC2.describeInstances().getReservations().stream()
				.map(Reservation::getInstances)
				.flatMap(instances -> instances.stream())
				.map(cloudInstanceMapper::mapToDomain)
				.collect(toList());
	}

	public List<SecurityGroup> loadSecurityGroups() {
		return securityGroupMapper.mapToDomain(amazonEC2.describeSecurityGroups().getSecurityGroups());
	}

	public List<Volume> loadVolumes() {
		return volumeMapper.mapToDomain(amazonEC2.describeVolumes().getVolumes());
	}
}

package pikalova.aws.client;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Reservation;

import pikalova.aws.domain.CloudInstance;
import pikalova.aws.mapping.CloudInstanceMapper;

public class Ec2Client {

	private AmazonEC2 amazonEC2;

	@Autowired
	private CloudInstanceMapper cloudInstanceMapper;

	Ec2Client(AmazonEC2 amazonEC2) {
		this.amazonEC2 = amazonEC2;
	}

	public List<CloudInstance> loadInstancesInfo() {
		return amazonEC2.describeInstances().getReservations().stream()
				.map(Reservation::getInstances)
				.flatMap(instances -> instances.stream())
				.map(cloudInstanceMapper::map)
				.collect(toList());
	}

	public DescribeSecurityGroupsResult loadSecurityGroups() {
		return amazonEC2.describeSecurityGroups();
	}

	public DescribeVolumesResult loadVolumes() {
		return amazonEC2.describeVolumes();
	}
}

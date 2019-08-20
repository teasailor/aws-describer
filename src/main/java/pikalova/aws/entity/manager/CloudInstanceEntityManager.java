package pikalova.aws.entity.manager;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.domain.Volume;
import pikalova.aws.entity.CloudInstanceEntity;
import pikalova.aws.entity.SecurityGroupsEntity;
import pikalova.aws.entity.VolumeEntity;
import pikalova.aws.repository.CloudInstanceRepository;

@Slf4j
@Component
public class CloudInstanceEntityManager {

	private final CloudInstanceRepository cloudInstanceRepository;
	private final SecurityGroupEntityManager securityGroupEntityManager;
	private final VolumeEntityManager volumeEntityManager;

	CloudInstanceEntityManager(SecurityGroupEntityManager securityGroupEntityManager, CloudInstanceRepository cloudInstanceRepository,
			VolumeEntityManager volumeEntityManager) {
		this.securityGroupEntityManager = securityGroupEntityManager;
		this.cloudInstanceRepository = cloudInstanceRepository;
		this.volumeEntityManager = volumeEntityManager;
	}

	// TODO: lock before update
	public List<CloudInstanceEntity> storeInstanceEntity(List<CloudInstanceEntity> instanceEntities, List<SecurityGroup> securityGroups, List<Volume> volumes) {
		if (!isEmpty(securityGroups)) {
			List<SecurityGroupsEntity> securityGroupEntities = securityGroupEntityManager.store(securityGroups);
			replaceSecurityGroups(instanceEntities, securityGroupEntities);
		}
		if (!isEmpty(volumes)) {
			List<VolumeEntity> volumeEntities = volumeEntityManager.store(volumes);
			replaceVolumes(instanceEntities, volumeEntities);
		}
		return cloudInstanceRepository.saveAll(instanceEntities);
	}

	private void replaceVolumes(List<CloudInstanceEntity> cloudInstances, List<VolumeEntity> volumes) {
		cloudInstances.forEach(cloudInstance -> {
			List<VolumeEntity> instanceVolumes = cloudInstance.getVolumes();
			if (isEmpty(instanceVolumes)) {
				return;
			}
			for (int i = 0; i < instanceVolumes.size(); i++) {
				VolumeEntity volume = instanceVolumes.get(i);
				VolumeEntity describedVolume = volumes.stream()
						.filter(v -> v.getVolumeId().equals(volume.getVolumeId()))
						.findFirst()
						.orElse(null);
				instanceVolumes.set(i, describedVolume);
			}
		});
	}

	private void replaceSecurityGroups(List<CloudInstanceEntity> cloudInstances, List<SecurityGroupsEntity> securityGroups) {
		cloudInstances.forEach(cloudInstance -> {
			List<SecurityGroupsEntity> instanceSecurityGroups = cloudInstance.getSecurityGroups();
			if (isEmpty(instanceSecurityGroups)) {
				return;
			}
			for (int i = 0; i < instanceSecurityGroups.size(); i++) {
				SecurityGroupsEntity securityGroup = instanceSecurityGroups.get(i);
				SecurityGroupsEntity describedGroup = securityGroups.stream()
						.filter(s -> s.getGroupId().equals(securityGroup.getGroupId()))
						.findFirst()
						.orElse(null);
				instanceSecurityGroups.set(i, describedGroup);
			}
		});
	}

	public void clean() {
		cloudInstanceRepository.deleteAll();
	}
}

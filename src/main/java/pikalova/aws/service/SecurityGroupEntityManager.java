package pikalova.aws.service;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.entity.SecurityGroupsEntity;
import pikalova.aws.mapping.SecurityGroupMapper;
import pikalova.aws.repository.SecurityGroupRepository;

@Service
class SecurityGroupEntityManager {
	private SecurityGroupRepository securityGroupRepository;
	private SecurityGroupMapper securityGroupMapper;

	SecurityGroupEntityManager(SecurityGroupRepository securityGroupRepository, SecurityGroupMapper securityGroupMapper) {
		this.securityGroupRepository = securityGroupRepository;
		this.securityGroupMapper = securityGroupMapper;
	}

	List<SecurityGroupsEntity> storeSecurityGroups(List<SecurityGroup> securityGroups) {
		List<SecurityGroupsEntity> freshGroups = securityGroupMapper.mapToEntities(securityGroups.stream().distinct().collect(Collectors.toList()));
		List<SecurityGroupsEntity> groupsToUpdate = securityGroupRepository.findByGroupIdIn(freshGroups.stream().map(SecurityGroupsEntity::getGroupId).collect(Collectors.toList()));
		mergeExistingAndFreshData(freshGroups, groupsToUpdate);
		return securityGroupRepository.saveAll(freshGroups);
	}

	private void mergeExistingAndFreshData(List<SecurityGroupsEntity> freshGroups, List<SecurityGroupsEntity> groupsToUpdate) {
		if (isEmpty(groupsToUpdate)) {
			return;
		}
		for (int i = 0; i < freshGroups.size(); i++) {
			SecurityGroupsEntity freshGroup = freshGroups.get(i);
			SecurityGroupsEntity groupToUpdate = groupsToUpdate.stream()
					.filter(existedGroup -> existedGroup.getGroupId().equals(freshGroup.getGroupId()))
					.findFirst().orElse(null);
			if (groupToUpdate != null) {
				freshGroups.set(i, updateExistingEntity(freshGroup, groupToUpdate));
			}
		}
	}

	private SecurityGroupsEntity updateExistingEntity(SecurityGroupsEntity freshGroup, SecurityGroupsEntity groupToUpdate) {
		freshGroup.setId(groupToUpdate.getId());
		return freshGroup;
	}
}

package pikalova.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pikalova.aws.entity.SecurityGroupsEntity;

public interface SecurityGroupRepository extends JpaRepository<SecurityGroupsEntity, Long> {
	void findByGroupIdIn(List<String> groupIds);
}

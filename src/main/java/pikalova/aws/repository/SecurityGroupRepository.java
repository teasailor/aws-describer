package pikalova.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pikalova.aws.entity.SecurityGroupsEntity;

public interface SecurityGroupRepository extends JpaRepository<SecurityGroupsEntity, Long> {
}

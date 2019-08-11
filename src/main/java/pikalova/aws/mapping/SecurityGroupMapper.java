package pikalova.aws.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import pikalova.aws.domain.SecurityGroup;
import pikalova.aws.entity.SecurityGroupsEntity;

@Mapper(uses = SecurityGroupPermissionMapper.class)
public interface SecurityGroupMapper {

	List<SecurityGroup> mapToDomain(List<com.amazonaws.services.ec2.model.SecurityGroup> securityGroups);

	SecurityGroup mapToDomain(com.amazonaws.services.ec2.model.SecurityGroup securityGroups);

	List<SecurityGroupsEntity> mapToEntities(List<SecurityGroup> securityGroup);

	SecurityGroupsEntity mapToEntity(SecurityGroup securityGroup);

	List<SecurityGroup> map(List<SecurityGroupsEntity> securityGroupsEntity);

	SecurityGroup map(SecurityGroupsEntity securityGroupsEntity);
}

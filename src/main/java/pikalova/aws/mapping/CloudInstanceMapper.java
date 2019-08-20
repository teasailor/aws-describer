package pikalova.aws.mapping;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amazonaws.services.ec2.model.Instance;

import pikalova.aws.domain.CloudInstance;
import pikalova.aws.entity.CloudInstanceEntity;

@Mapper(uses = { VolumeMapper.class, SecurityGroupMapper.class })
public interface CloudInstanceMapper {

	@Mapping(source = "monitoring.state", target = "monitoringState")
	@Mapping(source = "blockDeviceMappings", target = "volumes")
	CloudInstance mapToDomain(Instance instances);

	List<CloudInstanceEntity> mapToEntities(List<CloudInstance> cloudInstances);

	@Mapping(source = "state.name", target = "instanceState")
	CloudInstanceEntity map(CloudInstance cloudInstance);

	List<CloudInstance> map(List<CloudInstanceEntity> cloudInstances);

	@InheritInverseConfiguration
	CloudInstance map(CloudInstanceEntity cloudInstance);

}

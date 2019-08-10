package pikalova.aws.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amazonaws.services.ec2.model.Instance;

import pikalova.aws.domain.CloudInstance;

@Mapper
public interface CloudInstanceMapper {

	@Mapping(source = "monitoring.state", target = "monitoringState")
	CloudInstance map(Instance instances);
}

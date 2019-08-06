package pikalova.aws.mapping;

import org.mapstruct.Mapper;

import com.amazonaws.services.ec2.model.Instance;

import pikalova.aws.domain.CloudInstance;

@Mapper
public interface CloudInstanceMapper {

	CloudInstance map(Instance instances);
}

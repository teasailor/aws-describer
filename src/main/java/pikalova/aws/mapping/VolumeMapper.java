package pikalova.aws.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amazonaws.services.ec2.model.InstanceBlockDeviceMapping;

import pikalova.aws.domain.Volume;
import pikalova.aws.entity.VolumeEntity;

@Mapper
public interface VolumeMapper {

	List<Volume> mapBlockDevices(List<InstanceBlockDeviceMapping> instanceBlockDeviceMappings);

	@Mapping(source = "ebs.volumeId", target = "volumeId")
	Volume mapBlockDevice(InstanceBlockDeviceMapping instanceBlockDeviceMapping);

	List<Volume> mapToDomain(List<com.amazonaws.services.ec2.model.Volume> describedVolumes);

	Volume mapToDomain(com.amazonaws.services.ec2.model.Volume describedVolume);

	List<VolumeEntity> mapToEntities(List<Volume> volumes);

	VolumeEntity mapToEntities(Volume volume);
}

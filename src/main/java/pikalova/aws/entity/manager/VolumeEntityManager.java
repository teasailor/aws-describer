package pikalova.aws.entity.manager;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import pikalova.aws.domain.Volume;
import pikalova.aws.entity.VolumeEntity;
import pikalova.aws.mapping.VolumeMapper;
import pikalova.aws.repository.VolumeRepository;

@Slf4j
@Component
class VolumeEntityManager {
	private final VolumeRepository volumeRepository;
	private final VolumeMapper volumeMapper;

	VolumeEntityManager(VolumeRepository volumeRepository, VolumeMapper volumeMapper) {
		this.volumeRepository = volumeRepository;
		this.volumeMapper = volumeMapper;
	}

	List<VolumeEntity> store(List<Volume> volumes) {
		List<VolumeEntity> freshVolumes = volumeMapper.mapToEntities(volumes.stream().distinct().collect(Collectors.toList()));
		List<VolumeEntity> volumesToUpdate = volumeRepository.findByVolumeIdIn(freshVolumes.stream().map(VolumeEntity::getVolumeId).collect(Collectors.toList()));
		mergeExistingAndFreshData(freshVolumes, volumesToUpdate);
		return volumeRepository.saveAll(freshVolumes);
	}

	private void mergeExistingAndFreshData(List<VolumeEntity> freshVolumes, List<VolumeEntity> volumesToUpdate) {
		if (isEmpty(volumesToUpdate)) {
			return;
		}
		for (int i = 0; i < freshVolumes.size(); i++) {
			VolumeEntity freshVolume = freshVolumes.get(i);
			VolumeEntity volumeToUpdate = volumesToUpdate.stream()
					.filter(existedVolume -> existedVolume.getVolumeId().equals(freshVolume.getVolumeId()))
					.findFirst()
					.orElse(null);
			if (volumeToUpdate != null) {
				freshVolumes.set(i, updateExistingEntity(freshVolume, volumeToUpdate));
				log.debug("The volume with id {} will be refreshed.", freshVolume.getVolumeId());
			}
		}
	}

	private VolumeEntity updateExistingEntity(VolumeEntity freshVolume, VolumeEntity volumeToUpdate) {
		freshVolume.setId(volumeToUpdate.getId());
		return freshVolume;
	}
}

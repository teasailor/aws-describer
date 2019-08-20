package pikalova.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pikalova.aws.entity.VolumeEntity;

public interface VolumeRepository extends JpaRepository<VolumeEntity, Long> {

	List<VolumeEntity> findByVolumeIdIn(List<String> collect);

}

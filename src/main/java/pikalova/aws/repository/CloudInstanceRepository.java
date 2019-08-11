package pikalova.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pikalova.aws.entity.CloudInstanceEntity;

public interface CloudInstanceRepository extends JpaRepository<CloudInstanceEntity, Long> {

}

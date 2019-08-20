package pikalova.aws.scheduler;

import static java.time.LocalDateTime.now;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pikalova.aws.service.CloudInstanceService;

@Slf4j
@Component
public class UpdateInstanceDescriptionJob {

	private final CloudInstanceService cloudInstanceService;

	public UpdateInstanceDescriptionJob(CloudInstanceService cloudInstanceService) {
		this.cloudInstanceService = cloudInstanceService;
	}

	@Scheduled(cron = "${pikalova.aws.scheduler.update.instance.description.job}")
	public void run() {
		log.info("Scheduled job {} is started at {}", UpdateInstanceDescriptionJob.class, now());
		cloudInstanceService.refresh();
		log.debug("Scheduled job {} is finished at {}", UpdateInstanceDescriptionJob.class, now());
	}
}

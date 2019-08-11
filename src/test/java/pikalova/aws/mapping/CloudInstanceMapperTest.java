package pikalova.aws.mapping;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.amazonaws.services.ec2.model.GroupIdentifier;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Monitoring;

import pikalova.aws.domain.CloudInstance;
import pikalova.aws.domain.InstanceState;
import pikalova.aws.entity.CloudInstanceEntity;

public class CloudInstanceMapperTest {

	private CloudInstanceMapper tested = Mappers.getMapper(CloudInstanceMapper.class);

	@Test
	public void testMapMonitoringStateToDomain() {
		final String state = "Good test";

		CloudInstance result = tested.map(new Instance().withMonitoring(new Monitoring().withState(state)));

		assertThat(result.getMonitoringState(), is(state));
	}

	@Test
	public void testMapSecurityGroupsToDomain() {
		final String groupId = "Good test";

		CloudInstance result = tested.map(new Instance().withSecurityGroups(new GroupIdentifier().withGroupId(groupId)));

		assertThat(result.getSecurityGroups().get(0).getGroupId(), is(groupId));
	}

	@Test
	public void testMapInstanceStateToEntity() {
		final String state = "Good test";
		final ArrayList<CloudInstance> cloudInstances = new ArrayList<>();
		cloudInstances.add(CloudInstance.builder().state(InstanceState.builder().name(state).build()).build());

		List<CloudInstanceEntity> result = tested.mapToEntities(cloudInstances);

		assertThat(result.get(0).getInstanceState(), is(state));
	}

}
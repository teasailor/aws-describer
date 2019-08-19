package pikalova.aws.controller;

import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pikalova.aws.AwsClientApplication;
import pikalova.aws.client.Ec2Client;
import pikalova.aws.domain.CloudInstance;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = AwsClientApplication.class)
@AutoConfigureMockMvc
public class RefreshInstanceDescriptionControllerIntegrationTest {

	private static final String TESTED_URL = "/refresh/cloud/instances";

	@Mock
	private Ec2Client ec2Client;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception {
		given(ec2Client.loadSecurityGroups()).willReturn(emptyList());
		List<CloudInstance> instances = new ArrayList<>();
		instances.add(CloudInstance.builder()
				.instanceId("id")
				.keyName("key name").build());
		given(ec2Client.loadInstancesInfo()).willReturn(instances);

		mockMvc.perform(get(TESTED_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
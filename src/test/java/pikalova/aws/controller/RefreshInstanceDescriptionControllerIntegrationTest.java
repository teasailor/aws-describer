package pikalova.aws.controller;

import static org.hamcrest.Matchers.contains;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pikalova.aws.AwsClientApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = MOCK,
		classes = AwsClientApplication.class)
@AutoConfigureMockMvc
public class RefreshInstanceDescriptionControllerIntegrationTest {

	private static final String TESTED_URL = "/refresh/cloud/instances";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception {
		mockMvc.perform(get(TESTED_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[*].instanceId",
						contains("i-096674e61cc3e4372", "i-0bff7ca13b72a25e7", "i-0a4f0f23503d0dd5a", "i-08a62d6a0ffeba432")));
	}

}
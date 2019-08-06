package pikalova.aws.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

@Configuration
public class AmazonClientConfiguration {

	@Bean
	public Ec2Client aAmazonEC2Client() {
		return new Ec2Client(AmazonEC2ClientBuilder.standard()
				.withRegion(Regions.US_EAST_1)
				.withCredentials(new ClasspathPropertiesFileCredentialsProvider())
				.build());
	}
}

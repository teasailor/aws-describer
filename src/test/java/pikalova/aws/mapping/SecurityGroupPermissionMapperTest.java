package pikalova.aws.mapping;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.IpRange;
import com.amazonaws.services.ec2.model.Ipv6Range;

public class SecurityGroupPermissionMapperTest {

	private SecurityGroupPermissionMapper tested = Mappers.getMapper(SecurityGroupPermissionMapper.class);

	@Test
	public void mapCidrIpv4ToDomain() {
		final ArrayList<IpPermission> ipPermissions = new ArrayList<>();
		final String cidrIp = "goo test ";
		ipPermissions.add(new IpPermission().withIpv4Ranges(new IpRange().withCidrIp(cidrIp)));

		List<pikalova.aws.domain.SecurityGroupPermission> result = tested.mapToDomain(ipPermissions);

		assertThat(result.get(0).getIpv4Ranges().get(0), is(cidrIp));
	}

	@Test
	public void mapCidrIpv6ToDomain() {
		final ArrayList<IpPermission> ipPermissions = new ArrayList<>();
		final String cidrIp = "goo test ";
		ipPermissions.add(new IpPermission().withIpv6Ranges(new Ipv6Range().withCidrIpv6(cidrIp)));

		List<pikalova.aws.domain.SecurityGroupPermission> result = tested.mapToDomain(ipPermissions);

		assertThat(result.get(0).getIpv6Ranges().get(0), is(cidrIp));
	}
}
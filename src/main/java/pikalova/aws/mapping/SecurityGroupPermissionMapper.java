package pikalova.aws.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import com.amazonaws.services.ec2.model.IpRange;
import com.amazonaws.services.ec2.model.Ipv6Range;

import pikalova.aws.domain.SecurityGroupPermission;

@Mapper
public abstract class SecurityGroupPermissionMapper {

	public abstract List<SecurityGroupPermission> mapToDomain(List<com.amazonaws.services.ec2.model.IpPermission> ipPermission);

	public abstract SecurityGroupPermission mapToDomain(com.amazonaws.services.ec2.model.IpPermission ipPermission);

	public abstract List<String> mapIpv4Range(com.amazonaws.internal.SdkInternalList<IpRange> ipRages);

	public String map(IpRange ipRages) {
		return ipRages.getCidrIp();
	}

	public abstract List<String> mapIpv6Range(com.amazonaws.internal.SdkInternalList<Ipv6Range> ipRages);

	public String map(Ipv6Range ipRages) {
		return ipRages.getCidrIpv6();
	}

}
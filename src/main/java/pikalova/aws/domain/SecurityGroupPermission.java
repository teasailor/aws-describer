package pikalova.aws.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SecurityGroupPermission {

	/**
	 * <p>
	 * The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of <code>-1</code>
	 * indicates all ICMP/ICMPv6 types.
	 * </p>
	 */
	private Integer fromPort;
	/**
	 * <p>
	 * The IP protocol name (<code>tcp</code>, <code>udp</code>, <code>icmp</code>) or number (see <a
	 * href="http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml">Protocol Numbers</a>).
	 * </p>
	 * <p>
	 * [EC2-VPC only] Use <code>-1</code> to specify all protocols. When authorizing security group rules, specifying
	 * <code>-1</code> or a protocol number other than <code>tcp</code>, <code>udp</code>, <code>icmp</code>, or
	 * <code>58</code> (ICMPv6) allows traffic on all ports, regardless of any port range you specify. For
	 * <code>tcp</code>, <code>udp</code>, and <code>icmp</code>, you must specify a port range. For <code>58</code>
	 * (ICMPv6), you can optionally specify a port range; if you don't, traffic for all types and codes is allowed when
	 * authorizing rules.
	 * </p>
	 */
	private String ipProtocol;
	/**
	 * <p>
	 * (Valid for <a>AuthorizeSecurityGroupEgress</a>, <a>RevokeSecurityGroupEgress</a> and
	 * <a>DescribeSecurityGroups</a> only) One or more prefix list IDs for an AWS service. In an
	 * <a>AuthorizeSecurityGroupEgress</a> request, this is the AWS service that you want to access through a VPC
	 * endpoint from instances associated with the security group.
	 * </p>
	 */
	// TODO: private com.amazonaws.internal.SdkInternalList<PrefixListId> prefixListIds;
	/**
	 * <p>
	 * The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of <code>-1</code> indicates
	 * all ICMP/ICMPv6 codes for the specified ICMP type.
	 * </p>
	 */
	private Integer toPort;
	/**
	 * <p>
	 * One or more security group and AWS account ID pairs.
	 * </p>
	 */
	// TODO: private com.amazonaws.internal.SdkInternalList<UserIdGroupPair> userIdGroupPairs;
	/**
	 * <p>
	 * One or more IPv4 ranges.
	 * </p>
	 */
	private List<String> ipv4Ranges;
	/**
	 * <p>
	 * [EC2-VPC only] One or more IPv6 ranges.
	 * </p>
	 */
	public List<String> ipv6Ranges;

}

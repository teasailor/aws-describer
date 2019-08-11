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
public class SecurityGroup {

	/**
	 * <p>
	 * A description of the security group.
	 * </p>
	 */
	private String description;
	/**
	 * <p>
	 * The name of the security group.
	 * </p>
	 */
	private String groupName;
	/**
	 * <p>
	 * One or more inbound rules associated with the security group.
	 * </p>
	 */
	private List<SecurityGroupPermission> ipPermissions;
	/**
	 * <p>
	 * [EC2-VPC] One or more outbound rules associated with the security group.
	 * </p>
	 */
	private List<SecurityGroupPermission> ipPermissionsEgress;
	/**
	 * <p>
	 * The AWS account ID of the owner of the security group.
	 * </p>
	 */
	private String ownerId;
	/**
	 * <p>
	 * The ID of the security group.
	 * </p>
	 */
	private String groupId;
	/**
	 * <p>
	 * [EC2-VPC] The ID of the VPC for the security group.
	 * </p>
	 */
	private String vpcId;

}

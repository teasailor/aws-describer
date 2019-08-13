package pikalova.aws.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
@Table(name = "SECURITY_GROUP")
public class SecurityGroupsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//	@ManyToMany(fetch = FetchType.LAZY,
	//			cascade = {
	//					CascadeType.PERSIST,
	//					CascadeType.MERGE
	//			},
	//			mappedBy = "securityGroups")
	//	private List<CloudInstanceEntity> instances;

	/**
	 * <p>
	 * The AWS business ID of the security group.
	 * </p>
	 */
	@Column(unique = true, nullable = false)
	private String groupId;
	/**
	 * <p>
	 * The name of the security group.
	 * </p>
	 */
	private String groupName;
	/**
	 * <p>
	 * A description of the security group.
	 * </p>
	 */
	private String description;
	/**
	 * <p>
	 * One or more inbound rules associated with the security group.
	 * </p>
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SecurityGroupPermissionEntity> ipPermissions;
	/**
	 * <p>
	 * [EC2-VPC] One or more outbound rules associated with the security group.
	 * </p>
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SecurityGroupPermissionEntity> ipPermissionsEgress;
	/**
	 * <p>
	 * The AWS account ID of the owner of the security group.
	 * </p>
	 */
	private String ownerId;
	/**
	 * <p>
	 * [EC2-VPC] The ID of the VPC for the security group.
	 * </p>
	 */
	private String vpcId;

}

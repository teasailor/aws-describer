package pikalova.aws.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
@Table(name = "INSTANCE")
public class CloudInstanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * <p>
	 * The current state of the instance.
	 * </p>
	 */
	private String instanceState;

	/**
	 * <p>
	 * The monitoring state for the instance.
	 * </p>
	 */
	private String monitoringState;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "INSTANCE_SECURITY_GROUPS",
			joinColumns = { @JoinColumn(name = "INSTANCE_ID") },
			inverseJoinColumns = { @JoinColumn(name = "SECURITY_GROUP_ID") })
	private List<SecurityGroupsEntity> securityGroups = new ArrayList<>();

	/**
	 * <p>
	 * Any block device mapping entries for the instance.
	 * </p>
	 */
	//TODO: private com.amazonaws.internal.SdkInternalList<InstanceBlockDeviceMapping> blockDeviceMappings;

	/**
	 * <p>
	 * The AMI launch index, which can be used to find this instance in the launch group.
	 * </p>
	 */
	private Integer amiLaunchIndex;
	/**
	 * <p>
	 * The ID of the AMI used to launch the instance.
	 * </p>
	 */
	private String imageId;
	/**
	 * <p>
	 * The ID of the instance.
	 * </p>
	 */
	private String instanceId;
	/**
	 * <p>
	 * The instance type.
	 * </p>
	 */
	private String instanceType;
	/**
	 * <p>
	 * The kernel associated with this instance, if applicable.
	 * </p>
	 */
	private String kernelId;
	/**
	 * <p>
	 * The name of the key pair, if this instance was launched with an associated key pair.
	 * </p>
	 */
	private String keyName;
	/**
	 * <p>
	 * The time the instance was launched.
	 * </p>
	 */
	private java.util.Date launchTime;
	/**
	 * <p>
	 * The value is <code>Windows</code> for Windows instances; otherwise blank.
	 * </p>
	 */
	private String platform;
	/**
	 * <p>
	 * (IPv4 only) The private DNS hostname name assigned to the instance. This DNS hostname can only be used inside the
	 * Amazon EC2 network. This name is not available until the instance enters the <code>running</code> state.
	 * </p>
	 * <p>
	 * [EC2-VPC] The Amazon-provided DNS server will resolve Amazon-provided private DNS hostnames if you've enabled DNS
	 * resolution and DNS hostnames in your VPC. If you are not using the Amazon-provided DNS server in your VPC, your
	 * custom domain name servers must resolve the hostname as appropriate.
	 * </p>
	 */
	private String privateDnsName;
	/**
	 * <p>
	 * The private IPv4 address assigned to the instance.
	 * </p>
	 */
	private String privateIpAddress;
	/**
	 * <p>
	 * (IPv4 only) The public DNS name assigned to the instance. This name is not available until the instance enters
	 * the <code>running</code> state. For EC2-VPC, this name is only available if you've enabled DNS hostnames for your
	 * VPC.
	 * </p>
	 */
	private String publicDnsName;
	/**
	 * <p>
	 * The public IPv4 address assigned to the instance, if applicable.
	 * </p>
	 */
	private String publicIpAddress;
	/**
	 * <p>
	 * The RAM disk associated with this instance, if applicable.
	 * </p>
	 */
	private String ramdiskId;
	/**
	 * <p>
	 * The reason for the most recent state transition. This might be an empty string.
	 * </p>
	 */
	private String stateTransitionReason;
	/**
	 * <p>
	 * [EC2-VPC] The ID of the subnet in which the instance is running.
	 * </p>
	 */
	private String subnetId;
	/**
	 * <p>
	 * [EC2-VPC] The ID of the VPC in which the instance is running.
	 * </p>
	 */
	private String vpcId;
	/**
	 * <p>
	 * The architecture of the image.
	 * </p>
	 */
	private String architecture;
	/**
	 * <p>
	 * Indicates whether the instance is optimized for EBS I/O. This optimization provides dedicated throughput to
	 * Amazon EBS and an optimized configuration stack to provide optimal I/O performance. This optimization isn't
	 * available with all instance types. Additional usage charges apply when using an EBS Optimized instance.
	 * </p>
	 */
	private Boolean ebsOptimized;
	/**
	 * <p>
	 * Specifies whether enhanced networking with ENA is enabled.
	 * </p>
	 */
	private Boolean enaSupport;
	/**
	 * <p>
	 * The hypervisor type of the instance.
	 * </p>
	 */
	private String hypervisor;

	/**
	 * <p>
	 * The root device name (for example, <code>/dev/sda1</code> or <code>/dev/xvda</code>).
	 * </p>
	 */
	private String rootDeviceName;
	/**
	 * <p>
	 * The root device type used by the AMI. The AMI can use an EBS volume or an instance store volume.
	 * </p>
	 */
	private String rootDeviceType;
	/**
	 * <p>
	 * Specifies whether to enable an instance launched in a VPC to perform NAT. This controls whether
	 * source/destination checking is enabled on the instance. A value of <code>true</code> means checking is enabled,
	 * and <code>false</code> means checking is disabled. The value must be <code>false</code> for the instance to
	 * perform NAT. For more information, see <a
	 * href="http://docs.aws.amazon.com/AmazonVPC/latest/UserGuide/VPC_NAT_Instance.html">NAT Instances</a> in the
	 * <i>Amazon Virtual Private Cloud User Guide</i>.
	 * </p>
	 */
	private Boolean sourceDestCheck;
	/**
	 * <p>
	 * The virtualization type of the instance.
	 * </p>
	 */
	private String virtualizationType;

}

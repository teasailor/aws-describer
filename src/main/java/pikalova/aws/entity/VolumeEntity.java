package pikalova.aws.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
@Table(name = "VOLUME",
		indexes = { @Index(name = "I__VOLUME_ID", columnList = "volumeId", unique = true) })
public class VolumeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * <p>
	 * The ID of the volume.
	 * </p>
	 */
	@Column(nullable = false)
	private String volumeId;

	/**
	 * <p>
	 * Information about the volume attachments.
	 * </p>
	 */
	//TODO: private com.amazonaws.internal.SdkInternalList<VolumeAttachment> attachments;
	/**
	 * <p>
	 * The Availability Zone for the volume.
	 * </p>
	 */
	private String availabilityZone;
	/**
	 * <p>
	 * The time stamp when volume creation was initiated.
	 * </p>
	 */
	private Date createTime;
	/**
	 * <p>
	 * Indicates whether the volume will be encrypted.
	 * </p>
	 */
	private Boolean encrypted;
	/**
	 * <p>
	 * The full ARN of the AWS Key Management Service (AWS KMS) customer master key (CMK) that was used to protect the
	 * volume encryption key for the volume.
	 * </p>
	 */
	private String kmsKeyId;
	/**
	 * <p>
	 * The size of the volume, in GiBs.
	 * </p>
	 */
	private Integer size;
	/**
	 * <p>
	 * The snapshot from which the volume was created, if applicable.
	 * </p>
	 */
	private String snapshotId;
	/**
	 * <p>
	 * The volume state.
	 * </p>
	 */
	private String state;
	/**
	 * <p>
	 * The number of I/O operations per second (IOPS) that the volume supports. For Provisioned IOPS SSD volumes, this
	 * represents the number of IOPS that are provisioned for the volume. For General Purpose SSD volumes, this
	 * represents the baseline performance of the volume and the rate at which the volume accumulates I/O credits for
	 * bursting. For more information on General Purpose SSD baseline performance, I/O credits, and bursting, see <a
	 * href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSVolumeTypes.html">Amazon EBS Volume Types</a> in the
	 * <i>Amazon Elastic Compute Cloud User Guide</i>.
	 * </p>
	 * <p>
	 * Constraint: Range is 100-20000 IOPS for io1 volumes and 100-10000 IOPS for <code>gp2</code> volumes.
	 * </p>
	 * <p>
	 * Condition: This parameter is required for requests to create <code>io1</code> volumes; it is not used in requests
	 * to create <code>gp2</code>, <code>st1</code>, <code>sc1</code>, or <code>standard</code> volumes.
	 * </p>
	 */
	private Integer iops;

	/**
	 * <p>
	 * The volume type. This can be <code>gp2</code> for General Purpose SSD, <code>io1</code> for Provisioned IOPS SSD,
	 * <code>st1</code> for Throughput Optimized HDD, <code>sc1</code> for Cold HDD, or <code>standard</code> for
	 * Magnetic volumes.
	 * </p>
	 */
	private String volumeType;

}

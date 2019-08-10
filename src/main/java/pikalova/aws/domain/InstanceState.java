package pikalova.aws.domain;

import lombok.Data;

@Data
public class InstanceState {
	/**
	 * <p>
	 * The low byte represents the state. The high byte is an opaque internal value and should be ignored.
	 * </p>
	 * <ul>
	 * <li>
	 * <p>
	 * <code>0</code> : <code>pending</code>
	 * </p>
	 * </li>
	 * <li>
	 * <p>
	 * <code>16</code> : <code>running</code>
	 * </p>
	 * </li>
	 * <li>
	 * <p>
	 * <code>32</code> : <code>shutting-down</code>
	 * </p>
	 * </li>
	 * <li>
	 * <p>
	 * <code>48</code> : <code>terminated</code>
	 * </p>
	 * </li>
	 * <li>
	 * <p>
	 * <code>64</code> : <code>stopping</code>
	 * </p>
	 * </li>
	 * <li>
	 * <p>
	 * <code>80</code> : <code>stopped</code>
	 * </p>
	 * </li>
	 * </ul>
	 */
	private Integer code;
	/**
	 * <p>
	 * The current state of the instance.
	 * </p>
	 */
	private String name;
}

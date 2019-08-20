package pikalova.aws.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstanceState {
	/**
	 * <p>
	 * The current state of the instance.
	 * </p>
	 */
	private String name;
}

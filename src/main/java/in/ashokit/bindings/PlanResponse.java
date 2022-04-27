package in.ashokit.bindings;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer planId;
	private String planName;
	private String holderName;
	private String planStatus;
	private String benefitAmt;
	
}

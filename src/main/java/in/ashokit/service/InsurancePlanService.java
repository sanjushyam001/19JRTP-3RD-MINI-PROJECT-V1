package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.PlanResponse;
import in.ashokit.bindings.SearchRequest;

public interface InsurancePlanService {

	public List<PlanResponse> searchPlans(SearchRequest searchRequest);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
}

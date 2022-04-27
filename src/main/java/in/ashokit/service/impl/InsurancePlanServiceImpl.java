package in.ashokit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.PlanResponse;
import in.ashokit.bindings.SearchRequest;
import in.ashokit.entity.InsurancePlan;
import in.ashokit.repository.InsurancePlanRepository;
import in.ashokit.service.InsurancePlanService;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService{

	@Autowired
	private InsurancePlanRepository insurancePlanRepo;
	
	@Override
	public List<PlanResponse> searchPlans(SearchRequest reqst) {
		InsurancePlan entity=new InsurancePlan();
		if(reqst!=null&&reqst.getPlanName()!=null&&!reqst.getPlanName().equals("")) {
			entity.setPlanName(reqst.getPlanName());
		}
		if(reqst!=null&&reqst.getPlanStatus()!=null&&!reqst.getPlanStatus().equals("")) {
			entity.setPlanStatus(reqst.getPlanStatus());
		}
	
		Example<InsurancePlan> example = Example.of(entity);
		List<InsurancePlan> insurancePlans= insurancePlanRepo.findAll(example); 
		
		List<PlanResponse> plansData=new ArrayList<>();
		for(InsurancePlan ip:insurancePlans) {
			PlanResponse plan=new PlanResponse();
			BeanUtils.copyProperties(ip, plan);
			plansData.add(plan);
		}
		return plansData;
	}
	@Override
	public List<String> getUniquePlanNames() {
	
		return insurancePlanRepo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		
		return insurancePlanRepo.getPlanStatuses();
	}

}

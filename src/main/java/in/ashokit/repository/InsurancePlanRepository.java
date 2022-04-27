package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.InsurancePlan;

public interface InsurancePlanRepository extends JpaRepository<InsurancePlan, Serializable> {

	@Query("SELECT DISTINCT planName FROM InsurancePlan")
	public List<String> getPlanNames();
	
	@Query("SELECT DISTINCT planStatus FROM InsurancePlan")
	public List<String> getPlanStatuses();
}

package in.ashokit.controller.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.PlanResponse;
import in.ashokit.bindings.SearchRequest;
import in.ashokit.reports.ExcelReportGenerator;
import in.ashokit.reports.PdfReportGenerator;
import in.ashokit.service.InsurancePlanService;

@RestController
@CrossOrigin("*")
public class InsuranceRestController {

	@Autowired
	private InsurancePlanService service;
	
	@GetMapping("/plans-names")
	public ResponseEntity<List<String>> getPlanNames(){
	
		List<String> uniquePlanNames = service.getUniquePlanNames();
		return new ResponseEntity<List<String>>(uniquePlanNames, HttpStatus.OK);
	}
	
	@GetMapping("/plans-statuses")
	public ResponseEntity<List<String>> getPalnStatuses(){
		List<String> uniquePlanStatuses= service.getUniquePlanStatus();
		return new ResponseEntity<List<String>>(uniquePlanStatuses, HttpStatus.OK);
	}
	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlans(@RequestBody SearchRequest reqst){
	
		List<PlanResponse> searchPlans = service.searchPlans(reqst);
		
		return new ResponseEntity<>(searchPlans, HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse resp) throws IOException {
	
		resp.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=Plans.xlsx";
		resp.setHeader(headerKey, headerValue);
		
		List<PlanResponse> searchPlans = service.searchPlans(null);
		ExcelReportGenerator generator=new ExcelReportGenerator();
		generator.export(searchPlans, resp);
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse resp) throws Exception {
	
		resp.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=Plans.pdf";
		resp.setHeader(headerKey, headerValue);
		
		List<PlanResponse> searchPlans = service.searchPlans(null);
		PdfReportGenerator generator=new PdfReportGenerator();
		generator.export(searchPlans, resp);
	}
}

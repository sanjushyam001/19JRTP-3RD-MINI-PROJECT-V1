package in.ashokit.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ashokit.bindings.PlanResponse;

public class ExcelReportGenerator {

	public void export(List<PlanResponse> plans,HttpServletResponse resp) throws IOException {
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Insurance Plans");
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Plan ID");
		headerRow.createCell(1).setCellValue("Holder's Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Benefit Amount");
		
		for(int i=0;i<plans.size();i++) {
		
			
			XSSFRow dataRow = sheet.createRow(i+1);
			
			PlanResponse p=plans.get(i);
			
			dataRow.createCell(0).setCellValue(p.getPlanId());
			dataRow.createCell(1).setCellValue(p.getHolderName());
			dataRow.createCell(2).setCellValue(p.getPlanName());
			dataRow.createCell(3).setCellValue(p.getPlanStatus());
			dataRow.createCell(4).setCellValue(p.getBenefitAmt());
		}
		ServletOutputStream outputStream = resp.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}

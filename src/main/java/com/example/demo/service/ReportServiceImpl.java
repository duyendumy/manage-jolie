package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.dto.SupportingBillinfoClass;
import com.example.demo.model.Billinfo;
import com.example.demo.repository.BillinfoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private BillinfoRepository billinfoRepository;
	
	@Override
	public void exportReport(String reportFormat,Date beginDate, Date endDate) throws FileNotFoundException,JRException {
		String path = "src\\main\\resources\\static\\report";
		List<Billinfo> listbillinfo = billinfoRepository.getAllBillinfoDependDatePayments(beginDate,endDate,3);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:bills.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listbillinfo);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Jolie Management");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\reportBills.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\reportBills.pdf");
        }
	}

	@Override
	public List<Billinfo> getAllBillinfoDependDatePayments(Date beginDate, Date endDate, Integer status) {
		return billinfoRepository.getAllBillinfoDependDatePayments(beginDate, endDate,status);
	}

	@Override
	public long countBillinfoDependDatePayment(Date beginDate, Date endDate, Integer status) {
		return billinfoRepository.countBillinfoDependDatePayment(beginDate, endDate,status);
	}

	@Override
	public Float sumTotalPriceDependDatepayment(Date beginDate, Date endDate, Integer status) {
		return billinfoRepository.sumTotalPriceDependDatepayment(beginDate, endDate,status);	
	}

	@Override
	public List<SupportingBillinfoClass> getRevenue(Date beginDate, Date endDate, Integer status) {
		return billinfoRepository.getRevenue(beginDate, endDate, status);
	}

}

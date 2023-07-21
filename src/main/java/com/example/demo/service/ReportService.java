package com.example.demo.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.dto.SupportingBillinfoClass;
import com.example.demo.model.Billinfo;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	public void exportReport(String reportFormat,Date beginDate, Date endDate) throws FileNotFoundException,JRException ;
	public List<Billinfo> getAllBillinfoDependDatePayments(Date beginDate, Date endDate, Integer status);
	long countBillinfoDependDatePayment(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,@Param("status") Integer status);
	Float sumTotalPriceDependDatepayment(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,@Param("status") Integer status);
	public List<SupportingBillinfoClass> getRevenue(Date beginDate, Date endDate, Integer status); 
}

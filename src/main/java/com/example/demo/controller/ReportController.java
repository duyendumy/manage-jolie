package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.Report;
import com.example.demo.dto.SupportingClass;
import com.example.demo.dto.SupportingBillinfoClass;
import com.example.demo.model.Billinfo;

import com.example.demo.service.ReportService;
import com.example.demo.service.BillinfoService;
import com.example.demo.service.DetailcartService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;

	@Autowired
	private BillinfoService billinfoService;

	@Autowired
	private DetailcartService detailcartService;

	@GetMapping("/report")
	public String viewReport(Model model) {

		Report report = new Report();
		model.addAttribute("report", report);
		//Report report2 = new Report();
		//model.addAttribute("report2", report2);
		try {
			// Now is today and then is 7 days ago
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime then = now.minusDays(7);

			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String strNow = now.format(format);
			String strThen = then.format(format);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date BeginDate = formatter.parse(strThen);
			Date EndDate = formatter.parse(strNow);

			// Get list of successful billinfo
			List<Billinfo> listReportBillinfo = reportService.getAllBillinfoDependDatePayments(BeginDate, EndDate, 3);
			model.addAttribute("listReportBillinfo", listReportBillinfo);

			// Prepare for selling product chart
			List<Integer> product = new ArrayList<Integer>();
			List<Long> quantity = new ArrayList<Long>();
			Integer id;
			for (Billinfo i : listReportBillinfo) {
				id = i.getCart().getId();
				List<SupportingClass> supportingClass = detailcartService.getSoldProduct(id);
				for (SupportingClass j : supportingClass) {
					product.add(j.getProduct());
					quantity.add(j.getQuantity());
				}
			}

			// Convert two list product and quantity to Map
			Map<Integer, Long> myMap = this.convertToMap(product, quantity);

			// Find the best selling product
			Map.Entry<Integer, Long> maxEntry = myMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
					.orElse(null);
			if (maxEntry != null) {
				model.addAttribute("bestSellingProduct", maxEntry.getKey());
			} else {
				model.addAttribute("bestSellingProduct", 0);
			}
			model.addAttribute("product", myMap.keySet());
			model.addAttribute("quantity", myMap.values());

			// Find long number of bill info
			long countBillinfoDependDatePayment = reportService.countBillinfoDependDatePayment(BeginDate, EndDate, 3);
			// Find Sum of total price of payment
			Float sumTotalPriceDependDatepayment = reportService.sumTotalPriceDependDatepayment(BeginDate, EndDate, 3);
			model.addAttribute("countBillinfoDependDatePayment", countBillinfoDependDatePayment);
			model.addAttribute("sumTotalPriceDependDatepayment", sumTotalPriceDependDatepayment);
			
			// Get list of revenue
			List<SupportingBillinfoClass> supportingBillinfoClass = reportService.getRevenue(BeginDate, EndDate, 3);
			List<Double> totalPrice = new ArrayList<Double>();
			List<String> datePayment = new ArrayList<String>();
			String date;
			for (SupportingBillinfoClass i : supportingBillinfoClass) {
				totalPrice.add(i.getTotalPrice());
				date = formatter.format(i.getDatePayment());
				datePayment.add(date);
			}			
			// Convert two list to Map
			TreeMap<String, Double> myMap2 = this.convertToMapBillinfo(datePayment, totalPrice);
			
			model.addAttribute("datePayment", myMap2.keySet());
			model.addAttribute("totalPrice", myMap2.values());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "report";
	}

	@GetMapping("/displayReport")
	public String displayReport(@Param("beginDate") String beginDate, @Param("endDate") String endDate, Model model)
			throws FileNotFoundException, JRException {
		// Format date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Report report = new Report(beginDate, endDate);
		model.addAttribute("report", report);
		//Report report2 = new Report();
		//model.addAttribute("report2", report2);

		try {
			Date BeginDate = formatter.parse(beginDate);
			Date EndDate = formatter.parse(endDate);
			
			// Get list of successful billinfo
			List<Billinfo> listReportBillinfo = reportService.getAllBillinfoDependDatePayments(BeginDate, EndDate, 3);
			model.addAttribute("listReportBillinfo", listReportBillinfo);

			// Prepare for selling product chart
			List<Integer> product = new ArrayList<Integer>();
			List<Long> quantity = new ArrayList<Long>();
			Integer id;
			for (Billinfo i : listReportBillinfo) {
				id = i.getCart().getId();
				List<SupportingClass> supportingClass = detailcartService.getSoldProduct(id);
				for (SupportingClass j : supportingClass) {
					product.add(j.getProduct());
					quantity.add(j.getQuantity());
				}
			}
			// Convert two list to Map
			Map<Integer, Long> myMap = this.convertToMap(product, quantity);

			// Find the best selling product
			Map.Entry<Integer, Long> maxEntry = myMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
					.orElse(null);
			if (maxEntry != null) {
				model.addAttribute("bestSellingProduct", maxEntry.getKey());
			} else {
				model.addAttribute("bestSellingProduct", 0);
			}
			model.addAttribute("product", myMap.keySet());
			model.addAttribute("quantity", myMap.values());

			// Find number of bill info
			long countBillinfoDependDatePayment = reportService.countBillinfoDependDatePayment(BeginDate, EndDate, 3);
			// Find Sum of total price of payment
			Float sumTotalPriceDependDatepayment = reportService.sumTotalPriceDependDatepayment(BeginDate, EndDate, 3);
			if (countBillinfoDependDatePayment == 0) {
				model.addAttribute("countBillinfoDependDatePayment", 0);
			}
			if (sumTotalPriceDependDatepayment == null) {
				model.addAttribute("sumTotalPriceDependDatepayment", 0);
			} else {
				model.addAttribute("countBillinfoDependDatePayment", countBillinfoDependDatePayment);
				model.addAttribute("sumTotalPriceDependDatepayment", sumTotalPriceDependDatepayment);

			}
			
			// Get list of revenue
			List<SupportingBillinfoClass> supportingBillinfoClass = reportService.getRevenue(BeginDate, EndDate, 3);
			List<Double> totalPrice = new ArrayList<Double>();
			List<String> datePayment = new ArrayList<String>();
			String date;
			for (SupportingBillinfoClass i : supportingBillinfoClass) {
				totalPrice.add(i.getTotalPrice());
				date = formatter.format(i.getDatePayment());
				datePayment.add(date);
			}
			
			// Convert two list to Map
			TreeMap<String, Double> myMap2 = this.convertToMapBillinfo(datePayment, totalPrice);
			
			model.addAttribute("datePayment", myMap2.keySet());
			model.addAttribute("totalPrice", myMap2.values());

			// export report pdf
			this.reportService.exportReport("pdf", BeginDate, EndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "report";
	}

	public Map<Integer, Long> convertToMap(List<Integer> list1, List<Long> list2) {
		Map<Integer, Long> myMap = new HashMap<Integer, Long>();
		int i = 0;
		for (i = 0; i < list1.size(); i++) {
			if (myMap.containsKey(list1.get(i))) {
				myMap.put(list1.get(i), myMap.get(list1.get(i)) + list2.get(i));
			} else {
				myMap.put(list1.get(i), list2.get(i));
			}
		}
		return myMap;
	}
	
	public TreeMap<String, Double> convertToMapBillinfo(List<String> datePayment, List<Double> totalPrice) {
		TreeMap<String, Double> treeMap = new TreeMap<String, Double>();
		int i = 0;
		for (i = 0; i < datePayment.size(); i++) {
			if (treeMap.containsKey(datePayment.get(i))) {
				treeMap.put(datePayment.get(i), treeMap.get(datePayment.get(i)) + totalPrice.get(i));
			} else {
				treeMap.put(datePayment.get(i), totalPrice.get(i));
			}
		}
		return treeMap;
	}
	/*

	@GetMapping("/barChart")
	public String viewBarChart(@Param("beginDate") String beginDate, @Param("endDate") String endDate, Model model)
			throws FileNotFoundException, JRException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Report report = new Report();
		model.addAttribute("report", report);
		Report report2 = new Report(beginDate, endDate);
		model.addAttribute("report2", report2);

		// Prepare for selling product chart
		List<Integer> product = new ArrayList<Integer>();
		List<Long> quantity = new ArrayList<Long>();
		Integer id;
		try {
			Date BeginDate = formatter.parse(beginDate);
			Date EndDate = formatter.parse(endDate);
			List<Billinfo> listReportBillinfo = reportService.getAllBillinfoDependDatePayments(BeginDate, EndDate, 3);
			for (Billinfo i : listReportBillinfo) {
				id = i.getCart().getId();
				List<SupportingClass> supportingClass = detailcartService.getSoldProduct(id);
				for (SupportingClass j : supportingClass) {
					product.add(j.getProduct());
					quantity.add(j.getQuantity());
				}
			}

			// Convert two list to Map
			Map<Integer, Long> myMap = this.convertToMap(product, quantity);

			// Find the best selling product
			Map.Entry<Integer, Long> maxEntry = myMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
					.orElse(null);
			if (maxEntry != null) {
				model.addAttribute("bestSellingProduct", maxEntry.getKey());
			} else {
				model.addAttribute("bestSellingProduct", 0);
			}
			model.addAttribute("product", myMap.keySet());
			model.addAttribute("quantity", myMap.values());

			// Find long number of bill info
			long countBillinfoDependDatePayment = reportService.countBillinfoDependDatePayment(BeginDate, EndDate, 3);
			// Find Sum of total price of payment
			Float sumTotalPriceDependDatepayment = reportService.sumTotalPriceDependDatepayment(BeginDate, EndDate, 3);
			if (countBillinfoDependDatePayment == 0) {
				model.addAttribute("countBillinfoDependDatePayment", 0);
			}
			if (sumTotalPriceDependDatepayment == null) {
				model.addAttribute("sumTotalPriceDependDatepayment", 0);
			} else {
				model.addAttribute("countBillinfoDependDatePayment", countBillinfoDependDatePayment);
				model.addAttribute("sumTotalPriceDependDatepayment", sumTotalPriceDependDatepayment);

			}
			List<SupportingBillinfoClass> supportingBillinfoClass = reportService.getRevenue(BeginDate, EndDate, 3);
			List<Double> totalPrice = new ArrayList<Double>();
			List<String> datePayment = new ArrayList<String>();
			String date;
			for (SupportingBillinfoClass i : supportingBillinfoClass) {
				totalPrice.add(i.getTotalPrice());
				date = formatter.format(i.getDatePayment());
				datePayment.add(date);
			}
			model.addAttribute("datePayment", datePayment);
			model.addAttribute("totalPrice", totalPrice);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "report";
	}
	*/

}

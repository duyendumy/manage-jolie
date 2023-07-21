package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

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
public class BillinfoServiceImpl implements BillinfoService {

	@Autowired
	private BillinfoRepository billinfoRepository;
	
	@Override
	public List<Billinfo> getAllBillinfo() {
		return billinfoRepository.findAll();
	}

	@Override
	public List<Billinfo> getAllBillinfoDependStatus(int status) {
		return billinfoRepository.getAllBillinfoDependStatus(status);
	}

	@Override
	public Billinfo getBillinfoById(Integer id) {
		Optional<Billinfo> optional = billinfoRepository.findById(id);
		Billinfo billinfo = null;
		if (optional.isPresent()) {
			billinfo = optional.get();
		} else {
			throw new RuntimeException("Billinfo not found for id:" + id);
		}
		return billinfo;
	}

	@Override
	@Transactional
	public void updateStatusBill(int status, Integer id) {
	   this.billinfoRepository.updateStatusBill(status, id);		
	}

	@Override
	public List<Billinfo> searchBillinfo(String keyword) {
		if (keyword != null) {
			return billinfoRepository.searchBillinfo(keyword);
		}
		return null;
	}

	@Override
	public long countBillinfo(Integer status) {
		return billinfoRepository.countBillinfo(status);
	}

	@Override
	public Float sumTotalPrice(Integer status) {
		return billinfoRepository.sumTotalPrice(status);
	}

	@Override
	public Billinfo getBillinfoDependId(int id) {
		return billinfoRepository.getBillinfoDependId(id);
	}
	

}

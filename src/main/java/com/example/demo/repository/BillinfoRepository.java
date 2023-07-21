package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.SupportingBillinfoClass;
import com.example.demo.model.Billinfo;
import com.example.demo.model.Cart;


@Repository
public interface BillinfoRepository extends JpaRepository<Billinfo, Integer >{

	@Query("SELECT b FROM Billinfo b WHERE b.status =:status")
	public List<Billinfo> getAllBillinfoDependStatus(@Param("status") int status);
	
	@Modifying
	@Query("UPDATE Billinfo b SET b.status = :status WHERE b.id LIKE :id")
	void updateStatusBill( @Param("status") int status, @Param("id") Integer id);
	
	@Query("SELECT b FROM Billinfo b WHERE CONCAT(b.shippingAddress, b.recipientName, b.recipientPhone, b.datePayment) LIKE %?1%")
	public List<Billinfo> searchBillinfo(String keyword); 
	
	@Query("SELECT COUNT(b) FROM Billinfo b WHERE b.status =:status")
	public long countBillinfo(@Param("status") Integer status);
	
	@Query("SELECT SUM(b.totalPrice) FROM Billinfo b WHERE b.status =:status")
	public Float sumTotalPrice(@Param("status") Integer status);
	
	@Query("SELECT b FROM Billinfo b WHERE b.datePayment >=:beginDate AND b.datePayment <=:endDate AND b.status=:status")
	public List<Billinfo> getAllBillinfoDependDatePayments(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("status") Integer status);

	@Query("SELECT COUNT(b) FROM Billinfo b WHERE  b.datePayment >=:beginDate AND b.datePayment <=:endDate AND b.status =:status")
	long countBillinfoDependDatePayment(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,@Param("status") Integer status);
	
	@Query("SELECT SUM(b.totalPrice) FROM Billinfo b  WHERE b.datePayment >=:beginDate AND b.datePayment <=:endDate AND  b.status =:status")
	Float sumTotalPriceDependDatepayment(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,@Param("status") Integer status);

	@Query("SELECT b FROM Billinfo b WHERE b.id =:id")
	public Billinfo getBillinfoDependId(@Param("id") int id);
	
	@Query("SELECT new com.example.demo.dto.SupportingBillinfoClass(b.datePayment, SUM(b.totalPrice)) FROM Billinfo AS b WHERE b.datePayment >=:beginDate AND b.datePayment <=:endDate AND b.status=:status GROUP BY b.datePayment")
	public List<SupportingBillinfoClass> getRevenue(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("status") Integer status); 
	

}

package com.sudeep.rest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.sudeep.rest.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

	
	
	
	
	public List<Customer> findByMobileContaining(String mobile);
	
	public List<Customer> findByAgeGreaterThan(int age, Pageable pageable);
	
	@Query(value = "SELECT * FROM customer WHERE mobile LIKE :searchTerm", nativeQuery = true)
	public List<Customer> searchWithNativeQuery(
			@Param("searchTerm") String searchTerm);

	@Query("SELECT p FROM Customer p WHERE p.mobile LIKE :searchTerm")
	public List<Customer> searchWithJPQLQuery(
			@Param("searchTerm") String searchTerm);
	
	  //This is a query method.
	public  List<Customer> findByMobile(String mobile);
   
   
	public List<Customer> findAllOrderByCreatedOn(Pageable pageable);

   @Query("select c from Customer c where c.mobile = ?1")
   public  Customer getByMobile(String mobile);
   
   
   @Query("select count(c) <= 0 from Customer c where c.mobile = ?1")
   public  boolean CheckExistsByMobile(String mobile);
    
    Customer findById(Long id);
    
    Customer findByName(String name);
    
    Customer findByEmail(String email);
    
//    boolean isCustomerExist(Customer Customer);
    
    


}

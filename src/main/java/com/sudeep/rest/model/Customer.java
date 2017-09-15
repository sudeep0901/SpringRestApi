package com.sudeep.rest.model;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;




import javax.transaction.Transactional;





import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Transactional
public class Customer {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public Long getId() {
		return id;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	private String email;
	private String measurement;
	private String avatar;
	private int age;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dob;
	private Gender gender;
	private String remarks;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private String updatedOn;
	private Date created;
	  private Date updated;

	  private Date deleted;

	  @PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }

	  @PreUpdate
	  protected void onUpdate() {
	    updated = new Date();
	  }
	  @Column(name = "created_by1")
	    @CreatedBy
	    private String createdBy1;
	 
	    @Column(name = "modified_by1	")
	    @LastModifiedBy
	    private String modifiedBy1;

	  
	  @PreRemove
	    public void onPreRemove() {  deleted = new Date(); }
	  
	  
	@CreatedDate
	@Column(columnDefinition = "TIMESTAMP")
	private Date createdOn;

	// Orders
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public Customer() {

		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Transient
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", createdOn="
				+ createdOn + ", orders=" + orders + "]";
	}

}

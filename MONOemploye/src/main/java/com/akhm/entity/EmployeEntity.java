package com.akhm.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
@Data
@Entity
@Table(name="EMPLOYE_TABLE")
@Builder
public class EmployeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private Long empId;
	@Column(name="EMP_NAME")
	private String empName;
	@Column(name="EMP_DOB")
	private Date empDOB;
	@Column(name="EMP_DPT")
	private String empDpt;
	
	

}

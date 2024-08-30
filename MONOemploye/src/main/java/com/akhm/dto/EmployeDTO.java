package com.akhm.dto;


import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class EmployeDTO {
	private Long empId;
	private String empName;
	private Date empDOB;
	private String empDpt;
}

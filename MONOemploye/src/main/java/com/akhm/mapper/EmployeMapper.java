package com.akhm.mapper;

import com.akhm.dateutils.DateUtils;
import com.akhm.dto.EmployeDTO;
import com.akhm.entity.EmployeEntity;
public class EmployeMapper {
	private EmployeMapper() {
		
	}
				
	public static EmployeDTO entityToDTO(EmployeEntity entity) {
	    return EmployeDTO.builder()
	            .empId(entity.getEmpId())
	            .empName(entity.getEmpName())
	            .empDOB(DateUtils.convertUtilToSql(entity.getEmpDOB())) // Assuming convertUtilToSql is correct
	            .empDpt(entity.getEmpDpt())
	            .build();
	}

	public static EmployeEntity dtoToEntity(EmployeDTO eDTO) {
	    return EmployeEntity.builder()
	            .empId(eDTO.getEmpId())
	            .empName(eDTO.getEmpName())
	            .empDOB(DateUtils.convertSqlToUtil(eDTO.getEmpDOB())) // Assuming convertSqlToUtil is correct
	            .empDpt(eDTO.getEmpDpt())
	            .build();
	}

		
	

}

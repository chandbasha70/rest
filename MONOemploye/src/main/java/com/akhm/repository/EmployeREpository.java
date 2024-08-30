package com.akhm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.akhm.entity.EmployeEntity;
@Repository
public interface EmployeREpository extends ReactiveCrudRepository<EmployeEntity, Long>{

}

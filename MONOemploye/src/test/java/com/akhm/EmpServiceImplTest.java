package com.akhm;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.commons.math.random.EmpiricalDistributionImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.akhm.Exception.MyCustomException;
import com.akhm.dto.EmployeDTO;
import com.akhm.entity.EmployeEntity;
import com.akhm.mapper.EmployeMapper;
import com.akhm.repository.EmployeREpository;
import com.akhm.serviceimpl.EmploeServiceImpl;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class EmpServiceImplTest {
	@InjectMocks
	private EmploeServiceImpl emploeServiceImpl;
	@Mock
	private EmployeREpository employeREpository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void insertUSerTest() {
		EmployeDTO employeDTO = EmployeDTO.builder().build();
		EmployeEntity employeEntity = EmployeMapper.dtoToEntity(employeDTO);
		EmployeEntity savedEmpEntity = EmployeEntity.builder().empId(1l).build();
		when(employeREpository.save(employeEntity)).thenReturn(Mono.just(savedEmpEntity));
		Mono<Long> userId = emploeServiceImpl.insertEmploye(employeDTO);
		StepVerifier.create(userId).expectNext(savedEmpEntity.getEmpId()).verifyComplete();

	}

	@Test
	public void inserstUserTest_ErrorHandaling() {
		EmployeDTO employeDTO = EmployeDTO.builder().build();
		RuntimeException runTimeError = new RuntimeException("user details not saved");
		when(employeREpository.save(any(EmployeEntity.class))).thenReturn(Mono.error(runTimeError));
		Mono<Long> userId = emploeServiceImpl.insertEmploye(employeDTO);
		StepVerifier.create(userId).expectErrorMatches(error -> error instanceof MyCustomException).verify();
	}

	@Test
	public void getAllUsersTest() {
		EmployeEntity employeEntity = EmployeEntity.builder().empId(1l).build();
		EmployeEntity employeEntity2 = EmployeEntity.builder().empId(2l).build();
		when(employeREpository.findAll()).thenReturn(Flux.just(employeEntity, employeEntity2));
		Mono<List<EmployeDTO>> users = emploeServiceImpl.getAllEmployes();

		StepVerifier.create(users).expectNextMatches(usersList -> usersList.size() == 2
				&& usersList.get(0).getEmpId() == 1).verifyComplete();
	}
}


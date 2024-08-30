package com.akhm.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import org.springframework.http.ResponseEntity;

import com.akhm.Exception.MyCustomException;
import com.akhm.dto.EmployeDTO;
import com.akhm.service.EmployeService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
  
@RunWith(MockitoJUnitRunner.class)
public class EmpControllerTest {
	@InjectMocks
	private EmpController empController;

	@Mock
	private EmployeService employeService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		// userService = mock(UserService.class);
		// userController = new UserController();

	}

	@Test
	public void saveUserTest() {
		EmployeDTO employeDTO = EmployeDTO.builder().build();
		when(employeService.insertEmploye(any(EmployeDTO.class))).thenReturn(Mono.just(1l));
		Mono<ResponseEntity<String>> response = empController.saveEmp(employeDTO);
		StepVerifier.create(response).expectNextMatches(responseEntity -> {
			HttpStatusCode status = responseEntity.getStatusCode();
			String body = responseEntity.getBody();
			return status == HttpStatus.CREATED && body.contains("user saved successfully with id:");
        })
        .verifyComplete();

	}
	
    @Test
	public void saveUserTestException() {
    	EmployeDTO employeDTO = EmployeDTO.builder().build();
		when(employeService.insertEmploye(any(EmployeDTO.class))).thenReturn(Mono.error(new MyCustomException("failed to save userData")));
		Mono<ResponseEntity<String>> response = empController.saveEmp(employeDTO);
		StepVerifier.create(response).expectNextMatches(responseEntity -> {
			HttpStatusCode status = responseEntity.getStatusCode();
			String body = responseEntity.getBody();
			return status == HttpStatus.INTERNAL_SERVER_ERROR && body.contains("failed to save user:");
		}).verifyComplete();
	}
 

}


package com.akhm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akhm.dto.EmployeDTO;
import com.akhm.service.EmployeService;

import reactor.core.publisher.Mono;

@RestController

public class EmpController {
	@Autowired
	private EmployeService employeService;

	@PostMapping("/save")
	public Mono<ResponseEntity<String>> saveEmp(@RequestBody EmployeDTO employeDTO) {
		return employeService.insertEmploye(employeDTO).map(
				empId -> ResponseEntity.status(HttpStatus.CREATED).body("employe saved successfully with id: " + empId))
				.onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("failed to save emp" + error.getMessage())));

	}

	@GetMapping("/getAll")
	public Mono<ResponseEntity<List<EmployeDTO>>> getAllEmps() {
		return employeService.getAllEmployes().map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build())
				.onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(null)));

	}

}

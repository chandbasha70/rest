package com.akhm.service;

import java.util.List;

import com.akhm.dto.EmployeDTO;
import reactor.core.publisher.Mono;

public interface EmployeService {
	public Mono<Long> insertEmploye(EmployeDTO empDTO);

	public Mono<List<EmployeDTO>> getAllEmployes();

}

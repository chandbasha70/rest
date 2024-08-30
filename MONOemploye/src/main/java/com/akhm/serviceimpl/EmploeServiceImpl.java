package com.akhm.serviceimpl;

import java.time.Duration;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhm.Exception.MyCustomException;
import com.akhm.dto.EmployeDTO;
import com.akhm.mapper.EmployeMapper;
import com.akhm.repository.EmployeREpository;
import com.akhm.service.EmployeService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

@Service
@Slf4j
public class EmploeServiceImpl implements EmployeService {

	@Autowired
	private EmployeREpository employeREpository;

	@Override
	public Mono<Long> insertEmploye(EmployeDTO empDTO) {
		
		RetryBackoffSpec retryspec=Retry.backoff(2, Duration.ofSeconds(3))
				.onRetryExhaustedThrow((retrybackofspec,retrySignal)->new MyCustomException("in insert method saving emp failed",retrySignal.failure()));
		return employeREpository.save(EmployeMapper.dtoToEntity(empDTO)).map(empentity->{
			log.info("im empserviceimpl inser method empId is{}",empentity.getEmpId());
			return empentity.getEmpId();
		}).onErrorMap(error->{log.info("in serviceimpl insert meyhod exception raised-{}",error);
		
		throw new MyCustomException("failed to save the emp data",error); }).
		retryWhen(retryspec);
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		log.info("in EmpServiceImpl class insertMethod is executing");
//		RetryBackoffSpec retryspec = Retry.backoff(3, Duration.ofSeconds(2)).onRetryExhaustedThrow((RetryBackoffSpec,
//				retrySignal) -> new MyCustomException("Employe not inserted after reties", retrySignal.failure()));
//		log.debug("After Retry Object creation");
//		
//		return employeREpository.save(EmployeMapper.dtoToEntity(empDTO)).map(empEntity -> {
//			log.info("in empservice impl insertmethod..empid id->{}", empEntity.getEmpId());
//			return empEntity.getEmpId();
//		}).onErrorMap(error -> {
//			log.error("in empservice impl insert method exception raised-{}", error);
//			throw new MyCustomException("failed to emp data saving", error);
//		}).retryWhen(retryspec);

	}

	@Override
	public Mono<List<EmployeDTO>> getAllEmployes() {
		return employeREpository.findAll().map(empEntity -> EmployeMapper.entityToDTO(empEntity)).collectList()
				.retryWhen(Retry.backoff(3, Duration.ofSeconds(5)));
	}

}

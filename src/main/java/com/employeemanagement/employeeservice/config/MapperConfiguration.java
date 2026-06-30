package com.employeemanagement.employeeservice.config;

import com.employeemanagement.employeeservice.entity.Employee;
import com.employeemanagement.employeeservice.model.EmployeeRequestDTO;
import com.employeemanagement.employeeservice.model.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfiguration.class)
public interface MapperConfiguration {
    Employee toEntity(EmployeeRequestDTO requestDTO);

    EmployeeResponseDTO toResponseDTO(Employee employee);
}
package com.employeemanagement.employeeservice.config;

import com.employeemanagement.employeeservice.config.MapperConfiguration;
import com.employeemanagement.employeeservice.entity.Employee;
import com.employeemanagement.employeeservice.model.EmployeeRequestDTO;
import com.employeemanagement.employeeservice.model.EmployeeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface EmployeeMapper {

    Employee toEntity(EmployeeRequestDTO requestDTO);

    EmployeeResponseDTO toResponseDTO(Employee employee);
}
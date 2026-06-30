package com.employeemanagement.employeeservice.service;

import com.employeemanagement.employeeservice.model.EmployeeRequestDTO;
import com.employeemanagement.employeeservice.model.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO);

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> bulkCreateEmployees(List<EmployeeRequestDTO> requestDTOList);
}

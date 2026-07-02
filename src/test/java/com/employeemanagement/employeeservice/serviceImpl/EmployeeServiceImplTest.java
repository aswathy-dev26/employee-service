package com.employeemanagement.employeeservice.serviceImpl;


import com.employeemanagement.employeeservice.config.EmployeeMapper;
import com.employeemanagement.employeeservice.entity.Employee;

import com.employeemanagement.employeeservice.model.EmployeeRequestDTO;
import com.employeemanagement.employeeservice.model.EmployeeResponseDTO;
import com.employeemanagement.employeeservice.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void createEmployee_shouldReturnEmployeeResponse() {
        EmployeeRequestDTO request = EmployeeRequestDTO.builder()
                .employeeCode("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phone("9876543210")
                .department("IT")
                .designation("Developer")
                .salary(BigDecimal.valueOf(50000))
                .joiningDate(LocalDate.now())
                .active(true)
                .build();

        Employee employee = Employee.builder()
                .employeeCode("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        Employee savedEmployee = Employee.builder()
                .id(1L)
                .employeeCode("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        EmployeeResponseDTO response = EmployeeResponseDTO.builder()
                .id(1L)
                .employeeCode("EMP001")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        when(employeeRepository.existsByEmail("john@example.com")).thenReturn(false);
       // when(employeeRepository.existsByEmployeeCode("EMP001")).thenReturn(false);
        when(employeeMapper.toEntity(request)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);
        when(employeeMapper.toResponseDTO(savedEmployee)).thenReturn(response);

        EmployeeResponseDTO result = employeeService.createEmployee(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("EMP001", result.getEmployeeCode());
        assertEquals("john@example.com", result.getEmail());

        verify(employeeRepository).save(employee);
    }
}
package com.employeemanagement.employeeservice.controller;

import com.employeemanagement.employeeservice.model.EmployeeRequestDTO;
import com.employeemanagement.employeeservice.model.EmployeeResponseDTO;
import com.employeemanagement.employeeservice.response.ApiResponse;
import com.employeemanagement.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {

        EmployeeResponseDTO employee =
                employeeService.createEmployee(requestDTO);

        ApiResponse<EmployeeResponseDTO> response =
                ApiResponse.<EmployeeResponseDTO>builder()
                        .success(true)
                        .status(HttpStatus.CREATED.value())
                        .message("Employee created successfully")
                        .timestamp(LocalDateTime.now())
                        .data(employee)
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<EmployeeResponseDTO>> createEmployeesBulk(
            @Valid @RequestBody List<EmployeeRequestDTO> requestDTOList
    ) {
        List<EmployeeResponseDTO> response = employeeService.bulkCreateEmployees(requestDTOList);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Long id
    ) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
package com.employeemanagement.employeeservice.serviceImpl;

import com.employeemanagement.employeeservice.config.MapperConfiguration;
import com.employeemanagement.employeeservice.entity.Employee;
import com.employeemanagement.employeeservice.model.EmployeeRequestDTO;
import com.employeemanagement.employeeservice.model.EmployeeResponseDTO;
import com.employeemanagement.employeeservice.repo.EmployeeRepo;
import com.employeemanagement.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepository;
    private final MapperConfiguration employeeMapper;
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        Employee employee = employeeMapper.toEntity(requestDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDTO(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponseDTO)
                .toList();
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        return employeeMapper.toResponseDTO(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existingEmployee.setEmployeeCode(requestDTO.getEmployeeCode());
        existingEmployee.setFirstName(requestDTO.getFirstName());
        existingEmployee.setLastName(requestDTO.getLastName());
        existingEmployee.setEmail(requestDTO.getEmail());
        existingEmployee.setPhone(requestDTO.getPhone());
        existingEmployee.setDepartment(requestDTO.getDepartment());
        existingEmployee.setDesignation(requestDTO.getDesignation());
        existingEmployee.setSalary(requestDTO.getSalary());
        existingEmployee.setJoiningDate(requestDTO.getJoiningDate());
        existingEmployee.setActive(requestDTO.getActive());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.toResponseDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employeeRepository.delete(employee);

    }

    @Override
    public List<EmployeeResponseDTO> bulkCreateEmployees(List<EmployeeRequestDTO> requestDTOList) {
        List<Employee> employees = requestDTOList.stream()
                .map(employeeMapper::toEntity)
                .toList();

        List<Employee> savedEmployees = employeeRepository.saveAll(employees);

        return savedEmployees.stream()
                .map(employeeMapper::toResponseDTO)
                .toList();
    }
}

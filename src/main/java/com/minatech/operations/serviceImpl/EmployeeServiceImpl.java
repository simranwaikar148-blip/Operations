package com.minatech.operations.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minatech.operations.entity.Employee;
import com.minatech.operations.repository.EmployeeRepository;
import com.minatech.operations.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        // ensure addresses reference the employee
        if (employee.getAddresses() != null) {
            employee.getAddresses().forEach(a -> a.setEmployee(employee));
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());

        // update addresses properly
        existing.getAddresses().clear();
        if (employee.getAddresses() != null) {
            employee.getAddresses().forEach(a -> a.setEmployee(existing));
            existing.getAddresses().addAll(employee.getAddresses());
        }

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
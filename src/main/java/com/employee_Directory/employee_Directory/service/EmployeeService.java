package com.employee_Directory.employee_Directory.service;
import com.employee_Directory.employee_Directory.Response.ResponseMessage;
import com.employee_Directory.employee_Directory.dao.EmployeeRepository;
import com.employee_Directory.employee_Directory.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmpById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee Doesn't Exist"));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee Doesn't Exist"));
        if(employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
        }
        if(employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        return employeeRepository.save(existingEmployee);
    }

    public ResponseEntity<ResponseMessage> deleteEmployee(int id) {
        try {
            if(employeeRepository.findById(id).isPresent()) {
                employeeRepository.deleteById(id);
                ResponseMessage message = new ResponseMessage(true, "Employee Deleted Successfully");
                return ResponseEntity.ok(message);
            } else {
                ResponseMessage message = new ResponseMessage(false, "Employee Not Found With ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        } catch (Exception e) {
            ResponseMessage message = new ResponseMessage(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}

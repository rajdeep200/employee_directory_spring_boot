package com.employee_Directory.employee_Directory.controller;

import com.employee_Directory.employee_Directory.Response.ResponseMessage;
import com.employee_Directory.employee_Directory.entity.Employee;
import com.employee_Directory.employee_Directory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmpById(id);
    }

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteEmployeeById (@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}

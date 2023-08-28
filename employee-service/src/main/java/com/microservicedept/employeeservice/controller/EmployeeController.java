package com.microservicedept.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicedept.employeeservice.model.Employee;
import com.microservicedept.employeeservice.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  EmployeeRepository repository;

  @PostMapping
  public Employee add(@RequestBody Employee employee) {
    return repository.add(employee);
  }

  @GetMapping
  public List<Employee> findAll() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Employee findById(@PathVariable Long id) {
    return repository.findById(id);
  }

  @GetMapping("/department/{deptId}")
  public List<Employee> findByDept(@PathVariable("deptId") Long deptId) {
    return repository.findByDepartment(deptId);
  }

}

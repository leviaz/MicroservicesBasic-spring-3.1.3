package com.microservicedept.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Employee> add(@RequestBody Employee employee) {
    repository.add(employee);
    return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    repository.DeleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateStatus(@PathVariable Long id, @RequestBody Employee employee) {
    repository.updateStatus(id, employee);
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

  @PatchMapping("/transfer/{id}/{idNew}")
  public ResponseEntity<Employee> transferEmployee(@PathVariable("id") Long id, @PathVariable("idNew") Long idNew) {
    Employee employeeUpdated = repository.transferEmployee(id, idNew);
    return new ResponseEntity<Employee>(employeeUpdated, HttpStatus.ACCEPTED);
  }

}

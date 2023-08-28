package com.microservicedept.employeeservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.microservicedept.employeeservice.model.Employee;

@Repository
public class EmployeeRepository {
  private List<Employee> employees = new ArrayList<>();

  public Employee add(Employee employee) {
    employees.add(employee);
    return employee;
  }

  public List<Employee> findAll() {
    return employees;
  }

  public Employee findById(Long id) {
    return employees.stream().filter(employee -> employee.id().equals(id)).findFirst().orElseThrow();

  }

  public List<Employee> findByDepartment(Long departmentId) {
    return employees.stream().filter(employee -> employee.departmentId().equals(departmentId)).toList();

  }

  public void DeleteById(Long id) {
    employees.removeIf(employee -> employee.id().equals(id));
  }

  public Employee updateStatus(Long id, Employee employeeNew) {
    DeleteById(id);
    add(employeeNew);
    return employeeNew;

  }

}

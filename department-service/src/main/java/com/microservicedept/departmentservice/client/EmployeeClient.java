package com.microservicedept.departmentservice.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.microservicedept.departmentservice.model.Employee;

@HttpExchange
public interface EmployeeClient {

  // mapeia o request
  @GetExchange("employee/department/{deptId}")
  public List<Employee> findByDept(@PathVariable("deptId") Long deptId);

}

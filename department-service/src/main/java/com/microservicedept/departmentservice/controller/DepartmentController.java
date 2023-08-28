package com.microservicedept.departmentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicedept.departmentservice.client.EmployeeClient;
import com.microservicedept.departmentservice.model.Department;
import com.microservicedept.departmentservice.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private DepartmentRepository repository;

  @Autowired
  private EmployeeClient employeeClient;

  @PostMapping
  public Department Add(@RequestBody Department dept) {
    return repository.addDepartment(dept);
  }

  @GetMapping("/{id}")
  public Department findById(@PathVariable Long id) {
    return repository.findById(id);

  }

  @GetMapping
  public List<Department> findAll() {
    return repository.findAll();
  }

  @GetMapping("/with-employees")
  public List<Department> findAllWithEmployees() {
    // lista todos os departamentos
    List<Department> departments = repository.findAll();
    // percorre a lista e irá recolher todos os funcionários por id de dept passado,
    // transmitindo pelo set para a lista de cada dept
    departments.forEach(department -> department.setEmployees(employeeClient.findByDept(department.getId())));
    return departments;

  }

  @GetMapping("/{id}/with-employees")
  public Department findDepartmentWithEmployees(@PathVariable Long id) {
    Department department = repository.findById(id);
    department.setEmployees(employeeClient.findByDept(id));
    return department;
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    repository.deleteById(id);
  }

}

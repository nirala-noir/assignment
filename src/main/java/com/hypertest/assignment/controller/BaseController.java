package com.hypertest.assignment.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.hypertest.assignment.dto.Employee;
import com.hypertest.assignment.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BaseController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/ping")
    public ResponseEntity<String> testController() {
        return new ResponseEntity<>("pong", HttpStatus.ACCEPTED);
    }

    @GetMapping("api/allEmployee")
    public ResponseEntity<String> getAllEmployee() {
        List<Employee> allEmployee = employeeRepo.findAll();
        return new ResponseEntity<>(allEmployee.toString(), HttpStatus.ACCEPTED);
    }

    @PostMapping("api/createNewPost")
    public ResponseEntity<String> insertEmployee(@RequestBody JsonNode employeeData) {
        Employee employee = new Employee();
        try {
            employee.setName(employeeData.has("name") ? employeeData.get("name").asText() : null);
            employee.setExperienceYear(employeeData.has("experienceYear")
                    ? employeeData.get("experienceYear").asInt() : null);
            employee = employeeRepo.save(employee);
            log.info("employee data saved successfully with id: {}", employee.getId());
        } catch (Exception e) {
            log.error("error occurred due to employeeData : {} parsing with exception : {} ",
                    employeeData, e.getMessage());
        }
        return new ResponseEntity<>("employee ID : " + employee.getId().toString(), HttpStatus.ACCEPTED);
    }

}

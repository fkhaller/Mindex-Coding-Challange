package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private CompensationService compensationService;

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }
    
    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        LOG.debug("");
        
        //if employee does not exist catch the read will throw an exception
        //catch it, add a log that the create failed, and then throw it forward
        try {
            readEmployee(compensation.getEmployeeId());
        } catch(RuntimeException ex) {
            LOG.debug("Could not create compensation!");
            throw ex;
        }
        
        return compensationService.create(compensation);
    }

    @GetMapping("/employee/{id}")
    public Employee readEmployee(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }
    
    @GetMapping("/compensation/{id}")
    public Compensation readCompensation(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return compensationService.read(id);
    }
    
    @GetMapping("/employee/get-number-of-reporters/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);
        
        return employeeService.getNumberOfReporters(id);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    
    @PutMapping("/compensation/{id}")
    public Compensation updateCompensation(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for id [{}] and employee [{}]", id, compensation);

        compensation.setEmployeeId(id);
        return compensationService.update(compensation);
    }
}

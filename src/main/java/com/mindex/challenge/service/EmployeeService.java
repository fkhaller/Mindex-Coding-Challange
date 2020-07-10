package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService extends DatabaseService<Employee> {
    ReportingStructure getNumberOfReporters(String id);
}

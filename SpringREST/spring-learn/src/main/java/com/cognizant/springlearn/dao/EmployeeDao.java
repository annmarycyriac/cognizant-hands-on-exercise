package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    public static ArrayList<Employee> EMPLOYEE_LIST = new ArrayList<>();

    public EmployeeDao() {
        if (EMPLOYEE_LIST.isEmpty()) {
            ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
            @SuppressWarnings("unchecked")
            ArrayList<Employee> list = (ArrayList<Employee>) context.getBean("employeeList");
            EMPLOYEE_LIST.addAll(list);
        }
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        boolean found = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new EmployeeNotFoundException("Employee with ID " + employee.getId() + " not found");
        }
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        boolean removed = EMPLOYEE_LIST.removeIf(emp -> emp.getId().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }
}

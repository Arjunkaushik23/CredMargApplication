package com.credmarg.service;

import com.credmarg.model.Employee;
import com.credmarg.model.User;
import com.credmarg.repository.EmployeeRepository;
import com.credmarg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Employee createEmployee(Employee employee) {
        User user = new User();
        user.setUsername(employee.getEmail());
        user.setPassword(passwordEncoder.encode("default_password"));
        user.setRole("Role_Admin");

        User saveduser = userRepository.save(user);

        employee.setUser(saveduser);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

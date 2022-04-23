package com.example.springboot_project.Services;

import com.example.springboot_project.Model.Database.EmployeeModel;
import com.example.springboot_project.Model.Responses.LoginResponseModel;
import com.example.springboot_project.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List addEmployee (EmployeeModel employeeModel){

        employeeRepository.save(employeeModel);

        List employee = new ArrayList();
        employee.add(employeeModel);
        return employee;
    }

    public List getAllEmployees(){
        List<EmployeeModel> employeeModels=employeeRepository.findAll();

        return employeeModels;
    }
    public void updateEmployeeDetails(EmployeeModel employee) throws api.project.Exception.ObjectNotFoundException {
        if (employeeRepository.existsById(employee.getEmployeeId())) {
            EmployeeModel employeeModel = employeeRepository.findByEmployeeId(employee.getEmployeeId());

            if (employee.getFullName() != null && employee.getFullName().length() > 0) {
                employeeModel.setFullName(employee.getFullName());
            }
            if (employee.getTelephoneNumber() != null && employee.getTelephoneNumber().length() > 0) {
                employeeModel.setTelephoneNumber(employee.getTelephoneNumber());
            }
            if (employee.getRole() != null && employee.getRole().length() > 0) {
                employeeModel.setRole(employee.getRole());
            }
            if (employee.getUsername() != null && employee.getUsername().length() > 0) {
                employeeModel.setUsername(employee.getUsername());
            }
            if (employee.getPassword() != null && employee.getPassword().length() > 0) {
                employeeModel.setPassword(employee.getPassword());
            }
            if (employee.getSalary() != null && employee.getSalary().length() > 0) {
                employeeModel.setSalary(employee.getSalary());
            }
            if (employee.getNic() != null && employee.getNic().length() > 0) {
                employeeModel.setNic(employee.getNic());
            }

            employeeRepository.save(employeeModel);

        } else {
            throw new api.project.Exception.ObjectNotFoundException("Employee info not found");
        }
    }


    public void deleteEmployee(int id) throws api.project.Exception.ObjectNotFoundException {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }
        else{
            throw new api.project.Exception.ObjectNotFoundException("Id is not found");
        }
    }

    public List login(String userName, String password) throws api.project.Exception.ObjectNotFoundException {
        if (employeeRepository.existsByUsername(userName)) {
            EmployeeModel employeeModel = employeeRepository.findByUsername(userName);

            if (password.equals(employeeModel.getPassword())) {
                LoginResponseModel loginResponseModel = new LoginResponseModel();


                loginResponseModel.setUserID(employeeModel.getEmployeeId());
                loginResponseModel.setUsername(employeeModel.getUsername());
                loginResponseModel.setRole(employeeModel.getRole());

                List<LoginResponseModel> login = new ArrayList<>();
                login.add(loginResponseModel);
                return login;
            }
            else {
                throw new api.project.Exception.ObjectNotFoundException("password is incorrect");
            }
        } else {
            throw new api.project.Exception.ObjectNotFoundException("username is incorrect");
        }

    }
}

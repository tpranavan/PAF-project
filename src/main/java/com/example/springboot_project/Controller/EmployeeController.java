package com.example.springboot_project.Controller;

import com.example.springboot_project.Interface.ResponseCode;
import com.example.springboot_project.Model.Database.EmployeeModel;
import com.example.springboot_project.Model.Responses.ResponseModel;
import com.example.springboot_project.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register/Employee")
    public ResponseModel AddNewEmployee(@RequestBody EmployeeModel employeeModel) {
        try {
            employeeService.addEmployee(employeeModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    employeeService.addEmployee(employeeModel),
                    "Successfully Added Employee Information",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @PutMapping("/update/Employee")
    public ResponseModel UpdateEmployee(@RequestBody EmployeeModel employeeModel) {
        try {
            employeeService.updateEmployeeDetails(employeeModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Updated Employee Information",
                    null
            );

            return responseModel;
        } catch (api.project.Exception.ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @GetMapping("/getAll/EmployeeInfo")
    public ResponseModel GetAllEmployeeInfo() {
        try {

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    employeeService.getAllEmployees(),
                    "Successfully Got Employee Info",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public ResponseModel DeleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Deleted Employee Info",
                    null
            );
            return responseModel;
        } catch (api.project.Exception.ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
    @GetMapping("/login")
    public ResponseModel GetLoginResponse(@RequestParam("username") String userName, @RequestParam("password") String password) {
        try {
            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    employeeService.login(userName, password),
                    "Successfully login",
                    null
            );

            return responseModel;
        } catch (api.project.Exception.ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}

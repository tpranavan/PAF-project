package com.example.springboot_project.Controller;

import com.example.springboot_project.Interface.ResponseCode;
import com.example.springboot_project.Model.Database.UserModel;
import com.example.springboot_project.Model.Responses.ResponseModel;
import com.example.springboot_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerNewUser")
    public ResponseModel AddNewUser(@RequestBody UserModel userModel) {
        try {
            userService.addUser(userModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    userService.addUser(userModel),
                    "Successfully Add new User",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @PutMapping("/updateUser")
    public ResponseModel UpdateUser(@RequestBody UserModel userModel) {
        try {
            userService.updateUserDetails(userModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Updated User Info",
                    null
            );

            return responseModel;
        } catch (api.project.Exception.ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseModel GetAllUserInfo() {
        try {

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    userService.getAllUsers(),
                    "Successfully Retrieved Users Info",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @DeleteMapping(value = "/deleteUsers/{id}")
    public ResponseModel DeleteUser(@PathVariable int id) {
        try {
            userService.DeleteUser(id);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Deleted User Info",
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

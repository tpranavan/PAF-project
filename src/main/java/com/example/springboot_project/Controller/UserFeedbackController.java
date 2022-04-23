package com.example.springboot_project.Controller;

import com.example.springboot_project.Interface.ResponseCode;
import com.example.springboot_project.Model.Database.UnitPriceModel;
import com.example.springboot_project.Model.Database.UserFeedbackModel;
import com.example.springboot_project.Model.Responses.ResponseModel;
import com.example.springboot_project.Services.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/feedback")
public class UserFeedbackController {

    @Autowired
    UserFeedbackService userFeedbackService;

    @PostMapping("/addNewFeedback")
    public ResponseModel AddFeedback(@RequestBody UserFeedbackModel userFeedbackModel) {
        try {
            userFeedbackService.addFeedback(userFeedbackModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    userFeedbackService.addFeedback(userFeedbackModel),
                    "Successfully Added Feedback",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @PutMapping("/updateFeedback")
    public ResponseModel UpdateFeedback(@RequestBody UserFeedbackModel userFeedbackModel) {
        try {
            userFeedbackService.updateFeedback(userFeedbackModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Updated Feedback Info",
                    null
            );

            return responseModel;
        } catch (api.project.Exception.ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @GetMapping("/getAllFeedback")
    public ResponseModel GetAllFeedback() {
        try {

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    userFeedbackService.getAllFeedback(),
                    "Successfully Retrieved Feedback Info",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @DeleteMapping(value = "/deleteFeedback/{id}")
    public ResponseModel DeleteFeedback(@PathVariable int id) {
        try {
            userFeedbackService.DeleteFeedback(id);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Deleted Feedback Info",
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

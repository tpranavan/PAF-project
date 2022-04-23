package com.example.springboot_project.Controller;

import com.example.springboot_project.Interface.ResponseCode;
import com.example.springboot_project.Model.Database.UnitPriceModel;
import com.example.springboot_project.Model.Responses.ResponseModel;
import com.example.springboot_project.Services.UnitPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/unitPrice")
public class UnitPriceController {

    @Autowired
    private UnitPriceService unitPriceService;

    @PostMapping("/addNewUnitPrice")
    public ResponseModel AddUnitPrice(@RequestBody UnitPriceModel unitPriceModel) {
        try {
            unitPriceService.addUnitPrice(unitPriceModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    unitPriceService.addUnitPrice(unitPriceModel),
                    "Successfully Add new Unit Price",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @PutMapping("/updateUnitPrice")
    public ResponseModel UpdateUnitPrice(@RequestBody UnitPriceModel unitPriceModel) {
        try {
            unitPriceService.updateUnitPriceDetails(unitPriceModel);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Updated Unit Price Info",
                    null
            );

            return responseModel;
        } catch (api.project.Exception.ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @GetMapping("/getAllUnitPrice")
    public ResponseModel GetAllUnitPriceInfo() {
        try {

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    unitPriceService.getAllUnitPrice(),
                    "Successfully Retrieved UnitPrice Info",
                    null
            );

            return responseModel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @DeleteMapping(value = "/deleteUnitPrice/{id}")
    public ResponseModel DeleteUser(@PathVariable int id) {
        try {
            unitPriceService.DeleteUnitPrice(id);

            ResponseModel responseModel = new ResponseModel(
                    ResponseCode.OK,
                    null,
                    "Successfully Deleted Unit Price Info",
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

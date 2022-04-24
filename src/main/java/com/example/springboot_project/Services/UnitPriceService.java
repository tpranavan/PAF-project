package com.example.springboot_project.Services;

import com.example.springboot_project.Model.Database.UnitPriceModel;
import com.example.springboot_project.Model.Database.UserModel;
import com.example.springboot_project.Repository.UnitPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitPriceService {

    @Autowired
    UnitPriceRepository unitPriceRepository;
    public List addUnitPrice (UnitPriceModel unitPriceModel ){
        unitPriceRepository.save(unitPriceModel);

        List User = new ArrayList();
        User.add(unitPriceModel);

        return User;
    }
    public List getAllUnitPrice() {
        List<UnitPriceModel> unitPriceModels = unitPriceRepository.findAll();

        return unitPriceModels;
    }
    public void updateUnitPriceDetails(UnitPriceModel unitPriceModel) throws api.project.Exception.ObjectNotFoundException {
        if (unitPriceRepository.existsById(unitPriceModel.getDistrictId())) {
            UnitPriceModel unitPriceModel1 = unitPriceRepository.findByDistrictId(unitPriceModel.getDistrictId());

            if (unitPriceModel.getDistrict() != null && unitPriceModel.getDistrict().length() > 0) {
                unitPriceModel1.setDistrict(unitPriceModel.getDistrict());
            }
            if (unitPriceModel.getUnitPrice() != 0 ) {
                unitPriceModel1.setUnitPrice(unitPriceModel.getUnitPrice());
            }
        } else {
            throw new api.project.Exception.ObjectNotFoundException("Unit Price info not found");
        }
    }


    public void DeleteUnitPrice(int id) throws api.project.Exception.ObjectNotFoundException {
        if(unitPriceRepository.existsById(id)){
            unitPriceRepository.deleteById(id);
        }
        else{
            throw new api.project.Exception.ObjectNotFoundException("Id is not found");
        }
    }
}

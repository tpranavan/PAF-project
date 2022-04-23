package com.example.springboot_project.Services;

import com.example.springboot_project.Model.Database.UnitPriceModel;
import com.example.springboot_project.Model.Database.UserFeedbackModel;
import com.example.springboot_project.Repository.UnitPriceRepository;
import com.example.springboot_project.Repository.UserFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserFeedbackService {
    @Autowired
    UserFeedbackRepository userFeedbackRepository;

    public List addFeedback (UserFeedbackModel userFeedbackModel){
        userFeedbackRepository.save(userFeedbackModel);

        List feedback = new ArrayList();
        feedback.add(userFeedbackModel);

        return feedback;
    }
    public List getAllFeedback() {
        List<UserFeedbackModel> userFeedbackModels = userFeedbackRepository.findAll();

        return userFeedbackModels;
    }
    public void updateFeedback(UserFeedbackModel userFeedbackModel) throws api.project.Exception.ObjectNotFoundException {
        if (userFeedbackRepository.existsById(userFeedbackModel.getFeedbackId())) {
            UserFeedbackModel userFeedbackModel1 = userFeedbackRepository.findByFeedbackId(userFeedbackModel.getFeedbackId());

            if (userFeedbackModel.getUserName() != null && userFeedbackModel.getUserName().length() > 0) {
                userFeedbackModel1.setUserName(userFeedbackModel.getUserName());
            }
            if (userFeedbackModel.getEmail() != null && userFeedbackModel.getEmail().length() > 0) {
                userFeedbackModel1.setEmail(userFeedbackModel.getEmail());
            }
            if (userFeedbackModel.getFeedback() != null && userFeedbackModel.getFeedback().length() > 0) {
                userFeedbackModel1.setFeedback(userFeedbackModel.getFeedback());
            }
            userFeedbackRepository.save(userFeedbackModel1);

        } else {
            throw new api.project.Exception.ObjectNotFoundException("Feedback info not found");
        }
    }


    public void DeleteFeedback(int id) throws api.project.Exception.ObjectNotFoundException {
        if(userFeedbackRepository.existsById(id)){
            userFeedbackRepository.deleteById(id);
        }
        else{
            throw new api.project.Exception.ObjectNotFoundException("Id is not found");
        }
    }
}

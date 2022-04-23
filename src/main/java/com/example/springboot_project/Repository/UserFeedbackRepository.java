package com.example.springboot_project.Repository;

import com.example.springboot_project.Model.Database.UserFeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedbackModel, Integer> {

    UserFeedbackModel findByFeedbackId(int id);
}

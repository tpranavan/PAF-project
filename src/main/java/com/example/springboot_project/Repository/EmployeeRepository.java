package com.example.springboot_project.Repository;

import com.example.springboot_project.Model.Database.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel,Integer> {

    EmployeeModel findByEmployeeId(int id);

    EmployeeModel findByUsername(String name);

    boolean existsByUsername(String name);
}

package com.companies.enterprise.services;

import com.companies.enterprise.entities.Department;
import com.companies.enterprise.repositories.DepartmentRepository;
import com.companies.enterprise.validation.RequestEmployee;
import com.companies.enterprise.validation.RequestProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void validateDepartment(RequestProject data){
        Optional < Department> optionalDepartment = departmentRepository.findById(data.department_id());
        if(optionalDepartment.isEmpty()){
            throw new IllegalArgumentException("Departamento não encontrado");
        }
    }

}

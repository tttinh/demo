package com.example.demo.service;

import com.example.demo.domain.entity.Department;
import com.example.demo.domain.request.CreateDepartmentRequest;
import com.example.demo.domain.response.DepartmentResponse;
import com.example.demo.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentResponse findDepartment(Long id) {
        return departmentRepository.findById(id)
            .map(department -> {
                var response = new DepartmentResponse();
                response.setId(department.getId());
                response.setName(department.getName());
                return response;
            })
            .orElseGet(() -> new DepartmentResponse());
    }

    public void createDepartment(CreateDepartmentRequest request) {
        var department = new Department();
        department.setName(request.getName());

        departmentRepository.save(department);
    }
}

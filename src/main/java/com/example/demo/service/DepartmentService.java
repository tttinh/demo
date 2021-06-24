package com.example.demo.service;

import com.example.demo.domain.entity.Department;
import com.example.demo.domain.request.DepartmentCreateRequest;
import com.example.demo.domain.request.DepartmentUpdateRequest;
import com.example.demo.domain.response.DepartmentResponse;
import com.example.demo.domain.response.DepartmentResponseAsPage;
import com.example.demo.repository.DepartmentRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentResponse getOne(Long id) {
        return departmentRepository.findById(id)
            .map(department -> {
                var response = new DepartmentResponse();
                response.setId(department.getId());
                response.setName(department.getName());
                return response;
            })
            .orElseGet(DepartmentResponse::new);
    }

    public DepartmentResponseAsPage getAll(int page, int size) {
//        // The old way.
//        var items = departmentRepository.findAll();
//        var list = new ArrayList<DepartmentResponse>();
//        for (var item : items) {
//            var res = new DepartmentResponse();
//            res.setId(item.getId());
//            res.setName(item.setName());
//            list.add(res);
//        }
//
//        return list;
        var pageable = PageRequest.of(page, size);
        var departmentPage =  departmentRepository.findAll(pageable);
        return DepartmentResponseAsPage.of(departmentPage);
    }

    public void createDepartment(DepartmentCreateRequest request) {
        var department = new Department();
        department.setName(request.getName());

        departmentRepository.save(department);
    }

    public void updateDepartment(DepartmentUpdateRequest request) {
        departmentRepository.findById(request.getId())
            .ifPresent(department -> {
                department.setName(request.getName());
                departmentRepository.save(department);
            });
    }

    public void deleteDepartment(Long id) {
        departmentRepository.findById(id)
            .ifPresent(departmentRepository::delete);
    }
}

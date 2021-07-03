package com.example.demo.service;

import com.example.demo.domain.entity.Department;
import com.example.demo.domain.request.DepartmentCreateRequest;
import com.example.demo.domain.request.DepartmentUpdateRequest;
import com.example.demo.domain.response.DepartmentResponse;
import com.example.demo.domain.response.DepartmentResponseAsPage;
import com.example.demo.domain.response.DoctorResponseAsPage;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;


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

    public DoctorResponseAsPage getDoctors(Long departmentId, int page, int size) {
        var pageable = PageRequest.of(page, size);
        var doctorPage =  doctorRepository.findByDepartmentId(departmentId, pageable);
        return DoctorResponseAsPage.of(doctorPage);
    }
}

package com.example.demo.service;

import com.example.demo.common.ErrorCode;
import com.example.demo.domain.entity.Department;
import com.example.demo.domain.entity.Doctor;
import com.example.demo.domain.request.DepartmentCreateRequest;
import com.example.demo.domain.request.DepartmentUpdateRequest;
import com.example.demo.domain.request.DoctorCreateRequest;
import com.example.demo.domain.request.DoctorUpdateRequest;
import com.example.demo.domain.response.*;
import com.example.demo.domain.response.DoctorResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
    
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;


    public DoctorResponse getOne(Long id) {
        return doctorRepository.findById(id)
            .map(doctor -> {
                var department = new DepartmentResponse();
                department.setId(doctor.getDepartment().getId());
                department.setName(doctor.getDepartment().getName());

                var response = new DoctorResponse();
                response.setId(doctor.getId());
                response.setName(doctor.getName());
                response.setMobile(doctor.getMobile());
                response.setDepartment(department);

                return response;
            })
            .orElseGet(DoctorResponse::new);
    }

    public DoctorResponseAsPage getAll(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var doctorPage =  doctorRepository.findAll(pageable);
        return DoctorResponseAsPage.of(doctorPage);
    }

    public void createDoctor(DoctorCreateRequest request) {
        var department = departmentRepository.findById(request.getDepartmentId())
            .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_DEPARTMENT_ID));

        var doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setMobile(request.getMobile());
        doctor.setDepartment(department);

        doctorRepository.save(doctor);
    }

    public void updateDoctor(DoctorUpdateRequest request) {
        doctorRepository.findById(request.getId())
            .ifPresentOrElse(doctor -> {
                doctor.setName(request.getName());
                doctor.setMobile(request.getMobile());

                if (request.getDepartmentId() != null && doctor.getDepartment().getId() != request.getDepartmentId()) {
                    var department = departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() -> new BadRequestException(ErrorCode.INVALID_DEPARTMENT_ID));

                    doctor.setDepartment(department);
                }

                doctorRepository.save(doctor);
            }, () -> { throw new BadRequestException(ErrorCode.INVALID_DOCTOR_ID); });
    }

    public void deleteDoctor(Long id) {
        doctorRepository.findById(id)
            .ifPresent(doctorRepository::delete);
    }

}

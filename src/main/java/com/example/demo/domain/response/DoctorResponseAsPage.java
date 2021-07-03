package com.example.demo.domain.response;

import com.example.demo.domain.entity.Doctor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DoctorResponseAsPage {

    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<DoctorResponse> items;

    public static DoctorResponseAsPage of(Page<Doctor> page) {
        var items = page.stream()
            .map(doctor -> {
                var departmentResponse = new DepartmentResponse();
                departmentResponse.setId(doctor.getDepartment().getId());
                departmentResponse.setName(doctor.getDepartment().getName());

                var response = new DoctorResponse();
                response.setId(doctor.getId());
                response.setName(doctor.getName());
                response.setMobile(doctor.getMobile());
                response.setDepartment(departmentResponse);

                return response;
            })
            .collect(Collectors.toList());

        var response = new DoctorResponseAsPage();
        response.setTotalItems(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setPage(page.getNumber());
        response.setSize(page.getNumberOfElements());
        response.setItems(items);

        return response;
    }
    
}

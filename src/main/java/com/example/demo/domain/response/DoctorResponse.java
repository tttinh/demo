package com.example.demo.domain.response;

import lombok.Data;

@Data
public class DoctorResponse {

    private Long id;
    private String name;
    private String mobile;
    private DepartmentResponse department;
    
}

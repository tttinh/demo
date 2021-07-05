package com.example.demo.domain.request;

import lombok.Data;

@Data
public class DoctorCreateRequest {

    private Long departmentId;
    private String name;
    private String mobile;

}

package com.example.demo.domain.request;

import lombok.Data;

@Data
public class DoctorUpdateRequest {

    private Long id;
    private Long departmentId;
    private String name;
    private String mobile;

}

package com.example.demo.domain.response;

import com.example.demo.domain.entity.Department;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DepartmentResponseAsPage {

    private long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private List<DepartmentResponse> items;

    public static DepartmentResponseAsPage of(Page<Department> departmentPage) {
        var items = departmentPage.stream()
            .map(department -> {
                var response = new DepartmentResponse();
                response.setId(department.getId());
                response.setName(department.getName());
                return response;
            })
            .collect(Collectors.toList());

        var response = new DepartmentResponseAsPage();
        response.setTotalItems(departmentPage.getTotalElements());
        response.setTotalPages(departmentPage.getTotalPages());
        response.setPage(departmentPage.getNumber());
        response.setSize(departmentPage.getNumberOfElements());
        response.setItems(items);

        return response;
    }
    
}

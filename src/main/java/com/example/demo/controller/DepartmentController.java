package com.example.demo.controller;

import com.example.demo.domain.entity.Department;
import com.example.demo.domain.request.CreateDepartmentRequest;
import com.example.demo.domain.response.DepartmentResponse;
import com.example.demo.service.DepartmentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping("/departments/{id}")
	public DepartmentResponse getDepartment(@PathVariable Long id) {
		return departmentService.findDepartment(id);
	}

	@PostMapping("/departments")
	public void createDepartment(@RequestBody CreateDepartmentRequest request) {
		log.info("a: {}", request.toString());
		departmentService.createDepartment(request);
	}
}
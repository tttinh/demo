package com.example.demo.controller;

import com.example.demo.domain.request.DepartmentCreateRequest;
import com.example.demo.domain.request.DepartmentUpdateRequest;
import com.example.demo.domain.response.DepartmentResponse;
import com.example.demo.domain.response.DepartmentResponseAsPage;
import com.example.demo.domain.response.DoctorResponseAsPage;
import com.example.demo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping("/departments/{id}")
	public DepartmentResponse getOne(@PathVariable Long id) {
		return departmentService.getOne(id);
	}

	@GetMapping("/departments")
	public DepartmentResponseAsPage getAll(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		return departmentService.getAll(page, size);
	}

	@PostMapping("/departments")
	public void createDepartment(@RequestBody DepartmentCreateRequest request) {
		departmentService.createDepartment(request);
	}

	@PutMapping("/departments")
	public void updateDepartment(@RequestBody DepartmentUpdateRequest request) {
		departmentService.updateDepartment(request);
	}

	@DeleteMapping("/departments/{id}")
	public void deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
	}

	@GetMapping("/departments/{id}/doctors")
	public DoctorResponseAsPage getDoctors(
		@PathVariable Long id,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		return departmentService.getDoctors(id, page, size);
	}
}
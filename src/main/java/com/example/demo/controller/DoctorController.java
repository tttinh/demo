package com.example.demo.controller;

import com.example.demo.domain.request.DepartmentCreateRequest;
import com.example.demo.domain.request.DepartmentUpdateRequest;
import com.example.demo.domain.request.DoctorCreateRequest;
import com.example.demo.domain.request.DoctorUpdateRequest;
import com.example.demo.domain.response.DoctorResponse;
import com.example.demo.domain.response.DoctorResponseAsPage;
import com.example.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;

	@GetMapping("/doctors/{id}")
	public DoctorResponse getOne(@PathVariable Long id) {
		return doctorService.getOne(id);
	}

	@GetMapping("/doctors")
	public DoctorResponseAsPage getAll(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		return doctorService.getAll(page, size);
	}

	@PostMapping("/doctors")
	public void createDoctor(@RequestBody DoctorCreateRequest request) {
		doctorService.createDoctor(request);
	}

	@PutMapping("/doctors")
	public void updateDoctor(@RequestBody DoctorUpdateRequest request) {
		doctorService.updateDoctor(request);
	}

	@DeleteMapping("/doctors/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
	}

}
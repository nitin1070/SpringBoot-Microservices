package in.main.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import in.main.employeeservice.dto.DepartmentDto;


@FeignClient(name="Department-Service")
	public interface APIClient {

		@GetMapping("/api/departments/department/{department-code}")
		public DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
		
	
	}

	




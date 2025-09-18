package in.main.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import in.main.employeeservice.dto.DepartmentDto;


@FeignClient(name="API-Gateway", url="http://localhost:9000")
	public interface APIClient {
           //  Now the communication between emp service to department service via API Gateway 
		@GetMapping("/department-service/api/departments/department/{department-code}")
		public DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
		
	
	}

	




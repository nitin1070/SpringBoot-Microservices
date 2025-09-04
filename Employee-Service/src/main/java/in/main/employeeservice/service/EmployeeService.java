package in.main.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.main.employeeservice.dto.APIResponseDto;
import in.main.employeeservice.dto.DepartmentDto;
import in.main.employeeservice.dto.EmployeeDto;
import in.main.employeeservice.entity.Employee;
import in.main.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
@Autowired
private EmployeeRepository eRepo;
@Autowired
private RestTemplate restTemplate;

	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
	Employee employee = new Employee(
	employeeDto.getId(),
	employeeDto.getFirstName(),
	employeeDto.getLastName(),
	employeeDto.getEmail(),
	employeeDto.getDepartmentCode()

	);
	Employee savedEmployee = eRepo.save(employee);
	EmployeeDto savedEmployeeDto = new EmployeeDto(
	savedEmployee.getId(),
	savedEmployee.getFirstName(),
	savedEmployee.getLastName(),
	savedEmployee.getEmail(),
	savedEmployee.getDepartmentCode()
	);
	
	return savedEmployeeDto;
	   }
	
	public APIResponseDto getEmployeeById(Long employeeId) {
		Employee employee = eRepo.findById(employeeId).get();
		EmployeeDto employeeDto = new EmployeeDto(
		employee.getId(),
		employee.getFirstName(),
		employee.getLastName(),
		employee.getEmail(),
		employee.getDepartmentCode()
		); 
		
		ResponseEntity<DepartmentDto>responseEntity = restTemplate.getForEntity("http://localhost:8081/api/departments/department/"+employee.getDepartmentCode(), DepartmentDto.class);
		DepartmentDto departmentDto = responseEntity.getBody();
		APIResponseDto apiResponseDto = new  APIResponseDto();
		 apiResponseDto.setEmployeeDto(employeeDto);
		 apiResponseDto.setDepartmentDto(departmentDto);
			return apiResponseDto;
		}
	
	

	
	}


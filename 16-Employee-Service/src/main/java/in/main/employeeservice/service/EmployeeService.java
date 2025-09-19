package in.main.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import in.main.employeeservice.dto.APIResponseDto;
import in.main.employeeservice.dto.DepartmentDto;
import in.main.employeeservice.dto.EmployeeDto;
import in.main.employeeservice.entity.Employee;
import in.main.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeService {
	
@Autowired
private EmployeeRepository eRepo;
@Autowired
private APIClient apiClient;

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
	@Retry(name="deptRetry",fallbackMethod="getDeptFallback")
	public APIResponseDto getEmployeeById(Long employeeId) {
		Employee employee = eRepo.findById(employeeId).get();
		EmployeeDto employeeDto = new EmployeeDto(
		employee.getId(),
		employee.getFirstName(),
		employee.getLastName(),
		employee.getEmail(),
		employee.getDepartmentCode()
		); 
		
	DepartmentDto departmentDto=apiClient.getDepartment(employee.getDepartmentCode());
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployeeDto(employeeDto);
		apiResponseDto.setDepartmentDto(departmentDto);

		return apiResponseDto;
		}
	
	
	
	public APIResponseDto getDeptFallback(Long employeeId,Exception ex) {
		Employee employee = eRepo.findById(employeeId).get();
		EmployeeDto employeeDto = new EmployeeDto(
		employee.getId(),
		employee.getFirstName(),
		employee.getLastName(),
		employee.getEmail(),
		employee.getDepartmentCode()
		); 
		DepartmentDto departmentDto=new DepartmentDto();
		departmentDto.setId(-1L);
		departmentDto.setDepartmentCode(null);
		departmentDto.setDepartmentName("Dept Not Found");
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployeeDto(employeeDto);
		apiResponseDto.setDepartmentDto(departmentDto);
		    return apiResponseDto;
		}

	
	
		}
	

	



package in.main.departmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.main.departmentservice.dto.DepartmentDto;
import in.main.departmentservice.entity.Department;
import in.main.departmentservice.reposistory.DepartmentReposistory;

@Service
public class DepartmentService {
     @Autowired
	private DepartmentReposistory dRepo;
	
     public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
    	Department department = new Department(departmentDto.getId(), departmentDto.getDepartmentName(),
    	departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());
    	Department savedDepartment = dRepo.save(department);
    	DepartmentDto savedDepartmentDto = new DepartmentDto(savedDepartment.getId(),
    	savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription(),
    	savedDepartment.getDepartmentCode());
    	return savedDepartmentDto;
    		}
     
     
     public DepartmentDto getDepartmentByCode(String departmentCode) {
    	 Department department = dRepo.findByDepartmentCode(departmentCode);
    	 DepartmentDto departmentDto = new DepartmentDto(
    	 department.getId(),
    	 department.getDepartmentName(),
    	 department.getDepartmentDescription(),
    	 department.getDepartmentCode()
    	 );
    	     return departmentDto;
    	 }

}

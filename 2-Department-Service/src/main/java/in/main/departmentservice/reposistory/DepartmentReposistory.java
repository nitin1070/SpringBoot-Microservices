package in.main.departmentservice.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.departmentservice.entity.Department;


public interface DepartmentReposistory extends JpaRepository<Department, Long> {
		Department findByDepartmentCode(String departmentCode);
	

}


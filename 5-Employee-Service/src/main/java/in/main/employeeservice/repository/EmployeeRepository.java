package in.main.employeeservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import in.main.employeeservice.entity.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {
		


}


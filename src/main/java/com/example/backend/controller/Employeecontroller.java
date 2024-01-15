package com.example.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Employee;
import com.example.backend.repository.Employeerepository;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class Employeecontroller {

	@Autowired
	private Employeerepository employeerepository;
	
	
	//get all mployees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeerepository.findAll();
	}
	
	//create employee rest api
	@PostMapping("/")
	public Employee createemployee(@RequestBody Employee employee)
	{
		return employeerepository.save(employee);
	}
	
	//get employeee byl̥ id rest APi
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	    Optional<Employee> optionalEmployee = employeerepository.findById(id);

	    if (optionalEmployee.isPresent()) {
	        Employee employee = optionalEmployee.get();
	        return ResponseEntity.ok(employee);
	    } else {
	    	// String errorMessage = "Employee with ID " + id + " not found";
	        // return (ResponseEntity<Employee>) ResponseEntity.status(HttpStatus.NOT_FOUND);
	         return ResponseEntity.notFound().build();
	    
	    }
	}
	
	//update employee rest api
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateemployee(@PathVariable Long id, @RequestBody Employee employeenewdetails)
	{
		Optional<Employee> optionalEmployee = employeerepository.findById(id);
		if(optionalEmployee.isPresent()) {
			Employee employee=optionalEmployee.get();
			employee.setFirstname(employeenewdetails.getFirstname());
			employee.setLastname(employeenewdetails.getLastname());
			employee.setEmailid(employeenewdetails.getEmailid());
			Employee updatedemployee=employeerepository.save(employee);
			return ResponseEntity.ok(updatedemployee);
		}else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	//delete employee rest api
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Optional<Employee> optionalEmployee = employeerepository.findById(id);
		Map<String,Boolean> response=new HashMap<>();
		if(optionalEmployee.isPresent()) {
			Employee employee=optionalEmployee.get();
			employeerepository.delete(employee);
			 response.put("deleted",Boolean.TRUE);
			return ResponseEntity.ok(response);
		}else {
		 response.put("Not deleted",Boolean.FALSE);
		 return ResponseEntity.ok(response);
		}
		}
	}



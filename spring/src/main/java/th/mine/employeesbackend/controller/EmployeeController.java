package th.mine.employeesbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.mine.employeesbackend.exception.EntityNotFoundException;
import th.mine.employeesbackend.model.Employee;
import th.mine.employeesbackend.repository.IEmployeeRepository;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class EmployeeController {
    /**
     * NO DTO AND SERVICE LAYER ARE IMPLEMENTED
     */
    @Autowired
    private IEmployeeRepository employeeRepository;

    //get all employees
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // create employee rest api
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id rest api
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EntityNotFoundException {
         Employee employee = employeeRepository.findById(id).orElseThrow(
                 () -> new EntityNotFoundException("Employee not exist with id:" + id));
         return ResponseEntity.ok(employee);
        }

    // update employee rest api
    @RequestMapping(value = ("/employees/{id}"), method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDto) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not exist with id:" + id));

        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setEmailId(employeeDto.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @RequestMapping (value = ("employees/{id}"), method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws EntityNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not exist with id:" + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

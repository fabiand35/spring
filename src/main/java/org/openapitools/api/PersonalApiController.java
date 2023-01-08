package org.openapitools.api;

import com.sun.xml.bind.v2.TODO;
import org.openapitools.model.Assignment;
import org.openapitools.model.Employee;
import org.openapitools.model.Error;
import org.openapitools.model.PersonalAssignmentsGet200Response;
import org.openapitools.model.PersonalEmployeesGet200Response;
import org.openapitools.model.PersonalStatusGet200Response;

import java.util.*;


import org.openapitools.repository.AssignmentRepository;
import org.openapitools.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.validation.Valid;

import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-25T15:05:03.794851Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.biletadoServices.base-path:/}")
public class PersonalApiController implements PersonalApi {

    private final NativeWebRequest request;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    public PersonalApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<PersonalAssignmentsGet200Response> personalAssignmentsGet(UUID employeeId) {
        List<Assignment> assignments = new ArrayList<>();
        Iterable<Assignment> assignmentsIterable = assignmentRepository.findAll();
        for (Assignment loop: assignmentsIterable){
            if (loop.getEmployeeId().equals(employeeId)){
                assignments.add(loop);
            }
        }
        PersonalAssignmentsGet200Response response = new PersonalAssignmentsGet200Response();
        response.assignments(assignments);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> personalAssignmentsIdDelete(UUID id) {
        try{
            if (assignmentRepository.findById(id).isPresent()) {
                assignmentRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            // TODO authentification 401
            // TODO error
        }
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsIdGet(UUID id) {
        try {
            if (assignmentRepository.findById(id).isPresent()) {
                return ResponseEntity.ok(assignmentRepository.findById(id).get());
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> personalAssignmentsIdPut(UUID id, Assignment assignment) {
        try {
            if (!id.equals(assignment.getId())) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else{
                if (assignmentRepository.findById(id).isPresent()) {
                    assignmentRepository.save(assignment);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //TODO 400, 401
        }
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsPost(Assignment assignment) {
        try {
            if (assignmentRepository.findById(assignment.getId()).isPresent()) {
                assignmentRepository.save(assignment);
                return ResponseEntity.ok(assignment);
            }
            else {
                return new ResponseEntity<>(assignmentRepository.save(assignment), HttpStatus.CREATED);

            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //TODO 400, 401
        }
    }

    @Override
    public ResponseEntity<PersonalEmployeesGet200Response> personalEmployeesGet() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        PersonalEmployeesGet200Response response = new PersonalEmployeesGet200Response();
        response.employees(employees);
        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdDelete(UUID id) {
        try{
            if (employeeRepository.findById(id).isPresent()) {
                    employeeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            // TODO assignment 422, authentification 401
            // TODO error
        }
    }

    @Override
    public ResponseEntity<Employee> personalEmployeesIdGet(UUID id) {
        try {
            if (employeeRepository.findById(id).isPresent()) {
                return ResponseEntity.ok(employeeRepository.findById(id).get());
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> personalEmployeesIdPut(UUID id, Employee employee) {
        try {
            if (!id.equals(employee.getId())) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else{
                if (employeeRepository.findById(id).isPresent()) {
                    employeeRepository.save(employee);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //TODO 400, 401
        }
    }

    @Override
    public ResponseEntity<Employee> personalEmployeesPost(Employee employee) {
        try {
            if (employeeRepository.findById(employee.getId()).isPresent()) {
                employeeRepository.save(employee);
                return ResponseEntity.ok(employee);
            }
            else {
                return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);

            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //TODO 400, 401
        }
    }
}

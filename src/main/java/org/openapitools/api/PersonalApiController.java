package org.openapitools.api;

import com.sun.xml.bind.v2.TODO;
import org.hibernate.resource.beans.container.spi.BeanLifecycleStrategy;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.validation.Valid;

import javax.annotation.Generated;

import org.springframework.web.client.RestTemplate;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-25T15:05:03.794851Z[Etc/UTC]")
@Controller
public class PersonalApiController implements PersonalApi {

    private final NativeWebRequest request;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    RestTemplate restTemplate;

    @Value("${BACKEND_URL}")
    private String url;


    @Autowired
    public PersonalApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<PersonalAssignmentsGet200Response> personalAssignmentsGet(UUID employeeId, UUID reservationId) {
        List<Assignment> assignments = new ArrayList<>();
        Iterable<Assignment> assignmentsIterable = assignmentRepository.findAll();
        if (reservationId == null){
            for (Assignment loop: assignmentsIterable){
                if (loop.getEmployeeId().equals(employeeId)) {
                    assignments.add(loop);
                }
            }
        }
        else if(employeeId == null) {
            for (Assignment loop: assignmentsIterable){
                if (loop.getReservationId().equals(reservationId)) {
                    assignments.add(loop);
                }
            }
        }
        else if (employeeId != null && reservationId != null) {
            for (Assignment loop: assignmentsIterable){
                if (loop.getEmployeeId().equals(employeeId) && loop.getReservationId().equals(reservationId)) {
                    assignments.add(loop);
                }
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
            //if the reservation already has an assignment with the given role
            Iterable<Assignment> assignmentList = assignmentRepository.findAll();
            for(Assignment a: assignmentList) {
                if (a.getReservationId().equals(assignment.getReservationId()) && a.getRole().equals(assignment.getRole()) && a.getId()!=(assignment.getId())) {
                    getRequest().ifPresent(req ->
                    {
                        ApiUtil.setErrorResponse(req, "reservation has an assignment with the given role");
                    });
                    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
            //mismatching id in url and object
            if (!id.equals(assignment.getId())) {
                getRequest().ifPresent(req ->
                {
                    ApiUtil.setErrorResponse(req, "mismatching id in url and object");
                });
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else{
                if (assignmentRepository.findById(id).isPresent() == true) {
                    //check if there is an reservation with this id if not -> HttpServerErrorException
                    UUID reservationId = assignment.getReservationId();
                    ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url + reservationId.toString() + "/", Object.class);
                    Object object = responseEntity.getBody();

                    if(object != null) {
                        assignmentRepository.save(assignment);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                    else{
                        getRequest().ifPresent(req ->
                        {
                            ApiUtil.setErrorResponse(req, "reservation does not exist");
                        });
                        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
                else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }
        catch (DataIntegrityViolationException ed) {
            getRequest().ifPresent(req ->
            {
                ApiUtil.setErrorResponse(req, "employee does not exist ");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (HttpServerErrorException x) {

            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Assignment> personalAssignmentsPost(Assignment assignment) {
        try {
            //if the reservation already has an assignment with the given role
            Iterable<Assignment> assignmentList = assignmentRepository.findAll();
            for(Assignment a: assignmentList){
                if(a.getReservationId().equals(assignment.getReservationId()) && a.getRole().equals(assignment.getRole())){
                    getRequest().ifPresent(req ->
                    {
                        ApiUtil.setErrorResponse(req, "reservation has an assignment with the given role");
                    });
                    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
            UUID reservationId = assignment.getReservationId();
            if (assignmentRepository.findById(assignment.getId()).isPresent() == true) {
                ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url + reservationId.toString() + "/", Object.class);
                Object object = responseEntity.getBody();
                if(object != null) {
                    //Successful operation of updating an existing assignment. This can only happen if a uuid gets passed.
                    assignmentRepository.save(assignment);
                    return ResponseEntity.ok(assignment);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
            else {
                ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url + reservationId.toString() + "/", Object.class);
                Object object = responseEntity.getBody();
                if(object != null) {
                    //successful operation of creating a new assignment
                    return new ResponseEntity<>(assignmentRepository.save(assignment), HttpStatus.CREATED);
                }
                else{
                    getRequest().ifPresent(req ->
                    {
                        ApiUtil.setErrorResponse(req, "reservation does not exist");
                    });
                    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
        }
        catch (DataIntegrityViolationException ed) {
            getRequest().ifPresent(req ->
            {
                ApiUtil.setErrorResponse(req, "employee does not exist ");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (HttpServerErrorException x) {
            getRequest().ifPresent(req ->
            {
                ApiUtil.setErrorResponse(req, "reservation does not exist");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
            getRequest().ifPresent(req ->
            {
                ApiUtil.setErrorResponse(req, "deletion not possible because of existing assignments");
            });
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
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
                getRequest().ifPresent(req ->
                {
                    ApiUtil.setErrorResponse(req, "mismatching id in url and object");
                });
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            else{
                if (employeeRepository.findById(id).isPresent()== true) {
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
            if (employeeRepository.findById(employee.getId()).isPresent() == true) {
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

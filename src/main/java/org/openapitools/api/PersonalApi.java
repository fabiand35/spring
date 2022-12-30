/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.3.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.Assignment;
import org.openapitools.model.Employee;
import org.openapitools.model.Error;
import org.openapitools.model.PersonalAssignmentsGet200Response;
import org.openapitools.model.PersonalEmployeesGet200Response;
import org.openapitools.model.PersonalStatusGet200Response;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-25T15:05:03.794851Z[Etc/UTC]")
@Validated
@Tag(name = "personal", description = "CRUD assignment between employee and reservation")
public interface PersonalApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /personal/assignments/ : get all personal assignments
     *
     * @param employeeId filter for a given employee (optional)
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "personalAssignmentsGet",
        summary = "get all personal assignments",
        tags = { "assignment" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalAssignmentsGet200Response.class))
                    //TODO
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/personal/assignments/",
        produces = { "application/json" }
    )
    default ResponseEntity<PersonalAssignmentsGet200Response> personalAssignmentsGet(
        @Parameter(name = "employee_id", description = "filter for a given employee") @Valid @RequestParam(value = "employee_id", required = false) UUID employeeId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"assignments\" : [ { \"reservation_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"role\" : \"service\", \"employee_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"reservation_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"role\" : \"service\", \"employee_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /personal/assignments/{id}/ : delete an assignment by id
     *
     * @param id uuid of the assignment (required)
     * @return successful operation (status code 204)
     *         or if no (valid) authentication is given (status code 401)
     *         or not found (status code 404)
     */
    @Operation(
        operationId = "personalAssignmentsIdDelete",
        summary = "delete an assignment by id",
        tags = { "assignment" },
        responses = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "401", description = "if no (valid) authentication is given", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            })
        },
        security = {
            @SecurityRequirement(name = "biletado", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/personal/assignments/{id}/",
        produces = { "application/json" }
    )
    default ResponseEntity<Void> personalAssignmentsIdDelete(
        @Parameter(name = "id", description = "uuid of the assignment", required = true) @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /personal/assignments/{id}/ : get an assignment by id
     *
     * @param id uuid of the assignment (required)
     * @return successful operation (status code 200)
     *         or not found (status code 404)
     */
    @Operation(
        operationId = "personalAssignmentsIdGet",
        summary = "get an assignment by id",
        tags = { "assignment" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Assignment.class))
            }),
            @ApiResponse(responseCode = "404", description = "not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/personal/assignments/{id}/",
        produces = { "application/json" }
    )
    default ResponseEntity<Assignment> personalAssignmentsIdGet(
        @Parameter(name = "id", description = "uuid of the assignment", required = true) @PathVariable("id") UUID id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"reservation_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"role\" : \"service\", \"employee_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /personal/assignments/{id}/ : add or update an assignment by id
     * if an id is supplied in the object, it MUST mach with the one in the url
     *
     * @param id uuid of the assignment (required)
     * @param assignment  (required)
     * @return successful operation (status code 204)
     *         or invalid input (status code 400)
     *         or if no (valid) authentication is given (status code 401)
     *         or if the reservation already has an assignment with the given role or the employee does not exist or the reservation does not exist or mismatching id in url and object  (status code 422)
     */
    @Operation(
        operationId = "personalAssignmentsIdPut",
        summary = "add or update an assignment by id",
        tags = { "assignment" },
        responses = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "invalid input", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            }),
            @ApiResponse(responseCode = "401", description = "if no (valid) authentication is given", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            }),
            @ApiResponse(responseCode = "422", description = "if the reservation already has an assignment with the given role or the employee does not exist or the reservation does not exist or mismatching id in url and object ", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO

            })
        },
        security = {
            @SecurityRequirement(name = "biletado", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/personal/assignments/{id}/",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> personalAssignmentsIdPut(
        @Parameter(name = "id", description = "uuid of the assignment", required = true) @PathVariable("id") UUID id,
        @Parameter(name = "Assignment", description = "", required = true) @Valid @RequestBody Assignment assignment
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /personal/assignments/ : add a new assignment
     * MAY contain a uuid. If so, this method does the same checks as &#x60;PUT&#x60; does.
     *
     * @param assignment  (required)
     * @return Successful operation of updating an existing assignment. This can only happen if a uuid gets passed.  (status code 200)
     *         or successful operation of creating a new assignment (status code 201)
     *         or invalid input (status code 400)
     *         or if no (valid) authentication is given (status code 401)
     *         or if the reservation already has an assignment with the given role or the employee does not exist or the reservation does not exist  (status code 422)
     */
    @Operation(
        operationId = "personalAssignmentsPost",
        summary = "add a new assignment",
        tags = { "assignment" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation of updating an existing assignment. This can only happen if a uuid gets passed. ", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Assignment.class))
                    //TODO
            }),
            @ApiResponse(responseCode = "201", description = "successful operation of creating a new assignment", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Assignment.class))
                    //TODO
            }),
            @ApiResponse(responseCode = "400", description = "invalid input", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            }),
            @ApiResponse(responseCode = "401", description = "if no (valid) authentication is given", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            }),
            @ApiResponse(responseCode = "422", description = "if the reservation already has an assignment with the given role or the employee does not exist or the reservation does not exist ", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    //TODO
            })
        },
        security = {
            @SecurityRequirement(name = "biletado", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/personal/assignments/",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Assignment> personalAssignmentsPost(
        @Parameter(name = "Assignment", description = "", required = true) @Valid @RequestBody Assignment assignment
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"reservation_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"role\" : \"service\", \"employee_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /personal/employees/ : get all employees
     *
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "personalEmployeesGet",
        summary = "get all employees",
        tags = { "employee" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalEmployeesGet200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/personal/employees/",
        produces = { "application/json" }
    )
    default ResponseEntity<PersonalEmployeesGet200Response> personalEmployeesGet(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"employees\" : [ { \"name\" : \"Max Specimeno\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"name\" : \"Max Specimeno\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /personal/employees/{id}/ : delete an employee by id
     *
     * @param id uuid of the employee (required)
     * @return successful operation (status code 204)
     *         or if no (valid) authentication is given (status code 401)
     *         or not found (status code 404)
     *         or deletion not possible because of existing assignments (status code 422)
     */
    @Operation(
        operationId = "personalEmployeesIdDelete",
        summary = "delete an employee by id",
        tags = { "employee" },
        responses = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "401", description = "if no (valid) authentication is given", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "422", description = "deletion not possible because of existing assignments", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        },
        security = {
            @SecurityRequirement(name = "biletado", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/personal/employees/{id}/",
        produces = { "application/json" }
    )
    default ResponseEntity<Void> personalEmployeesIdDelete(
        @Parameter(name = "id", description = "uuid of the employee", required = true) @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /personal/employees/{id}/ : get an employee by id
     *
     * @param id uuid of the employee (required)
     * @return successful operation (status code 200)
     *         or not found (status code 404)
     */
    @Operation(
        operationId = "personalEmployeesIdGet",
        summary = "get an employee by id",
        tags = { "employee" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
            }),
            @ApiResponse(responseCode = "404", description = "not found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/personal/employees/{id}/",
        produces = { "application/json" }
    )
    default ResponseEntity<Employee> personalEmployeesIdGet(
        @Parameter(name = "id", description = "uuid of the employee", required = true) @PathVariable("id") UUID id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Max Specimeno\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /personal/employees/{id}/ : add or update an employee by id
     * if an id is supplied in the object, it MUST mach with the one in the url
     *
     * @param id uuid of the employee (required)
     * @param employee  (required)
     * @return successful operation (status code 204)
     *         or invalid input (status code 400)
     *         or if no (valid) authentication is given (status code 401)
     *         or mismatching id in url and object (status code 422)
     */
    @Operation(
        operationId = "personalEmployeesIdPut",
        summary = "add or update an employee by id",
        tags = { "employee" },
        responses = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "invalid input", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "if no (valid) authentication is given", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "422", description = "mismatching id in url and object", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        },
        security = {
            @SecurityRequirement(name = "biletado", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/personal/employees/{id}/",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> personalEmployeesIdPut(
        @Parameter(name = "id", description = "uuid of the employee", required = true) @PathVariable("id") UUID id,
        @Parameter(name = "Employee", description = "", required = true) @Valid @RequestBody Employee employee
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /personal/employees/ : add a new employee
     * MAY contain a uuid. If so, this method does the same checks as &#x60;PUT&#x60; does.
     *
     * @param employee  (required)
     * @return Successful operation of updating an existing employee. This can only happen if a uuid gets passed.  (status code 200)
     *         or successful operation of creating a new employee (status code 201)
     *         or invalid input (status code 400)
     *         or if no (valid) authentication is given (status code 401)
     */
    @Operation(
        operationId = "personalEmployeesPost",
        summary = "add a new employee",
        tags = { "employee" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation of updating an existing employee. This can only happen if a uuid gets passed. ", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
            }),
            @ApiResponse(responseCode = "201", description = "successful operation of creating a new employee", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
            }),
            @ApiResponse(responseCode = "400", description = "invalid input", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "if no (valid) authentication is given", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        },
        security = {
            @SecurityRequirement(name = "biletado", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/personal/employees/",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Employee> personalEmployeesPost(
        @Parameter(name = "Employee", description = "", required = true) @Valid @RequestBody Employee employee
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"Max Specimeno\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /personal/status/ : returns information about the backend-service and status
     *
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "personalStatusGet",
        summary = "returns information about the backend-service and status",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalStatusGet200Response.class))
                    //TODO
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/personal/status/",
        produces = { "application/json" }
    )
    default ResponseEntity<PersonalStatusGet200Response> personalStatusGet(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"authors\" : [ \"authors\", \"authors\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

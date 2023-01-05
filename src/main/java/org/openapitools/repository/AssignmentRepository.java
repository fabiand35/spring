package org.openapitools.repository;

import org.openapitools.model.Assignment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AssignmentRepository extends CrudRepository<Assignment, UUID>{
}

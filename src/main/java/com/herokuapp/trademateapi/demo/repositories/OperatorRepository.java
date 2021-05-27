package com.herokuapp.trademateapi.demo.repositories;

import com.herokuapp.trademateapi.demo.models.Company;
import com.herokuapp.trademateapi.demo.models.Operator;
import org.springframework.data.repository.CrudRepository;

public interface OperatorRepository extends CrudRepository<Operator, Long> {
    Operator findByNameAndCompany(String name, Company company);
    Operator findByEmail(String email);
}

package com.herokuapp.trademateapi.demo.repositories;

import com.herokuapp.trademateapi.demo.models.Company;
import com.herokuapp.trademateapi.demo.models.Merchandiser;
import org.springframework.data.repository.CrudRepository;

public interface MerchandiserRepository extends CrudRepository<Merchandiser, Long> {
    Merchandiser findByEmail(String email);
    Merchandiser findByAccessToken(String accessToken);
    Merchandiser findByNameAndCompany(String name, Company company);
}

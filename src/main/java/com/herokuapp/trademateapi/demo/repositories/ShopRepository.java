package com.herokuapp.trademateapi.demo.repositories;

import com.herokuapp.trademateapi.demo.models.Company;
import com.herokuapp.trademateapi.demo.models.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends CrudRepository<Shop, Long> {
    List<Shop> findAllByCompanyOrderByName(Company company);
    Shop findByNameAndCompany(String name, Company company);
}

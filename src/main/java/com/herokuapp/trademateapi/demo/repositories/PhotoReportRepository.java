package com.herokuapp.trademateapi.demo.repositories;

import com.herokuapp.trademateapi.demo.models.Merchandiser;
import com.herokuapp.trademateapi.demo.models.PhotoReport;
import org.springframework.data.repository.CrudRepository;

public interface PhotoReportRepository extends CrudRepository<PhotoReport, Long> {
    PhotoReport findByNameAndMerchandiser(String name, Merchandiser merchandiser);
}

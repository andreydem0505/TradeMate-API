package com.herokuapp.trademateapi.demo.repositories;

import com.herokuapp.trademateapi.demo.models.Photo;
import com.herokuapp.trademateapi.demo.models.PhotoReport;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
    Photo findByIdAndPhotoReport(long id, PhotoReport photoReport);
}

package com.herokuapp.trademateapi.demo.repositories;

import com.herokuapp.trademateapi.demo.models.Merchandiser;
import com.herokuapp.trademateapi.demo.models.Request;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findAllByDateTimeBetweenAndMerchandiserOrderByDateTimeDesc(LocalDateTime start, LocalDateTime stop, Merchandiser merchandiser);
    List<Request> findAllByDateTimeBetweenOrderByDateTimeDesc(LocalDateTime start, LocalDateTime stop);
}

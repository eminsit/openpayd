package com.eminsit.openpayd.repository;

import com.eminsit.openpayd.model.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ConversionRepository extends CrudRepository<Conversion, Long> {
    Page<Conversion> findAll(Pageable pageable);
    Page<Conversion> findConversionsByDate(Date date, Pageable pageable);
    Page<Conversion> findConversionsByTransactionId(Long id, Pageable pageable);
    Page<Conversion> findConversionsByDateBetween(Date date1, Date date2, Pageable pageable);

}
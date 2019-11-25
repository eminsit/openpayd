package com.eminsit.openpayd.service;

import com.eminsit.openpayd.model.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;


public interface ConversionService {

    Double convertCurrencyAmount(String base, String target, Double amount);
    Conversion saveConversion(Conversion conversion);
    Conversion getConversion(String base, String target, Double amount);
    Page<Conversion> getAll(Long id, String date, Integer pageNo, Integer pageSize);
}

package com.eminsit.openpayd.service;

import com.eminsit.openpayd.model.Conversion;
import com.eminsit.openpayd.repository.ConversionRepository;
import com.eminsit.openpayd.utils.CustomDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    ConversionRepository conversionRepository;

    @Override
    public Double convertCurrencyAmount(String base, String target, Double amount) {
        Double currencyRate = currencyService.getCurrencyRates(base, target);
        return currencyRate * amount;
    }

    @Override
    public Conversion saveConversion(Conversion conversion) {
        return conversionRepository.save(conversion);
    }

    @Override
    public Conversion getConversion(String base, String target, Double amount) {
        Double value = convertCurrencyAmount(base, target, amount);
        Date currentDay = new Date();
        Conversion conversion = new Conversion(base, target, amount, value, currentDay);

        return saveConversion(conversion);
    }

    @Override
    public Page<Conversion> getAll(Long id, String dateString, Integer pageNo, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        if (id != null) {
            return conversionRepository.findConversionsByTransactionId(id, paging);
        } else if (!dateString.equals("")) {
            try {
                Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
                return conversionRepository.findConversionsByDateBetween(date, CustomDateUtil.getDateOfNextDay(date), paging);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

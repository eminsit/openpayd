package com.eminsit.openpayd.controller;

import com.eminsit.openpayd.model.Conversion;
import com.eminsit.openpayd.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConversionController {

    @Autowired
    ConversionService conversionService;

    @RequestMapping("/conversion")
    public Map<String, String> convertCurrency(
            @RequestParam(name = "base") String base,
            @RequestParam(name = "target") String target,
            @RequestParam(name = "amount") Double amount
    ) {
        Map<String, String> conversionMap = new HashMap<>();
        Conversion conversion = conversionService.getConversion(base, target, amount);

        conversionMap.put("rate", conversion.getConvertedAmount().toString());
        conversionMap.put("transaction_id", conversion.getTransactionId().toString());

        return conversionMap;
    }

    @GetMapping("/conversions")
    public Page<Conversion> listConversions(
            @RequestParam(name = "page_no", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "transaction_id", required = false) Long id,
            @RequestParam(name = "date") String date
    ) {

        return conversionService.getAll(id, date, pageNo, pageSize);
    }

}
package com.eminsit.openpayd.controller;
import com.eminsit.openpayd.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/currency_rates")
    public Map<String, Double> getCurrencyRates(@RequestParam(name = "base") String base, @RequestParam(name = "target") String target) {
        Map<String, Double> rateMap = new HashMap<>();
        rateMap.put("rate", currencyService.getCurrencyRates(base, target));

        return rateMap;
    }

}
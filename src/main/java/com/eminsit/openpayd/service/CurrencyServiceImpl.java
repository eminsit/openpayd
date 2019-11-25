package com.eminsit.openpayd.service;

import com.eminsit.openpayd.model.Currency;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    final String url = "https://api.ratesapi.io/api/";

    @Override
    public Double getCurrencyRates(String base, String target) {

        return getCurrencyRatesfromAPI(base, target);
    }

    private Double getCurrencyRatesfromAPI(String base, String target) {
        String uri = url + "/latest?base=" + base + "&target=" + target;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Currency currency = getRateFromJsonString(result);
        return currency.getRates().get(target);
    }

    private Currency getRateFromJsonString(String currencyRates) {
        Gson gson = new Gson();
        Currency currency = gson.fromJson(currencyRates, Currency.class);

        return currency;
    }
}

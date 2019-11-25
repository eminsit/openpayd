package com.eminsit.openpayd.service;

import com.eminsit.openpayd.model.Conversion;

public interface CurrencyService {

    public Double getCurrencyRates(String base, String target);
}

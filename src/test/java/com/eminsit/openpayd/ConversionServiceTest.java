package com.eminsit.openpayd;

import com.eminsit.openpayd.model.Conversion;
import com.eminsit.openpayd.repository.ConversionRepository;
import com.eminsit.openpayd.service.ConversionService;
import com.eminsit.openpayd.service.ConversionServiceImpl;
import com.eminsit.openpayd.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ConversionServiceTest {

    @Mock
    ConversionRepository conversionRepository;

    @Mock
    CurrencyService currencyService;

    @InjectMocks
    ConversionServiceImpl conversionService;

    @Test
    void getConversionShouldReturnOneConversion() {
        Conversion conversion = new Conversion("TRY", "USD", 12d, 2.211, new Date());
        when(currencyService.getCurrencyRates("TRY", "USD")).thenReturn(2.1);
        when(conversionService.convertCurrencyAmount("TRY", "USD", 12d)).thenReturn(2.211);
        conversionService.getConversion("TRY", "USD", 12d);
        verify(conversionService.saveConversion(conversion), times(1));
    }


}

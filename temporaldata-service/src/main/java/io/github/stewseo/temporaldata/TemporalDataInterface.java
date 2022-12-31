package io.github.stewseo.temporaldata;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;

public interface TemporalDataInterface {

    BreinTemporalDataResult temporalDataResult(String city);

    BreinTemporalDataResult temporalDataResult(String city, String state, String country);

    BreinHolidayResult holidayResult();

    BreinWeatherResult weatherResult();

    BreinLocationResult locationResult();

}

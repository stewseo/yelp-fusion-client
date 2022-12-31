package io.github.stewseo.temporaldata;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;

public interface TemporalDataInterface {

    public BreinTemporalDataResult temporalDataResult(String city);

    public BreinTemporalDataResult temporalDataResult(String city, String state, String country);

    public BreinHolidayResult holidayResult();

    public BreinWeatherResult weatherResult();

    public BreinLocationResult locationResult();

}

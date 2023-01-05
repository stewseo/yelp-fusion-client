package io.github.stewseo.temporaldata.service;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;

import java.util.List;

public interface TemporalDataInterface {

    BreinTemporalDataResult temporalDataResult(String city);

    BreinTemporalDataResult temporalDataResult(String city, String state, String country);

    List<BreinHolidayResult> holidayResult(String city);

    BreinWeatherResult weatherResult(String city);

    BreinLocationResult locationResult(String city);

}

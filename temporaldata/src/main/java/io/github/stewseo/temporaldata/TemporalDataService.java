package io.github.stewseo.temporaldata;

import com.brein.api.BreinTemporalData;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinEventResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class TemporalDataService {


    public BreinTemporalDataResult temporalData(String city, String state, String country) {

        final BreinTemporalDataResult result = new BreinTemporalData()
                .setLocation(city, state, country)
                .execute();

        LocalDateTime field1 = result.getEpochDateTime();

        ZonedDateTime field2 =  result.getLocalDateTime();

        BreinLocationResult field3 =  result.getLocation();

        List<BreinEventResult> field4 =  result.getEvents();

        List<BreinHolidayResult> field5 = result.getHolidays();

        BreinWeatherResult field6 = result.getWeather();

//        return new TemporalData(field1, field2, field3, field4, field5, field6);
        return result;
    }
}

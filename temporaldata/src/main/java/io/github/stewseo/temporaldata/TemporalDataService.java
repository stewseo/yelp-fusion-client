package io.github.stewseo.temporaldata;

import com.brein.api.BreinTemporalData;
import com.brein.api.Breinify;
import com.brein.domain.BreinConfig;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinEventResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;
import com.brein.engine.BreinEngineType;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class TemporalDataService {

    public TemporalDataService() {

    }

    public BreinTemporalDataResult temporalData(String city) {

        BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
                .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

        Breinify.setConfig(config);

        final BreinTemporalDataResult result = new BreinTemporalData()
                .setLocation(city)
                .execute();

        LocalDateTime field1 = result.getEpochDateTime();

        ZonedDateTime field2 =  result.getLocalDateTime();

        BreinLocationResult field3 =  result.getLocation();

        List<BreinEventResult> field4 =  result.getEvents();

        List<BreinHolidayResult> field5 = result.getHolidays();

        BreinWeatherResult field6 = result.getWeather();

        return result;
    }
    public BreinTemporalDataResult temporalData(String city, String state, String country) {

        BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
                .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

        Breinify.setConfig(config);

        final BreinTemporalDataResult result = new BreinTemporalData()
                .setLocation(city, state, country)
                .execute();

        LocalDateTime field1 = result.getEpochDateTime();

        ZonedDateTime field2 =  result.getLocalDateTime();

        BreinLocationResult field3 =  result.getLocation();

        List<BreinEventResult> field4 =  result.getEvents();

        List<BreinHolidayResult> field5 = result.getHolidays();

        BreinWeatherResult field6 = result.getWeather();

        return result;
    }
}

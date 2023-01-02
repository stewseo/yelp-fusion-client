package io.github.stewseo.temporaldata.service;

import com.brein.api.BreinTemporalData;
import com.brein.api.Breinify;
import com.brein.domain.BreinConfig;
import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;
import com.brein.engine.BreinEngineType;

import java.util.List;

public class TemporalDataService implements TemporalDataInterface {

    public TemporalDataService() {

    }

    public BreinTemporalDataResult temporalDataResult(String city) {

        BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
                .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

        Breinify.setConfig(config);

        return new BreinTemporalData()
                .setLocation(city)
                .execute();
    }

    public BreinTemporalDataResult temporalDataResult(String city, String state, String country) {

        BreinConfig config = new BreinConfig("938D-3120-64DD-413F-BB55-6573-90CE-473A", "utakxp7sm6weo5gvk7cytw==")
                .setRestEngineType(BreinEngineType.UNIREST_ENGINE);

        Breinify.setConfig(config);


        return new BreinTemporalData()
                .setLocation(city, state, country)
                .execute();
    }

    @Override
    public List<BreinHolidayResult> holidayResult(String city) {
        return new BreinTemporalData()
                .setLocation(city)
                .execute()
                .getHolidays();
    }

    @Override
    public BreinWeatherResult weatherResult(String city) {
        return new BreinTemporalData()
                .setLocation(city)
                .execute()
                .getWeather();
    }

    @Override
    public BreinLocationResult locationResult(String city) {
        return new BreinTemporalData()
                .setLocation(city)
                .execute()
                .getLocation();
    }
}

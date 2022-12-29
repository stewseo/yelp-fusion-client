package io.github.stewseo.temporaldata;

import com.brein.domain.results.temporaldataparts.BreinEventResult;
import com.brein.domain.results.temporaldataparts.BreinHolidayResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import com.brein.domain.results.temporaldataparts.BreinWeatherResult;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class TemporalData {

    public LocalDateTime localDateTime;

    public ZonedDateTime zonedDateTime;

    public BreinLocationResult breinLocationResult;

    public List<BreinEventResult> breinEventResults;

    public List<BreinHolidayResult> breinHolidayResults;

    public BreinWeatherResult breinWeatherResult;

    public TemporalData() {}

    public TemporalData(LocalDateTime field1,
                        ZonedDateTime field2,
                        BreinLocationResult field3,
                        List<BreinEventResult> field4,
                        List<BreinHolidayResult> field5,
                        BreinWeatherResult field6) {

        this.localDateTime = field1;
        this.zonedDateTime = field2;
        this.breinLocationResult = field3;
        this.breinEventResults = field4;
        this.breinHolidayResults = field5;
        this.breinWeatherResult = field6;
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public BreinLocationResult getBreinLocationResult() {
        return breinLocationResult;
    }

    public void setBreinLocationResult(BreinLocationResult breinLocationResult) {
        this.breinLocationResult = breinLocationResult;
    }

    public List<BreinEventResult> getBreinEventResults() {
        return breinEventResults;
    }

    public void setBreinEventResults(List<BreinEventResult> breinEventResults) {
        this.breinEventResults = breinEventResults;
    }

    public List<BreinHolidayResult> getBreinHolidayResults() {
        return breinHolidayResults;
    }

    public void setBreinHolidayResults(List<BreinHolidayResult> breinHolidayResults) {
        this.breinHolidayResults = breinHolidayResults;
    }

    public BreinWeatherResult getBreinWeatherResult() {
        return breinWeatherResult;
    }

    public void setBreinWeatherResult(BreinWeatherResult breinWeatherResult) {
        this.breinWeatherResult = breinWeatherResult;
    }

}

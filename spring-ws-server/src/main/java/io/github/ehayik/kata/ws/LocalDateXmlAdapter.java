package io.github.ehayik.kata.ws;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class LocalDateXmlAdapter extends XmlAdapter<String, java.time.LocalDate> {

    @Override
    public LocalDate unmarshal(String inputDate) {
        return isNotBlank(inputDate) ? LocalDate.parse(inputDate, ISO_DATE) : null;
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate != null ? localDate.format(ISO_DATE) : null;
    }
}

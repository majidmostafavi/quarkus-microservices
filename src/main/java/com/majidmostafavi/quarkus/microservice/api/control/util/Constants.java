package com.majidmostafavi.quarkus.microservice.api.control.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public final class Constants {

    static final String[] EventListIncludeFields = new String[]{"rawEvent", "IMSI", "@timestamp"};
    static final String[] EventByIdIncludeFields =
            new String[]{"LAC", "IMEI", "IMSI", "TerminalIP", "APN", "GGSNIP", "ratType", "rawEvent", "@timestamp",
                    "CellID", "DestMSISDN", "CustomerNo", "GTPCause", "MapErrorCode", "MAPStatus", "MCC", "MNC",
                    "OrigMSISDN", "PDPRX", "PDPTX", "resultCode", "LAI", "UEIP", "SGSNIP", "VISITINGMNO"};
    static final String[] excludeFields = new String[]{};

    public static final int MAX_STRING_LENGTH = 50;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm[:ss][.SSS][X]");
    public static final String REGEX_FOR_RANGE_1_TO_1000 = "^([1-9][0-9]{0,2}|1000)$";
    public static final String REGEX_FOR_UNSIGNED_INTEGER = "^[1-9]+[0-9]*$";

    public static final int WINDOW_SIZE = 14;
    public static final int WINDOW_SIZE_IN_SECONDS = 1209600;

    private static final String WHITESPACE = " ";
    public static final String BASIC_AUTHENTICATION = "Basic" + WHITESPACE;

    public static final String RTA_USER = "RTA_USER";
    public static final String RTA_PASSWORD = "RTA_PASSWORD";

    public static final ZoneId utcZone = ZoneId.of("UTC");
    public static final ZonedDateTime now = Instant.now().atZone(utcZone);
    public static final ZonedDateTime oneHourAgo = now.minusHours(1);

    public static final String VALID_IMSI_REGEX = "^[0-9]{14}$|^[0-9]{15}$";

}

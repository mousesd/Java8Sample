package net.homenet;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();

        app.runClockSample();
        System.out.println();

        app.runZoneIdSample();
        System.out.println();

        app.runLocalTimeSample();
        System.out.println();

        app.runLocalDateSample();
        System.out.println();

        app.runLocalDateTimeSample();
    }

    /**
     * Clock, Instant class sample
     */
    private void runClockSample() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        System.out.println(clock);
        System.out.println(millis);
        System.out.println(System.currentTimeMillis());

        Instant instant = clock.instant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

    /**
     * ZoneId class sample
     */
    private void runZoneIdSample() {
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Asia/Seoul");
        ZoneId zone2 = ZoneId.of("Asia/Pyongyang");

        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }

    /**
     * LocalTime class sample
     */
    private void runLocalTimeSample() {
        ZoneId zone1 = ZoneId.of("Asia/Seoul");
        ZoneId zone2 = ZoneId.of("Asia/Pyongyang");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1);
        System.out.println(now2);

        System.out.println(now1.isBefore(now2));
        System.out.println(ChronoUnit.HOURS.between(now1, now2));
        System.out.println(ChronoUnit.MINUTES.between(now1, now2));
    }

    /**
     * LocalDate class sample
     */
    private void runLocalDateSample() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        System.out.println(today);
        System.out.println(tomorrow);
        System.out.println(yesterday);

        LocalDate independenceDay = LocalDate.of(1945, 8, 15);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        DateTimeFormatter formatter = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("25.12.2019", formatter);
        System.out.println(xmas);
    }

    /**
     * LocalDateTime class sample
     */
    private void runLocalDateTimeSample() {
        LocalDateTime mousesd = LocalDateTime.of(1976, Month.FEBRUARY, 19, 18, 30, 30);

        System.out.println(mousesd.getDayOfWeek());
        System.out.println(mousesd.getMonth());
        System.out.println(mousesd.getLong(ChronoField.MINUTE_OF_DAY));

        Instant instant = mousesd.atZone(ZoneId.systemDefault()).toInstant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsed = LocalDateTime.parse("1976-02-19 18:30", formatter);
        System.out.println(formatter.format(parsed));
    }
}


























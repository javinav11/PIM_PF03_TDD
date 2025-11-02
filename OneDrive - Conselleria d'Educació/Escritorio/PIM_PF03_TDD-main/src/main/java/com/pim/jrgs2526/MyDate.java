package com.pim.jrgs2526;

public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    private int _day;
    private Months _month;
    private int _year;

    public MyDate() {
    }

    public MyDate(int day, Months month, int year) {
        if (month == null) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }

        if (month == Months.FEBRUARY && day == 29 && !isLeapYear(year)) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }

        int max = daysInMonth(month, year);
        if (day <= 0 || day > max) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }

        this._day = day;
        this._month = month;
        this._year = year;
    }

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth(int monthNumber) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    // Helpers
    private static int daysInMonth(Months month, int year) {
        switch (month) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    private static boolean isLeapYear(int year) {
        if (year < 0) return false;
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public void setDay(int day) {
        int max = daysInMonth(this._month, this._year);
        if (day <= 0 || day > max) {
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this._day = day;
    }

    public void setMonth(Months month) {
        if (month == null) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        if (!isValidForMonthAndYear(this._day, month, this._year)) {
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        this._month = month;
    }

    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        if (!isValidForMonthAndYear(this._day, this._month, year)) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        this._year = year;
    }

    private static boolean isValidForMonthAndYear(int day, Months month, int year) {
        if (month == null) return false;
        int max = daysInMonth(month, year);
        return day > 0 && day <= max;
    }

    // GETTERS
    public int getDay() {
        return _day;
    }

    public Months getMonth() {
        return _month;
    }

    public int getYear() {
        return _year;
    }
}

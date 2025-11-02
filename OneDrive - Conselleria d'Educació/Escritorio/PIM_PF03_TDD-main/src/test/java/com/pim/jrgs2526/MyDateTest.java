package com.pim.jrgs2526;

import com.pim.jrgs2526.MyDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MyDateTest {
    @Test
    public void myDateCorrectEmptyDateTest() {
        MyDate myDate = new MyDate();
    }

    @ParameterizedTest
    @CsvSource({"12, FEBRUARY, 2000", "1, JANUARY, 1999", "31, DECEMBER, 2001"})
    public void myDateCorrectDateTest(int day, MyDate.Months month, int year) {
        MyDate myDate;
        // First check: a valid date from a leap year
        try {
            myDate = new MyDate(day, month, year);
        }
        catch (Exception ex) {
            Assertions.fail();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 400, 2000, 2004})
    public void myDateValidLeapYear(int year) {
        MyDate myDate;
        // This test will be successful if an exception is thrown
        try {
            myDate = new MyDate(29, MyDate.Months.FEBRUARY, year);
        }
        catch (IllegalArgumentException ex) {
            Assertions.fail();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 100, 1000, 1998, 1999, 2001, 2002, 2003, 2005, 2006})
    public void myDateNotALeapYear(int year) {
        MyDate myDate;
        // This test will be successful if an exception is thrown
        try {
            myDate = new MyDate(29, MyDate.Months.FEBRUARY, year);
        }
        catch (IllegalArgumentException ex) {
            if (ex.getMessage().equals(MyDate.ERR_INVALID_DATE))
                return;
        }
        Assertions.fail();
    }

    @ParameterizedTest
    @EnumSource(value = MyDate.Months.class, names = {"FEBRUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    public void myDateInvalidMonthChange(MyDate.Months month) {
        MyDate myDate;
        // This test will be successful if an exception is thrown
        try {
            myDate = new MyDate(31, MyDate.Months.MARCH, 2023);
            myDate.setMonth(month);
        }
        catch (IllegalArgumentException ex) {
            if (ex.getMessage().equals(MyDate.ERR_INVALID_MONTH))
                return;
        }
        Assertions.fail();
    }

    @Test
    public void myDateInvalidMonthChange2() {
        MyDate myDate;
        // This test will be successful if an exception is thrown
        try {
            myDate = new MyDate(29, MyDate.Months.MARCH, 2023);
            myDate.setMonth(MyDate.Months.FEBRUARY);
        }
        catch (IllegalArgumentException ex) {
            if (ex.getMessage().equals(MyDate.ERR_INVALID_MONTH))
                return;
        }
        Assertions.fail();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 31, 32, 50})
    public void myDateInvalidDayChange(int day) {
        MyDate myDate;
        // This test will be successful if an exception is thrown
        try {
            myDate = new MyDate(30, MyDate.Months.APRIL, 2023);
            myDate.setDay(day);
        }
        catch (IllegalArgumentException ex) {
            if (ex.getMessage().equals(MyDate.ERR_INVALID_DAY))
                return;
        }
        Assertions.fail();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 1998, 1999, 2001, 2002, 2003, 2005, 2006})
    public void myDateInvalidYear(int year) {
        MyDate myDate;
        // This test will be successful if an exception is thrown
        try {
            myDate = new MyDate(29, MyDate.Months.FEBRUARY, 2000);
            myDate.setYear(year);
        }
        catch (IllegalArgumentException ex) {
            if (ex.getMessage().equals(MyDate.ERR_INVALID_YEAR))
                return;
        }
        Assertions.fail();
    }
}


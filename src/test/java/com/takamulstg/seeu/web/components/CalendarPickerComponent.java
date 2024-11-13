package com.takamulstg.seeu.web.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarPickerComponent {
    private final SelenideElement self = $("owl-date-time-container");

    private final SelenideElement
            chooseMonthAndYearDropdown = $("[aria-label='Choose month and year']");

    public CalendarPickerComponent chooseDate(String year, Month month, String day) {
        chooseMonthAndYearDropdown.click();
        $(format("[aria-label='%s']", year)).click();
        $(format("[aria-label='%s %s']", month, year)).click();
        $(format("[aria-label='%s %s, %s']", month, day, year)).click();

        return this;
    }

}

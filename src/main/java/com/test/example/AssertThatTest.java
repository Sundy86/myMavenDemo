package com.test.example;

import org.testng.annotations.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertThatTest {
    @Test
    public  void test() {
        String actual = "some text";
        String except = "some1";
        String errMsg = String.format("actual is: %s - except is: %s", actual, except);
        assertThat(actual).overridingErrorMessage(errMsg).startsWith(except);

        //  assertThat(2.0d).isLessThanOrEqualTo(2.0d);
        // assertThat("a").contains("c");
        // assertThat(actual.length()).as(actual).isEqualTo(5);
    }


}

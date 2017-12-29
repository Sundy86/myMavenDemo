package com.test.excel;

import com.github.crab2died.converter.WriteConvertible;
import com.test.date.DateUtils;
import java.util.Date;

public class dateConverter implements WriteConvertible {
    @Override
    public Object execWrite(Object o) {
        Date date = (Date)o;
        return DateUtils.date2Str(date,DateUtils.DATE_FORMAT_SEC);
    }

}

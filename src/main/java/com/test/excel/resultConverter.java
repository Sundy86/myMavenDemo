package com.test.excel;

import com.github.crab2died.converter.WriteConvertible;

public class resultConverter  implements WriteConvertible {

    @Override
    public Object execWrite(Object o) {
        if(o.toString().equals("ok")){
            return "测试成功";
        } else {
            return "测试失败";
        }

    }
}

package com.test.date;

import com.github.crab2died.exceptions.IllegalGroupIndexException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>正则匹配相关工具</p>
 * author : Crab2Died
 * date : 2017/5/24  9:43
 */
public class RegularUtils {

    /**
     * <p>判断内容是否匹配</p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:46:25
     *
     * @param pattern 匹配目标内容
     * @param reg     正则表达式
     * @return 返回boolean
     */
    public static boolean isMatched(String pattern, String reg) {
        Pattern compile = Pattern.compile(reg);
        return compile.matcher(pattern).matches();
    }

    /**
     * <p>正则提取匹配到的内容</p>
     * <p>例如：</p>
     * <p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:49:51
     *
     * @param pattern 匹配目标内容
     * @param reg     正则表达式
     * @param group   提取内容索引
     * @return 提取内容集合
     */
    public static List<String> match(String pattern, String reg, int group) {

        List<String> matchGroups = new ArrayList<String>();
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(pattern);
        if (group > matcher.groupCount() || group < 0)
            throw new IllegalGroupIndexException("Illegal match group :" + group);
        while (matcher.find()) {
            matchGroups.add(matcher.group(group));
        }
        return matchGroups;
    }

    /**
     * <p>正则提取匹配到的内容,默认提取索引为0</p>
     * <p>例如：</p>
     * <p>
     * author : Crab2Died
     * date   : 2017年06月02日  15:49:51
     *
     * @param pattern 匹配目标内容
     * @param reg     正则表达式
     * @return 提取内容集合
     */
    public static String match(String pattern, String reg) {

        String match = null;
        List<String> matches = match(pattern, reg, 0);
        if (null != matches && matches.size() > 0) {
            match = matches.get(0);
        }
        return match;
    }

    public static String converNumByReg(String number) {
        Pattern compile = Pattern.compile("^(\\d+)(\\.0*)?$");
        Matcher matcher = compile.matcher(number);
        while (matcher.find()) {
            number = matcher.group(1);
        }
        return number;
    }

    public static void main(String[] args) {
        System.out.println(converNumByReg("A"));
        List<String> list =  match("10:29:34","^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$",0);
        for (String s:list){
            System.out.println(s);
        }
    }
}

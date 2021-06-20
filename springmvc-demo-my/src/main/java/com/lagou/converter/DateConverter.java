package com.lagou.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器
 *
 * @author lane
 * @date 2021年03月31日 下午3:51
 * Converter<S,T>
 * S:source 原类型
 * T：target 目标类型
 */
public class DateConverter implements Converter<String, Date> {

    //完成字符串向日期的装换
    @Override
    public Date convert(String source) {

        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {

            date = sm.parse(source);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}

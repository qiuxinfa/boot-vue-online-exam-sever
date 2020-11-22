package com.qxf.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName QuestionLevelMap
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/21 23:33
 **/
public class QuestionLevelMap {
    private static final Map<String,Integer> questionLevelMap = new HashMap<>(4);
    static {
        // 这里应该去数据库查字典的，只是为了方便起见，直接写死了
        questionLevelMap.put("简单",1);
        questionLevelMap.put("中等",2);
        questionLevelMap.put("困难",3);
    }
    public static Integer getLevelByValue(String value){
        return questionLevelMap.get(value);
    }
}

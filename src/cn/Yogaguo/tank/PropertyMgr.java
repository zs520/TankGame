package cn.Yogaguo.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private static Properties pro;
    static {
        pro = new Properties();
        try {
            pro.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key){
        return (String)pro.get(key);
    }
}

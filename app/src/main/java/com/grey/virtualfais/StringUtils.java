package com.grey.virtualfais;

import java.text.Normalizer;

public class StringUtils {
    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String normalize(String str){
        String tmp = Normalizer.normalize(str, Normalizer.Form.NFD);
        return tmp.replaceAll("[^\\x00-\\x7F]", "");
    }
}

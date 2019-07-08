package com.blueice.aaptool.utils;

/**
* @Description: TODO
* @author blueice
* @date 2019年7月7日 下午3:52:44
*
*/
public class StringUtils
{
    public static boolean isEmpty(String target) {
        if(target!=null&&!target.isEmpty()) {
            return false;
        }
        return true;
    }
}

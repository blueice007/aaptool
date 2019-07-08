package com.blueice.aaptool.utils;

/**
* @Description: TODO
* @author blueice
* @date 2019年7月7日 下午4:01:04
*
*/
public class ProcessBuilderFactory
{
    public static ProcessBuilder newInstance() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        return processBuilder;
    }
}

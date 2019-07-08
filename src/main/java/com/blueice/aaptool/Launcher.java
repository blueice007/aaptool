package com.blueice.aaptool;

/**
* @Description: TODO
* @author blueice
* @date 2019年7月7日 下午8:04:47
*
*/
public class Launcher
{
    public static void main(String[] args)
    {
        ApkMeta a = ApkUtil.getApkMetaInfo(args[0], args[1]);
        System.out.println(a);
    }
}

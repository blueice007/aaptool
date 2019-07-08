package com.blueice.aaptool.utils;

/**
* @Description: TODO
* @author blueice
* @date 2019年7月7日 下午3:35:32
*
*/
public class OSDetection
{
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String Bit = System.getProperty("sun.arch.data.model").toLowerCase();
    
    public static boolean isWindows()
    {
      return OS.contains("win");
    }
    
    public static boolean isMacOSX()
    {
      return OS.contains("mac");
    }
    
    public static boolean isUnix()
    {
      return (OS.contains("nix")) || (OS.contains("nux")) || (OS.contains("aix")) || (OS.contains("sunos"));
    }
    
    public static boolean is64Bit()
    {
      return Bit.equalsIgnoreCase("64");
    }
    
    public static String returnOS()
    {
      return OS;
    }
}

package com.blueice.aaptool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blueice.aaptool.utils.OSDetection;
import com.blueice.aaptool.utils.ProcessBuilderFactory;
import com.blueice.aaptool.utils.StringUtils;

/**
* @Description: TODO
* @author blueice
* @date 2019年7月7日 下午3:42:46
*
*/
public class ApkUtil
{
    private static final String AAPT = "aapt";
    private static Map<String,Pattern> patternMap;
    static {
        patternMap = new HashMap<String, Pattern>();
        patternMap.put("package", Pattern.compile("package: name='(.+)' versionCode='(.+)' versionName='(.+)' platformBuildVersionName="));
        patternMap.put("sdkVersion", Pattern.compile("sdkVersion:'(.+)'"));
        patternMap.put("targetSdkVersion", Pattern.compile("targetSdkVersion:'(.+)'"));
        patternMap.put("uses-permission", Pattern.compile("uses-permission: name='(.+)'"));
        patternMap.put("application-label", Pattern.compile("application-label-(.+):'(.*)'"));
        patternMap.put("application-icon", Pattern.compile("application-icon-(\\d+):'(.+)'"));
        patternMap.put("application", Pattern.compile("application: label='(.*)' icon='(.*)'"));
        patternMap.put("launchable-activity", Pattern.compile("launchable-activity: name='(.+)'  label='(.*)' icon='(.*)'"));
        patternMap.put("uses-feature-not-required", Pattern.compile("uses-feature-not-required: name='(.+)'"));
        patternMap.put("uses-feature", Pattern.compile("uses-feature: name='(.+)'"));
        patternMap.put("uses-implied-feature", Pattern.compile("uses-implied-feature: name='(.+)' reason='(.*)'"));
        patternMap.put("provides-component", Pattern.compile("provides-component:'(.+)'"));
        patternMap.put("supports-screens", Pattern.compile("supports-screens: '(.+)'"));
        patternMap.put("supports-any-density", Pattern.compile("supports-any-density: '(.+)'"));
        patternMap.put("locales", Pattern.compile("locales: '(.+)'"));
        patternMap.put("densities", Pattern.compile("densities: '(.+)'"));
        patternMap.put("native-code", Pattern.compile("native-code: '(.+)'"));
    }
    public static ApkMeta getApkMetaInfo(String aaptPath,String apkFilePath) {
        if(StringUtils.isEmpty(apkFilePath)) {
            return null;
        }
        File file = new File(apkFilePath);
        if(!file.exists()) {
            return null;
        }
        ApkMeta apkMeta = new ApkMeta();
        apkMeta.setApkFileName(file.getName());
        apkMeta.setApkFileSize(file.length());
        ProcessBuilder processBuilder = ProcessBuilderFactory.newInstance();
        Process process = null;
        BufferedReader br = null;
        try
        {
            
            process = processBuilder.command(getAaptPath(aaptPath),"d","badging",apkFilePath).start();
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String tempStr = null;
            while((tempStr=br.readLine())!=null) {
                buildApkMeta(apkMeta,tempStr);
            }
        }
        catch (IOException e)
        {
            apkMeta = null;
            e.printStackTrace();
        }finally {
            if(process!=null) {
                process.destroy();
            }
            if(br!=null) {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return apkMeta;
    }
    
    /**
    * 
    * @param apkMeta
    * @param tempStr
    * @author blueice
    * @date 2019年7月7日 下午4:44:18
    */
    private static void buildApkMeta(ApkMeta apkMeta, String tempStr)
    {
        if(StringUtils.isEmpty(tempStr.trim())||!tempStr.contains(":")) {
            return;
        }
        String patternKey = tempStr.substring(0, tempStr.indexOf(":")).trim();
        
        if(patternKey.startsWith("application-label")) {
            patternKey = "application-label";
        }else if(patternKey.startsWith("application-icon")) {
            patternKey = "application-icon";
        }
        
        if(patternMap.containsKey(patternKey)) {
            Matcher m = patternMap.get(patternKey).matcher(tempStr);
            if("package".equals(patternKey)&&m.find()) {
                apkMeta.setPackageName(m.group(1));
                apkMeta.setVersionCode(m.group(2));
                apkMeta.setVersionName(m.group(3));
            }
            if("sdkVersion".equals(patternKey)&&m.find()) {
                apkMeta.setSdkVersion(m.group(1));
            }
            if("targetSdkVersion".equals(patternKey)&&m.find()) {
                apkMeta.setTargetSdkVersion(m.group(1));
            }
            if("uses-permission".equals(patternKey)&&m.find()) {
                if(apkMeta.getUsesPermissions()!=null) {
                    apkMeta.getUsesPermissions().add(m.group(1));
                }else {
                    List<String> list = new ArrayList<String>();
                    list.add(m.group(1));
                    apkMeta.setUsesPermissions(list);
                }
            }
            if("application-label".equals(patternKey)&&m.find()) {
                if(apkMeta.getApplicationLables()!=null) {
                    apkMeta.getApplicationLables().put(m.group(1), m.group(2));
                }else {
                    Map<String,String> applicationLables = new HashMap<String, String>();
                    applicationLables.put(m.group(1), m.group(2));
                    apkMeta.setApplicationLables(applicationLables);
                }
            }
            if("application-icon".equals(patternKey)&&m.find()) {
                if(apkMeta.getApplicationIcons()!=null) {
                    apkMeta.getApplicationIcons().put(m.group(1), m.group(2));
                }else {
                    Map<String,String> applicationIcons = new HashMap<String, String>();
                    applicationIcons.put(m.group(1), m.group(2));
                    apkMeta.setApplicationIcons(applicationIcons);
                }
            }
            if("application".equals(patternKey)&&m.find()) {
                apkMeta.setCurApplicationLable(m.group(1));
                apkMeta.setCurApplicationIcon(m.group(2));
            }
            if("launchable-activity".equals(patternKey)&&m.find()) {
                apkMeta.setLaunchableName(m.group(1));
                apkMeta.setLaunchableLabel(m.group(2));
                apkMeta.setLaunchableIcon(m.group(3));
            }
            if("uses-feature-not-required".equals(patternKey)&&m.find()) {
                if(apkMeta.getUsesFeatures()!=null) {
                    apkMeta.getUsesFeatures().add("not-required#"+m.group(1));
                }else {
                    List<String> list = new ArrayList<String>();
                    list.add("not-required#"+m.group(1));
                    apkMeta.setUsesFeatures(list);
                }
            }
            if("uses-feature".equals(patternKey)&&m.find()) {
                if(apkMeta.getUsesFeatures()!=null) {
                    apkMeta.getUsesFeatures().add("required#"+m.group(1));
                }else {
                    List<String> list = new ArrayList<String>();
                    list.add("required#"+m.group(1));
                    apkMeta.setUsesFeatures(list);
                }
            }
            if("uses-implied-feature".equals(patternKey)&&m.find()) {
                if(apkMeta.getUsesFeatures()!=null) {
                    apkMeta.getUsesFeatures().add(String.format("implied#%s#%s", m.group(1),m.group(2)));
                }else {
                    List<String> list = new ArrayList<String>();
                    list.add(String.format("implied#%s#%s", m.group(1),m.group(2)));
                    apkMeta.setUsesFeatures(list);
                }
            }
            if("provides-component".equals(patternKey)&&m.find()) {
                if(apkMeta.getProvidesComponents()!=null) {
                    apkMeta.getProvidesComponents().add(m.group(1));
                }else {
                    List<String> list = new ArrayList<String>();
                    list.add(m.group(1));
                    apkMeta.setProvidesComponents(list);
                }
            }
            if("supports-screens".equals(patternKey)&&m.find()) {
                List<String> list = new ArrayList<String>();
                for(String screen:m.group(1).split("' '")){
                    list.add(screen);
                }
                apkMeta.setSupportsScreens(list);
            }
            if("supports-any-density".equals(patternKey)&&m.find()) {
                apkMeta.setSupportsAnyDensity(m.group(1));
            }
            if("locales".equals(patternKey)&&m.find()) {
                List<String> list = new ArrayList<String>();
                for(String screen:m.group(1).split("' '")){
                    list.add(screen);
                }
                apkMeta.setLocales(list);
            }
            if("densities".equals(patternKey)&&m.find()) {
                List<String> list = new ArrayList<String>();
                for(String screen:m.group(1).split("' '")){
                    list.add(screen);
                }
                apkMeta.setDensities(list);
            }
            if("native-code".equals(patternKey)&&m.find()) {
                List<String> list = new ArrayList<String>();
                for(String screen:m.group(1).split("' '")){
                    list.add(screen);
                }
                apkMeta.setNativeCode(list);
            }
        }
    }

    private static String getAaptPath(String aaptPath) {
        if(OSDetection.isUnix()) {
            return aaptPath+ String.format("%s%s%s%s%s%s", 
                    File.separator,"linux",File.separator,
                    OSDetection.is64Bit()?"64":"32",File.separator,
                            AAPT);
        }else if(OSDetection.isWindows()) {
            return aaptPath+ String.format("%s%s%s%s.exe", 
                    File.separator,"windows",File.separator,
                            AAPT);
        }else {
            return aaptPath+ String.format("%s%s%s%s%s%s", 
                    File.separator,"macosx",File.separator,
                    OSDetection.is64Bit()?"64":"32",File.separator,
                            AAPT);
        }
    }
    
    public static void main(String[] args)
    {
        ApkMeta a = ApkUtil.getApkMetaInfo(args[0], args[1]);
        System.out.println(a);
    }
}

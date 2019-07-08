package com.blueice.aaptool;

import java.util.List;
import java.util.Map;

/**
* @Description: TODO
* @author blueice
* @date 2019年7月1日 下午8:23:59
*
*/
public class ApkMeta
{
    private String apkFileName;
    private Long apkFileSize;
    private String packageName;
    private String versionCode;
    private String versionName;
    private String sdkVersion;
    private String targetSdkVersion;
    private List<String> usesPermissions;
    private Map<String,String> applicationLables;
    private Map<String,String> applicationIcons;
    private String curApplicationLable;
    private String curApplicationIcon;
    private String launchableName;
    private String launchableLabel;
    private String launchableIcon;
    // xxx#com.xx.xx.xx#reason
    private List<String> usesFeatures;
    private List<String> providesComponents;
    private List<String> supportsScreens;
    private String supportsAnyDensity;
    private List<String> locales;
    private List<String> densities;
    private List<String> nativeCode;
    public String getPackageName()
    {
        return packageName;
    }
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
    public String getVersionCode()
    {
        return versionCode;
    }
    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }
    public String getVersionName()
    {
        return versionName;
    }
    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }
    public String getSdkVersion()
    {
        return sdkVersion;
    }
    public void setSdkVersion(String sdkVersion)
    {
        this.sdkVersion = sdkVersion;
    }
    public String getTargetSdkVersion()
    {
        return targetSdkVersion;
    }
    public void setTargetSdkVersion(String targetSdkVersion)
    {
        this.targetSdkVersion = targetSdkVersion;
    }
    public List<String> getUsesPermissions()
    {
        return usesPermissions;
    }
    public void setUsesPermissions(List<String> usesPermissions)
    {
        this.usesPermissions = usesPermissions;
    }
    public Map<String, String> getApplicationLables()
    {
        return applicationLables;
    }
    public void setApplicationLables(Map<String, String> applicationLables)
    {
        this.applicationLables = applicationLables;
    }
    public Map<String, String> getApplicationIcons()
    {
        return applicationIcons;
    }
    public void setApplicationIcons(Map<String, String> applicationIcons)
    {
        this.applicationIcons = applicationIcons;
    }
    public String getCurApplicationLable()
    {
        return curApplicationLable;
    }
    public void setCurApplicationLable(String curApplicationLable)
    {
        this.curApplicationLable = curApplicationLable;
    }
    public String getCurApplicationIcon()
    {
        return curApplicationIcon;
    }
    public void setCurApplicationIcon(String curApplicationIcon)
    {
        this.curApplicationIcon = curApplicationIcon;
    }
    public String getLaunchableName()
    {
        return launchableName;
    }
    public void setLaunchableName(String launchableName)
    {
        this.launchableName = launchableName;
    }
    public String getLaunchableLabel()
    {
        return launchableLabel;
    }
    public void setLaunchableLabel(String launchableLabel)
    {
        this.launchableLabel = launchableLabel;
    }
    public String getLaunchableIcon()
    {
        return launchableIcon;
    }
    public void setLaunchableIcon(String launchableIcon)
    {
        this.launchableIcon = launchableIcon;
    }
    public List<String> getUsesFeatures()
    {
        return usesFeatures;
    }
    public void setUsesFeatures(List<String> usesFeatures)
    {
        this.usesFeatures = usesFeatures;
    }
    public List<String> getProvidesComponents()
    {
        return providesComponents;
    }
    public void setProvidesComponents(List<String> providesComponents)
    {
        this.providesComponents = providesComponents;
    }
    public List<String> getSupportsScreens()
    {
        return supportsScreens;
    }
    public void setSupportsScreens(List<String> supportsScreens)
    {
        this.supportsScreens = supportsScreens;
    }
    public String getSupportsAnyDensity()
    {
        return supportsAnyDensity;
    }
    public void setSupportsAnyDensity(String supportsAnyDensity)
    {
        this.supportsAnyDensity = supportsAnyDensity;
    }
    public List<String> getLocales()
    {
        return locales;
    }
    public void setLocales(List<String> locales)
    {
        this.locales = locales;
    }
    public List<String> getDensities()
    {
        return densities;
    }
    public void setDensities(List<String> densities)
    {
        this.densities = densities;
    }
    public List<String> getNativeCode()
    {
        return nativeCode;
    }
    public void setNativeCode(List<String> nativeCode)
    {
        this.nativeCode = nativeCode;
    }
    public String getApkFileName()
    {
        return apkFileName;
    }
    public void setApkFileName(String apkFileName)
    {
        this.apkFileName = apkFileName;
    }
    public Long getApkFileSize()
    {
        return apkFileSize;
    }
    public void setApkFileSize(Long apkFileSize)
    {
        this.apkFileSize = apkFileSize;
    }
    @Override
    public String toString()
    {
        return "ApkMeta [apkFileName=" + apkFileName + ", packageName=" + packageName + ", versionCode=" + versionCode + ", versionName=" + versionName + "]";
    }
    
}

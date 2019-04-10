package com.neo.utils;

/**
 * @author lihongbo
 * @create 2017-11-08 上午11:45
 **/
public class AppIdentifier {
    private String appKey = null;
    private String appSecret = null;
    private int userCount = 0;
    private long deadLine = 0L;
    private long licenseIndex = 0L;
    private int packageCount = 0;


    public String getAppKey() {
        return appKey;
    }

    public AppIdentifier setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public AppIdentifier setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public int getUserCount() {
        return userCount;
    }

    public AppIdentifier setUserCount(int userCount) {
        this.userCount = userCount;
        return this;
    }


    public long getDeadLine() {
        return deadLine;
    }

    public AppIdentifier setDeadLine(long deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public long getLicenseIndex() {
        return licenseIndex;
    }

    public AppIdentifier setLicenseIndex(long licenseIndex) {
        this.licenseIndex = licenseIndex;
        return this;
    }

    public int getPackageCount() {
        return packageCount;
    }

    public AppIdentifier setPackageCount(int packageCount) {
        this.packageCount = packageCount;
        return this;
    }
}
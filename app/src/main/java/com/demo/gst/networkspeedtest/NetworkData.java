package com.demo.gst.networkspeedtest;

/**
 * Created by steffi on 23/3/18.
 */

public class NetworkData {

    private String NetworkType;
    private String DownloadSpeed;
    private String UploadSpeed;
    private String DateTime;

    /*public NetworkData(String DateTime, String NetworkType, String DownloadSpeed, String UploadSpeed){
        this.DateTime = DateTime;
        this.NetworkType = NetworkType;
        this.DownloadSpeed = DownloadSpeed;
        this.UploadSpeed = UploadSpeed;

    }*/

    public String getNetworkType() {
        return NetworkType;
    }

    public void setNetworkType(String networkType) {
        NetworkType = networkType;
    }

    public String getDownloadSpeed() {
        return DownloadSpeed;
    }

    public void setDownloadSpeed(String downloadSpeed) {
        DownloadSpeed = downloadSpeed;
    }

    public String getUploadSpeed() {
        return UploadSpeed;
    }

    public void setUploadSpeed(String uploadSpeed) {
        UploadSpeed = uploadSpeed;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }
}


package com.realappraiser.gharvalue.model;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class ConfigData {

    @Expose
    private String fileName;
    @Expose
    private String url;
    @Expose
    private String user;
    @Expose
    private String version;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}

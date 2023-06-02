package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UrlModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public class Data {

        @SerializedName("OLD")
        @Expose
        private List<Old> old = null;
        @SerializedName("NEW")
        @Expose
        private List<New> _new = null;

        public List<Old> getOld() {
            return old;
        }

        public void setOld(List<Old> old) {
            this.old = old;
        }

        public List<New> getNew() {
            return _new;
        }

        public void setNew(List<New> _new) {
            this._new = _new;
        }
        public class Old {

            @SerializedName("WebsiteName")
            @Expose
            private String websiteName;

            public String getWebsiteName() {
                return websiteName;
            }

            public void setWebsiteName(String websiteName) {
                this.websiteName = websiteName;
            }

        }

        public class New {

            @SerializedName("WebsiteName")
            @Expose
            private String websiteName;

            public String getWebsiteName() {
                return websiteName;
            }

            public void setWebsiteName(String websiteName) {
                this.websiteName = websiteName;
            }

        }

    }
}

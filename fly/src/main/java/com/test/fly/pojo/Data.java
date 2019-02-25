package com.test.fly.pojo;

import java.util.Date;

public class Data {
    private Integer msid;

    private Integer id;

    private String pm;

    private Integer temp;

    private Integer humi;

    private Double lon;

    private Double lat;

    private Date rq;

    private String status;

    public Data(Integer msid, Integer id, String pm, Integer temp, Integer humi, Double lon, Double lat, Date rq ,String status) {
        this.msid = msid;
        this.id = id;
        this.pm = pm;
        this.temp = temp;
        this.humi = humi;
        this.lon = lon;
        this.lat = lat;
        this.rq = rq;
        this.status = status;
    }

    public Data() {
        super();
    }

    public Integer getMsid() {
        return msid;
    }

    public void setMsid(Integer msid) {
        this.msid = msid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm == null ? null : pm.trim();
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getHumi() {
        return humi;
    }

    public void setHumi(Integer humi) {
        this.humi = humi;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getStatus(){
        return status;
    }

    public  void setStatus(String status){
        this.status = status;
    }
}
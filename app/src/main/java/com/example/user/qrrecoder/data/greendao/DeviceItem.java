package com.example.user.qrrecoder.data.greendao;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

/**
 * Created by USER on 2017/11/7.
 */

@Entity
public class DeviceItem{
    @Id private Long id;
    @Expose
    @Index(unique = true)
    @NotNull
    private int deviceid;
    @Expose
    @Index
    private int userid;
    @Expose
    @NotNull
    private String deviceuuid;
    @Expose
    private long scantime;
    @Expose(serialize = false)
    private int serverState;//上传的状态
    @Expose
    private String faccount;
    @Generated(hash = 1144007671)
    public DeviceItem(Long id, int deviceid, int userid, @NotNull String deviceuuid,
            long scantime, int serverState, String faccount) {
        this.id = id;
        this.deviceid = deviceid;
        this.userid = userid;
        this.deviceuuid = deviceuuid;
        this.scantime = scantime;
        this.serverState = serverState;
        this.faccount = faccount;
    }
    @Generated(hash = 1139167867)
    public DeviceItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getDeviceid() {
        return this.deviceid;
    }
    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }
    public int getUserid() {
        return this.userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getDeviceuuid() {
        return this.deviceuuid;
    }
    public void setDeviceuuid(String deviceuuid) {
        this.deviceuuid = deviceuuid;
    }
    public long getScantime() {
        return this.scantime;
    }
    public void setScantime(long scantime) {
        this.scantime = scantime;
    }
    public int getServerState() {
        return this.serverState;
    }
    public void setServerState(int serverState) {
        this.serverState = serverState;
    }
    public String getFaccount() {
        return this.faccount;
    }
    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

}

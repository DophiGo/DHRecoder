package com.example.user.qrrecoder.data.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;

/**
 * Created by USER on 2017/11/7.
 */
@Entity
public class User implements Serializable {
    static final long serialVersionUID = 42L;
    @Id private Long id;
    @Index(unique = true)
    private int userid;
    private String acount;
    private String fname;
    private String userpwd;
    private String tel;
    private String agent;
    private String address;
    private String agenttel;
    private String token;
    @Generated(hash = 1766897838)
    public User(Long id, int userid, String acount, String fname, String userpwd,
            String tel, String agent, String address, String agenttel,
            String token) {
        this.id = id;
        this.userid = userid;
        this.acount = acount;
        this.fname = fname;
        this.userpwd = userpwd;
        this.tel = tel;
        this.agent = agent;
        this.address = address;
        this.agenttel = agenttel;
        this.token = token;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUserid() {
        return this.userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getAcount() {
        return this.acount;
    }
    public void setAcount(String acount) {
        this.acount = acount;
    }
    public String getFname() {
        return this.fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getUserpwd() {
        return this.userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAgent() {
        return this.agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAgenttel() {
        return this.agenttel;
    }
    public void setAgenttel(String agenttel) {
        this.agenttel = agenttel;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
   
}

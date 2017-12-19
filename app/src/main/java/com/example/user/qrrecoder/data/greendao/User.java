package com.example.user.qrrecoder.data.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * Created by USER on 2017/11/7.
 */
@Entity
public class User {
    @Id private Long id;
    @Index(unique = true)
    private int userid;
    private String acount;
    private String username;
    private String fname;
    private String userpwd;
    private String email;
    private int sessionid;
    @Generated(hash = 1071991224)
    public User(Long id, int userid, String acount, String username, String fname,
            String userpwd, String email, int sessionid) {
        this.id = id;
        this.userid = userid;
        this.acount = acount;
        this.username = username;
        this.fname = fname;
        this.userpwd = userpwd;
        this.email = email;
        this.sessionid = sessionid;
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
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getSessionid() {
        return this.sessionid;
    }
    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }
   
}

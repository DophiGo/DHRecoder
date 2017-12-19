package com.example.user.qrrecoder.http.Enty;

/**
 * Created by dxs on 2017/11/30.
 */

public class LoginResult extends HttpResults {
    private int fcustomerid; //用户所属客户ID
    private int fidentity; //用户所属身份 2:方案商、3:工厂、4:代理商
    private String fusername; //用户名
    private String fname;  //客户名称
    private String fmail;  //首次登陆用来发激活链接
    private int sessionid;//会话ID

    public int getFcustomerid() {
        return fcustomerid;
    }

    public void setFcustomerid(int fcustomerid) {
        this.fcustomerid = fcustomerid;
    }

    public int getFidentity() {
        return fidentity;
    }

    public void setFidentity(int fidentity) {
        this.fidentity = fidentity;
    }

    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFmail() {
        return fmail;
    }

    public void setFmail(String fmail) {
        this.fmail = fmail;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }
}

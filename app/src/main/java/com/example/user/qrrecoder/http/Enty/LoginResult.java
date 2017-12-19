package com.example.user.qrrecoder.http.Enty;

/**
 * Created by dxs on 2017/11/30.
 */

public class LoginResult extends HttpResults {
    private String faccount; //用户所属客户ID
    private String fname;  //客户名称
    private String ftoken;//会话ID

    public String getFaccount() {
        return faccount;
    }

    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtoken() {
        return ftoken;
    }

    public void setFtoken(String ftoken) {
        this.ftoken = ftoken;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "faccount='" + faccount + '\'' +
                ", fname='" + fname + '\'' +
                ", ftoken='" + ftoken + '\'' +
                '}';
    }
}

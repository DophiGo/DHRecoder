package com.example.user.qrrecoder.bean;

import android.text.TextUtils;

/**
 * Created by dxs on 2017/12/20.
 */

public class UserInfoRegist {
    private String account;
    private String emailcode;
    private String pwd;
    private String name;
    private String tel;
    private String agent;
    private String addr;
    private String agenttel;

    public UserInfoRegist(String account, String emailcode, String pwd, String name, String tel, String agent, String addr, String agenttel) {
        this.account = account;
        this.emailcode = emailcode;
        this.pwd = pwd;
        this.name = name;
        this.tel = tel;
        this.agent = agent;
        this.addr = addr;
        this.agenttel = agenttel;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmailcode() {
        return emailcode;
    }

    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAgenttel() {
        return agenttel;
    }

    public void setAgenttel(String agenttel) {
        this.agenttel = agenttel;
    }

    public boolean isEmpty(){
        return TextUtils.isEmpty(account)
                ||TextUtils.isEmpty(emailcode)
                ||TextUtils.isEmpty(pwd)
                ||TextUtils.isEmpty(name)
                ||TextUtils.isEmpty(tel)
                ||TextUtils.isEmpty(agent)
                ||TextUtils.isEmpty(agenttel)
                ||TextUtils.isEmpty(addr);
    }
}

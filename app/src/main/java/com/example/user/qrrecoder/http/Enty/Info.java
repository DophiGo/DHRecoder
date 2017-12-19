package com.example.user.qrrecoder.http.Enty;

/**
 * Created by dxs on 2017/12/19.
 */

public class Info extends HttpResults {
    private String fstatus;//
    private String fagenttel;// 代理商电话
    private String ftel;// 电话
    private String faddr;// 代理商地址
    private String fagent;// 代理商名称
    private String fname;// 姓名

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtel() {
        return ftel;
    }

    public void setFtel(String ftel) {
        this.ftel = ftel;
    }

    public String getFagent() {
        return fagent;
    }

    public void setFagent(String fagent) {
        this.fagent = fagent;
    }

    public String getFaddr() {
        return faddr;
    }

    public void setFaddr(String faddr) {
        this.faddr = faddr;
    }

    public String getFagenttel() {
        return fagenttel;
    }

    public void setFagenttel(String fagenttel) {
        this.fagenttel = fagenttel;
    }

    @Override
    public String toString() {
        return "Info{" +
                "fname='" + fname + '\'' +
                ", ftel='" + ftel + '\'' +
                ", fagent='" + fagent + '\'' +
                ", faddr='" + faddr + '\'' +
                ", fagenttel='" + fagenttel + '\'' +
                '}';
    }
}

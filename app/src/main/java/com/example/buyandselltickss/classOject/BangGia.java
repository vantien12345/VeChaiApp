package com.example.buyandselltickss.classOject;

public class BangGia {
    private int hinh;
    private String tenPheLieu;
    private String giaPheLieu;

    public BangGia(int hinh, String tenPheLieu, String giaPheLieu) {
        this.hinh = hinh;
        this.tenPheLieu = tenPheLieu;
        this.giaPheLieu = giaPheLieu;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTenPheLieu() {
        return tenPheLieu;
    }

    public void setTenPheLieu(String tenPheLieu) {
        this.tenPheLieu = tenPheLieu;
    }

    public String getGiaPheLieu() {
        return giaPheLieu;
    }

    public void setGiaPheLieu(String giaPheLieu) {
        this.giaPheLieu = giaPheLieu;
    }
}

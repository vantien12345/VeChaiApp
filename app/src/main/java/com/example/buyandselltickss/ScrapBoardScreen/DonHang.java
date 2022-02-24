package com.example.buyandselltickss.ScrapBoardScreen;

public class DonHang {
    private String address;
    private String time;


    public DonHang() {
    }

    public DonHang(String address, String time) {
        this.address = address;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

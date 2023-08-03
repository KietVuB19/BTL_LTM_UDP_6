package client;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class TaiSan implements Serializable{
    private Long ma;
    private String ten;
    private Float gia;
    private String viTriHienTai;
    private String loaiTaiSan;
    private Integer maPhong;
    private Phong phong;

    public TaiSan() {
    }

    public TaiSan(Long ma, String ten, Float gia, String viTriHienTai, String loaiTaiSan, Phong phong) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.viTriHienTai = viTriHienTai;
        this.loaiTaiSan = loaiTaiSan;
        this.phong = phong;
    }

    public TaiSan(String ten, Float gia, String viTriHienTai, String loaiTaiSan, Phong phong) {
        this.ten = ten;
        this.gia = gia;
        this.viTriHienTai = viTriHienTai;
        this.loaiTaiSan = loaiTaiSan;
        this.phong = phong;
    }

    public TaiSan(Long ma, String ten, Float gia, String viTriHienTai, String loaiTaiSan, Integer maPhong) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.viTriHienTai = viTriHienTai;
        this.loaiTaiSan = loaiTaiSan;
        this.maPhong = maPhong;
    }

    public TaiSan(String ten, Float gia, String viTriHienTai, String loaiTaiSan, Integer maPhong) {
        this.ten = ten;
        this.gia = gia;
        this.viTriHienTai = viTriHienTai;
        this.loaiTaiSan = loaiTaiSan;
        this.maPhong = maPhong;
    }

    
    
    public Integer getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(Integer maPhong) {
        this.maPhong = maPhong;
    }
    
    public Long getMa() {
        return ma;
    }

    public void setMa(Long ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public String getViTriHienTai() {
        return viTriHienTai;
    }

    public void setViTriHienTai(String viTriHienTai) {
        this.viTriHienTai = viTriHienTai;
    }

    public String getLoaiTaiSan() {
        return loaiTaiSan;
    }

    public void setLoaiTaiSan(String loaiTaiSan) {
        this.loaiTaiSan = loaiTaiSan;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
    
}

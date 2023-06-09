/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Produk;

/**
 *
 * @author Asus
 */
public class pakaian {
    String nama;
    String jenis;
    String ukuran;
    String brand;
    Integer stok;

    public pakaian(String nama, String jenis, String ukuran, String brand, Integer stok) {
        this.nama = nama;
        this.jenis = jenis;
        this.ukuran = ukuran;
        this.brand = brand;
        this.stok = stok;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getUkuran() {
        return ukuran;
    }

    public Integer getStok() {
        return stok;
    }

    public String getBrand() {
        return brand;
    }
    
}

package com.mycompany.gudanginternet.controller;

import com.mycompany.gudanginternet.model.Barang;
import com.mycompany.gudanginternet.model.BarangUmum;
import com.mycompany.gudanginternet.model.PerangkatJaringan;
import com.mycompany.gudanginternet.model.KabelJaringan;
import java.util.ArrayList;

public class BarangController {
    private final ArrayList<Barang> daftarBarang;
    private int nextId;

   
    public BarangController() {
        this.daftarBarang = new ArrayList<>();
        this.nextId = 1;
    }

    public Barang tambahBarang(String namaBarang, String kategori, double harga, int stok) {
        Barang barangBaru = new BarangUmum(nextId, namaBarang, kategori, harga, stok);
        daftarBarang.add(barangBaru);
        nextId++;
        return barangBaru;
    }

    public Barang tambahBarang(String namaBarang, String kategori, double harga, int stok,
            String merek, int garansiBulan) {
        Barang barangBaru = new PerangkatJaringan(nextId, namaBarang, kategori, harga, stok, merek, garansiBulan);
        daftarBarang.add(barangBaru);
        nextId++;
        return barangBaru;
    }

    public Barang tambahBarang(String namaBarang, String kategori, double harga, int stok,
            double panjangMeter, String jenisKabel) {
        Barang barangBaru = new KabelJaringan(nextId, namaBarang, kategori, harga, stok, panjangMeter, jenisKabel);
        daftarBarang.add(barangBaru);
        nextId++;
        return barangBaru;
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    public Barang cariBarangById(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean updateBarang(int id, String namaBarang, String kategori, double harga, int stok) {
        Barang barang = cariBarangById(id);
        if (barang == null) {
            return false;
        }
        barang.setNamaBarang(namaBarang);
        barang.setKategori(kategori);
        barang.setHarga(harga);
        barang.setStok(stok);
        return true;
    }

    public boolean hapusBarang(int id) {
        Barang barang = cariBarangById(id);
        if (barang == null) {
            return false;
        }
        return daftarBarang.remove(barang);
    }

    public int getJumlahBarang() {
        return daftarBarang.size();
    }
}
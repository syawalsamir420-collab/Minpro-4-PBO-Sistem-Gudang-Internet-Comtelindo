package com.mycompany.gudanginternet.view;

import com.mycompany.gudanginternet.controller.BarangController;
import com.mycompany.gudanginternet.model.Barang;
import com.mycompany.gudanginternet.model.Bergaransi;
import com.mycompany.gudanginternet.model.Diskon;
import java.util.Scanner;

public class BarangView {
    private Scanner sc;
    private BarangController controller;


    public BarangView(BarangController controller) {
        this.sc = new Scanner(System.in);
        this.controller = controller;
    }

    public void tampilkanMenuUtama() {
        boolean running = true;

        while (running) {
            tampilkanMenu();
            int pilihan = Validator.inputPilihanMenu(sc, "Pilih menu (1-8): ", 1, 8);

            switch (pilihan) {
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    System.out.println("\n=== DAFTAR BARANG GUDANG ===");
                    tampilkanSemuaBarang();
                    break;
                case 3:
                    cariBarang();
                    break;
                case 4:
                    updateBarang();
                    break;
                case 5:
                    hapusBarang();
                    break;
                case 6:
                    klaimGaransi();
                    break;
                case 7:
                    hitungDiskon();
                    break;
                case 8:
                    System.out.println("Terima kasih telah menggunakan Sistem Gudang Internet Comtelindo!");
                    running = false;
                    break;
                default:
                    System.out.println("Menu tidak tersedia.");
            }
            System.out.println();
        }

        sc.close();
    }

    private void tampilkanMenu() {
        System.out.println("===========================================");
        System.out.println("   SISTEM GUDANG INTERNET COMTELINDO");
        System.out.println("===========================================");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Tampilkan Semua Barang");
        System.out.println("3. Cari Barang berdasarkan ID");
        System.out.println("4. Update Barang");
        System.out.println("5. Hapus Barang");
        System.out.println("6. Klaim Garansi");
        System.out.println("7. Hitung Harga Diskon");
        System.out.println("8. Keluar");
        System.out.println("===========================================");
    }

    private void tambahBarang() {
        System.out.println("\n=== TAMBAH BARANG GUDANG ===");
        System.out.println("Jenis barang:");
        System.out.println("1. Barang Umum");
        System.out.println("2. Perangkat Jaringan");
        System.out.println("3. Kabel Jaringan");
        int jenis = Validator.inputPilihanMenu(sc, "Pilih jenis (1-3): ", 1, 3);

        String nama = Validator.inputString(sc, "Nama barang: ");
        String kategori = Validator.inputString(sc, "Kategori: ");
        double harga = Validator.inputDoubleMin(sc, "Harga (Rp): ", 0);
        int stok = Validator.inputIntMin(sc, "Stok: ", 0);

        Barang barangBaru;
        switch (jenis) {
            case 2:
                String merek = Validator.inputString(sc, "Merek: ");
                int garansi = Validator.inputIntMin(sc, "Garansi (bulan): ", 0);
                barangBaru = controller.tambahBarang(nama, kategori, harga, stok, merek, garansi);
                break;
            case 3:
                double panjang = Validator.inputDoubleMin(sc, "Panjang (meter): ", 0.1);
                String jenisKabel = Validator.inputString(sc, "Jenis kabel: ");
                barangBaru = controller.tambahBarang(nama, kategori, harga, stok, panjang, jenisKabel);
                break;
            default:
                barangBaru = controller.tambahBarang(nama, kategori, (int) harga, stok);
        }
        System.out.println("Barang berhasil ditambahkan dengan ID: " + barangBaru.getId());
    }

    private void tampilkanSemuaBarang() {
        if (controller.getDaftarBarang().isEmpty()) {
            System.out.println("Belum ada data barang di gudang.");
            return;
        }
        System.out.println("=====================================================================");
        for (Barang b : controller.getDaftarBarang()) {
            b.tampilkanInfo();
            b.caraPenyimpanan();
            System.out.println("---------------------------------------------------------------------");
        }
    }

    private void cariBarang() {
        System.out.println("\n=== CARI BARANG ===");
        int id = Validator.inputIntMin(sc, "Masukkan ID barang: ", 1);
        Barang barang = controller.cariBarangById(id);
        if (barang == null) {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
        } else {
            barang.tampilkanInfo(true);
            System.out.println();
            barang.tampilkanInfo(false);
        }
    }

    private void updateBarang() {
        System.out.println("\n=== UPDATE BARANG ===");
        tampilkanSemuaBarang();
        int id = Validator.inputIntMin(sc, "Masukkan ID barang yang ingin diupdate: ", 1);
        Barang barang = controller.cariBarangById(id);
        if (barang == null) {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.println("Data saat ini:");
        barang.tampilkanInfo();
        String nama = Validator.inputString(sc, "Nama barang baru: ");
        String kategori = Validator.inputString(sc, "Kategori baru: ");
        double harga = Validator.inputDoubleMin(sc, "Harga baru (Rp): ", 0);
        int stok = Validator.inputIntMin(sc, "Stok baru: ", 0);

        boolean berhasil = controller.updateBarang(id, nama, kategori, harga, stok);
        if (berhasil) {
            System.out.println("Barang berhasil diupdate.");
        } else {
            System.out.println("Gagal mengupdate barang.");
        }
    }

    private void hapusBarang() {
        System.out.println("\n=== HAPUS BARANG ===");
        tampilkanSemuaBarang();
        int id = Validator.inputIntMin(sc, "Masukkan ID barang yang ingin dihapus: ", 1);
        Barang barang = controller.cariBarangById(id);
        if (barang == null) {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            return;
        }
        System.out.print("Yakin ingin menghapus '" + barang.getNamaBarang() + "'? (y/n): ");
        String konfirmasi = sc.nextLine().trim().toLowerCase();
        if (konfirmasi.equals("y")) {
            controller.hapusBarang(id);
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    private void klaimGaransi() {
        System.out.println("\n=== KLAIM GARANSI ===");
        int id = Validator.inputIntMin(sc, "Masukkan ID barang: ", 1);
        Barang barang = controller.cariBarangById(id);
        if (barang == null) {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            return;
        }
        if (barang instanceof Bergaransi) {
            Bergaransi item = (Bergaransi) barang;
            item.klaimGaransi();
        } else {
            System.out.println("Barang ini tidak memiliki garansi.");
        }
    }

    private void hitungDiskon() {
        System.out.println("\n=== HITUNG HARGA DISKON ===");
        int id = Validator.inputIntMin(sc, "Masukkan ID barang: ", 1);
        Barang barang = controller.cariBarangById(id);
        if (barang == null) {
            System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            return;
        }
        if (barang instanceof Diskon) {
            Diskon item = (Diskon) barang;
            double persen = Validator.inputDoubleMin(sc, "Persen diskon (0-100): ", 0);
            double hargaAkhir = item.hitungHargaSetelahDiskon(persen);
            System.out.println("Harga setelah diskon " + persen + "%: Rp" + String.format("%,.0f", hargaAkhir));
        } else {
            System.out.println("Barang ini tidak mendukung diskon.");
        }
    }
}

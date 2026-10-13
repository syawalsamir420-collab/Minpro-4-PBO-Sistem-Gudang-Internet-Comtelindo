package com.mycompany.gudanginternet.main;

import com.mycompany.gudanginternet.controller.BarangController;
import com.mycompany.gudanginternet.view.BarangView;
//  Aslab Sayang Praktikan
// Bismillah 100
// Jangan Lupa kasih bintang 5 buat abang aslab ini
// Thanks bang 
public class MainApp {
    public static void main(String[] args) {
        BarangController controller = new BarangController();

        controller.tambahBarang("Kabel UTP Cat 6", "Kabel", 100.0, 5000, "UTP Cat 6", 200);
        double panjang = 5.0;
        controller.tambahBarang("Modem ZTE F609", "Perangkat Jaringan", 350000, 350000, panjang, "ZTE");
        controller.tambahBarang("Router Mikrotik RB750", "Perangkat Jaringan", 620000, 620000, panjang, "Mikrotik");
        controller.tambahBarang("Kabel Fiber Optik Dropcore", "Kabel", 100.0, 750000, "Fiber Optik", 15);
        controller.tambahBarang("Konektor RJ45", "Aksesoris", 1500, 500);

        BarangView view = new BarangView(controller);
        view.tampilkanMenuUtama();
    }
}

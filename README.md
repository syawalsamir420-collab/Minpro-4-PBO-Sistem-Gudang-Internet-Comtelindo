# Sistem Inventaris Barang Gudang Internet

## 1. Deskripsi Program

Sistem Inventaris Barang Gudang Internet adalah program berbasis Java yang saya kembangkan untuk membantu mengelola data inventaris, kategori, supplier, serta transaksi barang secara terstruktur dan efisien.

Sebelumnya, pencatatan stok dan barang di gudang sering kali saya lakukan secara manual, yang berisiko menimbulkan kesalahan pencatatan, duplikasi data, serta menyulitkan proses pencarian ketika saya memerlukan informasi stok barang secara cepat.

Sistem ini saya buat sebagai solusi untuk memusatkan pencatatan informasi inventaris dalam satu sistem database relasional yang terintegrasi. Program ini berfokus pada pengelolaan data master barang yang meliputi penambahan data, penampilan data, serta integrasi langsung dengan database.

Dalam implementasinya, saya mengembangkan program ini dengan menerapkan arsitektur MVC (Model-View-Controller) serta menghubungkan program Java ke database MySQL (melalui Laragon/phpMyAdmin) menggunakan JDBC, PreparedStatement, dan pola DAO (Data Access Object). Dengan demikian, data barang dapat saya simpan secara permanen di database dan langsung ditampilkan ketika program dijalankan.

## 2. Kebutuhan Sistem

### Data Barang

Saya dapat:

- Melihat daftar seluruh barang langsung dari database.

- Menambahkan barang baru beserta detail lengkapnya.

- Informasi yang saya kelola pada tabel barang:

- ID Barang (Primary Key)

### Nama Barang

- ID Kategori (Relasi ke tabel kategori)

- ID Supplier (Relasi ke tabel supplier)

- Harga Barang

- Stok Barang

- Spesifikasi Khusus

- Cara Penyimpanan

### Data Pendukung (Kategori, Supplier, & Transaksi)

Sistem yang saya buat juga terhubung dengan data referensi pendukung untuk memastikan konsistensi data inventaris:

- Kategori barang (Networking, Kabel, Tools, dll.)

- Supplier atau pemasok perangkat jaringan

- Transaksi Masuk dan Transaksi Keluar untuk pencatatan arus barang

## 3. Struktur Package

### main:

MainApp.java

### view:

BarangView.java

### controller:

BarangController.java

### model:

Barang.java

BarangUmum.java

### dao:

BarangDAO.java

### config:

database.java

Setiap package saya pisahkan fungsinya agar program menjadi lebih teratur, modular, dan mudah dikembangkan. Package main saya gunakan sebagai titik awal (entry point) untuk menjalankan program melalui file MainApp.java.

Package view saya gunakan untuk menampilkan menu antarmuka, menerima masukan dari pengguna, serta menampilkan hasil keluaran program.

Package controller bertindak sebagai perantara antara View, Model, dan DAO bagi saya. Bagian ini menangani logika alur aplikasi serta meneruskan permintaan dari pengguna ke DAO.

Package model merepresentasikan objek dan struktur data entitas barang yang saya gunakan dalam sistem.

Package dao menangani seluruh proses komunikasi saya dengan database, termasuk eksekusi operasi CRUD menggunakan JDBC.

Package config diwakili oleh file database.java yang mengatur konfigurasi koneksi antara aplikasi Java saya dan database MySQL (gudang_internet).

## 4. Alur Program

Program saya mulai dari MainApp.java, kemudian sistem menampilkan menu utama kepada pengguna. Pengguna dapat memilih operasi yang ingin dilakukan, seperti melihat daftar barang atau menambahkan barang baru.

Setelah pengguna memasukkan perintah atau data yang diperlukan, View meneruskan permintaan tersebut ke BarangController.java. Controller kemudian memanggil fungsi yang sesuai pada BarangDAO.java yang saya buat.

BarangDAO mengakses database MySQL melalui koneksi yang disediakan oleh database.java. Proses akses database ini saya lakukan menggunakan JDBC dan PreparedStatement guna mengeksekusi perintah SQL secara aman dengan parameter terpisah.

Data yang berhasil saya ambil dari database (ResultSet) dikembalikan melalui DAO ke Controller, lalu disajikan kepada pengguna melalui View. Sementara itu, proses penambahan data baru saya teruskan secara langsung ke tabel barang di database MySQL.

## 5. Penerapan Encapsulation

Encapsulation saya terapkan untuk membungkus data pada objek agar atribut tidak dapat diakses atau diubah secara langsung sembarangan dari luar class.

Atribut privat pada class Barang saya akses dan modifikasi melalui metode getter dan setter. Hal ini saya lakukan untuk memastikan integritas data tetap terjaga dengan baik.

Penerapan ini memudahkan saya dalam melakukan validasi nilai atribut seperti harga atau stok agar tidak bernilai negatif, sehingga pengelolaan objek menjadi lebih terkontrol dan aman.

## 6. Penerapan DAO dan PreparedStatement

Pola DAO (Data Access Object) saya gunakan untuk memisahkan logika akses database dari kode antarmuka dan kontrol alur program utama. Dengan pola ini, saya tidak perlu menuliskan sintaks SQL langsung di dalam Controller.

JDBC (Java Database Connectivity) bersama PreparedStatement saya gunakan untuk menghubungkan aplikasi Java dengan database MySQL di phpMyAdmin.

PreparedStatement memastikan parameter input dari aplikasi dikirim terpisah dari struktur kueri SQL, sehingga sangat efektif bagi saya dalam mencegah risiko SQL Injection dibandingkan penggabungan string secara manual.

## 7. Dummy Data dan Integrasi Database

Sesuai dengan spesifikasi sistem, database MySQL bernama gudang_internet saya persiapkan dengan struktur relasional yang mencakup tabel barang, kategori, dan supplier.

Data awal (dummy data) sebanyak beberapa baris saya masukkan langsung ke dalam database melalui file .sql atau kueri SQL di phpMyAdmin, bukan saya tulis di dalam kode Java (hardcode).

Hal ini memastikan bahwa ketika fitur baca data (read) saya jalankan pertama kali oleh program, data langsung tampil secara utuh tanpa perlu melakukan input manual dari awal.

## 8. Kesimpulan

Sistem Inventaris Barang Gudang Internet berhasil saya kembangkan dengan mengintegrasikan arsitektur MVC, pola DAO, serta koneksi JDBC ke database MySQL (Laragon/phpMyAdmin).

Dengan memanfaatkan PreparedStatement, komunikasi data antara aplikasi Java saya di NetBeans dan phpMyAdmin berjalan lancar, aman, dan real-time. Struktur program yang terorganisir membuat sistem ini andal bagi saya untuk mengelola inventaris perangkat jaringan secara profesional.

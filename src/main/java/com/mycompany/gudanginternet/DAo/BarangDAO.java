package DAo;



import com.mycompany.gudanginternet.model.Barang;
import com.mycompany.gudanginternet.model.BarangUmum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {
    public List<Barang> getSemuaBarang() {
    List<Barang> listBarang = new ArrayList<>();
    try {
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        
        String query = "SELECT * FROM barang";
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            int id = rs.getInt("id_barang");
            String nama = rs.getString("nama_barang");
            String kategori = rs.getString("kategori");
            double harga = rs.getDouble("harga");
            int stok = rs.getInt("stok");
            
            Barang b = new BarangUmum(id, nama, kategori, harga, stok);
            listBarang.add(b);
        }
        rs.close();
        stmt.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listBarang;
}

public void tambahBarang(String nama, int idKategori, int idSupplier, double harga, int stok, String spesifikasiKhusus, String caraPenyimpanan) {
    try {
        Connection conn = Database.connect();
        String query = "INSERT INTO barang (nama_barang, id_kategori, id_supplier, harga, stok, spesifikasi_khusus, cara_penyimpanan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nama);
        pstmt.setInt(2, idKategori);
        pstmt.setInt(3, idSupplier);
        pstmt.setDouble(4, harga);
        pstmt.setInt(5, stok);
        pstmt.setString(6, spesifikasiKhusus);
        pstmt.setString(7, caraPenyimpanan);
        
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Data berhasil disimpan ke database via DAO & Prepared Statement.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
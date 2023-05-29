package model;

import com.mysql.cj.jdbc.Blob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class lapora {
    private static String id, idPengajuan;
    Blob blob;
    
    dbconnect koneksi = new dbconnect();
    boolean queryResult;
     
    public void laporanGet(String id){
        koneksi.connect();
//        System.out.println("idnec"+dataAkun.getId());
        try {
//            System.out.println(" ukmdb");
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT * FROM laporanpenggunaandana"));
//            System.out.println("ukkk");
            while (rs.next()) {
                System.out.println("while");
                setId(rs.getString(1));
                setIdPengajuan(rs.getString("idPengajuan"));
                setIdPengajuan(rs.getString("idPengajuan"));
//                System.out.println("model"+getNamaUsaha());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean upLaporan(String filename, String id) throws FileNotFoundException{
        System.out.println("ubah gambar terpanggil");
        koneksi.connect();
        try {
            System.out.println(filename);
            System.out.println(dataAkun.getId());
            System.out.println(pengajuan.getId());
            String sql = "insert into laporanpenggunaandana (upLaporan, idPengguna, idPengajuan) VALUES (?, ?, ?)";
               PreparedStatement ps = koneksi.con.prepareStatement(sql);
               InputStream is = new FileInputStream(new File(filename));
               ps.setBlob(1, is);
               ps.setString(2, dataAkun.getId());
               ps.setString(3, pengajuan.getId());
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data berhasil tersimpan");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            queryResult = false;
        }
        return queryResult;
    }
    
    public void tampilLap(String id) throws FileNotFoundException{
        koneksi.connect();
//        System.out.println("idnec"+dataAkun.getId());
        try {
//            System.out.println(" ukmdb");
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT * FROM laporanpenggunaandana where idPengajuan='%s'", pengajuan.getId()));
//            System.out.println("ukkk");
            while (rs.next()) {
                setIdPengajuan(rs.getString("idPengajuan"));
                String filename = "Laporan dana usaha "+ukm.getNamaUsaha()+".pdf";
                blob = (Blob) rs.getBlob("upLaporan");
                InputStream is = blob.getBinaryStream();
                FileOutputStream fos = new FileOutputStream("C:\\Users\\user\\Downloads"+ "\\" + filename);
                int b = 0;
                while ((b = is.read()) != -1){
                    fos.write(b); 
                }
            }
        } 
        catch (IOException e){
            e.getMessage (); e.printStackTrace(); 
            System.out.println(e); 
        } 
        catch (SQLException e){
            e.getMessage (); e.printStackTrace(); 
            System.out.println(e); 
            }
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        lapora.id = id;
    }

    public static String getIdPengajuan() {
        return idPengajuan;
    }

    public static void setIdPengajuan(String idPengajuan) {
        lapora.idPengajuan = idPengajuan;
    }

    
    
}

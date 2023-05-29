
package model;

import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;

public class pengajuan {
    private static String id, jmlhPinjam, wktPelunasan, wktCicilan, jaminan, buktiKepemilikan, alasanPeminjaman, status, pembuat, idSementara, jmlh;
    dbconnect koneksi = new dbconnect();
    boolean queryResult; 
    
    public void jmlh(){
        koneksi.connect();
        try {
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT count(*) FROM dataPengajuan"));
            while (rs.next()) {                
                setJmlh(rs.getString("count(*)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void pengajuanDB(String id){
        koneksi.connect();
        try {
//            System.out.println("masuk ajuan");
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT * FROM dataPengajuan where idPengguna='%s'", dataAkun.getId()));
            while (rs.next()) {
//                System.out.println("while");
                setId(rs.getString(1));
                setJmlhPinjam(rs.getString(2));
                setWktPelunasan(rs.getString(3));
                setWktCicilan(rs.getString(4));
                setJaminan(rs.getString(5));
                setBuktiKepemilikan(rs.getString(6));
                setPembuat(rs.getString(7));
                setAlasanPeminjaman(rs.getString(8));
                setStatus(rs.getString(11));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean addPengajuan(String jmlhPinjam, String  wktPelunasan, String  wktCicilan, String  jaminan, String  buktiKepemilikan, String alasanPeminjaman){
        boolean hasil = true;
        System.out.println("add user terpanggil");
        koneksi.connect();
        try {
//            System.out.println("masuk");
            Statement stmt = koneksi.con.createStatement();
            queryResult = stmt.executeUpdate(String.format(
                    "insert into dataPengajuan (jumlahPinjaman, waktuPelunasan, waktuCicilan, jaminan, buktiKepemilikan, alasanPeminjaman, idPengguna) values('%s', '%s', '%s', '%s', '%s', '%s', %s)", 
                    jmlhPinjam, wktPelunasan, wktCicilan, jaminan, buktiKepemilikan, alasanPeminjaman, dataAkun.getId())) > 0;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            hasil = false;
        }
        pengajuanDB(dataAkun.getId());
        return hasil;
    }
    
    public boolean verif(String Status, String id){
        koneksi.connect();
        try {
            System.out.println("masuk ubah");
            Statement stmt = koneksi.con.createStatement();
            queryResult = stmt.executeUpdate(String.format(
                    "UPDATE dataPengajuan SET statusVerif='%s' WHERE dataPengajuan.idPengajuan='%s'", 
                    Status, id)) > 0;
            setStatus(Status);
            System.out.println(queryResult);
        } catch (SQLException e) {
            System.out.println("catch");
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            queryResult = false;
        }
        pengajuanDB(pengajuan.getId());
        return queryResult;
    }

    public static String getPembuat() {
        return pembuat;
    }

    public static void setPembuat(String pembuat) {
        pengajuan.pembuat = pembuat;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        pengajuan.status = status;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        pengajuan.id = id;
    }

    public static String getJmlhPinjam() {
        return jmlhPinjam;
    }

    public static void setJmlhPinjam(String jmlhPinjam) {
        pengajuan.jmlhPinjam = jmlhPinjam;
    }

    public static String getWktPelunasan() {
        return wktPelunasan;
    }

    public static void setWktPelunasan(String wktPelunasan) {
        pengajuan.wktPelunasan = wktPelunasan;
    }

    public static String getWktCicilan() {
        return wktCicilan;
    }

    public static void setWktCicilan(String wktCicilan) {
        pengajuan.wktCicilan = wktCicilan;
    }

    public static String getJaminan() {
        return jaminan;
    }

    public static void setJaminan(String jaminan) {
        pengajuan.jaminan = jaminan;
    }

    public static String getBuktiKepemilikan() {
        return buktiKepemilikan;
    }

    public static void setBuktiKepemilikan(String buktiKepemilikan) {
        pengajuan.buktiKepemilikan = buktiKepemilikan;
    }

    public static String getAlasanPeminjaman() {
        return alasanPeminjaman;
    }

    public static void setAlasanPeminjaman(String alasanPeminjaman) {
        pengajuan.alasanPeminjaman = alasanPeminjaman;
    }

    public static String getIdSementara() {
        return idSementara;
    }

    public static void setIdSementara(String idSementara) {
        pengajuan.idSementara = idSementara;
    }
    
    public static String getJmlh() {
        return jmlh;
    }

    public static void setJmlh(String jmlh) {
        pengajuan.jmlh = jmlh;
    }
}

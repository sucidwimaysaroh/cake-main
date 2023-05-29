package model;

import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class bantuan {
    private static String id, idPengguna, idPengajuan, jmlh;
    private static int jmlhUang;
    
    dbconnect koneksi = new dbconnect();
    boolean queryResult;
  
    public void jmlh(){
        koneksi.connect();
        try {
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT count(*) FROM danaBantuan"));
            while (rs.next()) {                
                setJmlh(rs.getString("count(*)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ukmDB(String id){
        koneksi.connect();
        System.out.println("idnec"+dataAkun.getId());
        try {
            System.out.println(" ukmdb");
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT * FROM danaBantuan where idPengguna='%s'", dataAkun.getId()));
            System.out.println("ukkk");
            while (rs.next()) {
                System.out.println("while");
                setId(rs.getString(1));
                setJmlhUang(rs.getInt("jumlahUang"));
//                System.out.println("model"+getNamaUsaha());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean addBantuan(String jmlh){
        System.out.println(pengajuan.getId());
        boolean hasil = true;
        System.out.println("add bantuan terpanggil");
        koneksi.connect();
        try {
            System.out.println("masuk");
            Statement stmt = koneksi.con.createStatement();
            queryResult = stmt.executeUpdate(String.format(
                    "insert into danaBantuan (jumlahUang, idPengajuan, idPengguna) values(%s, '%s', '%s')", 
                    jmlh, pengajuan.getId(), dataAkun.getId())) > 0;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            hasil = false;
        }
        return hasil;
    }
    
    public void getBantuan(String id){
        koneksi.connect();
        System.out.println("idAjuan"+pengajuan.getId());
        try{
            Statement stmt = koneksi.con.createStatement();
            String sql     = "SELECT idPengajuan, SUM(jumlahUang) FROM `danabantuan` WHERE idPengajuan ='"+pengajuan.getId()+"' ";
            ResultSet rs   = stmt.executeQuery(sql);
            while(rs.next ()){
                setIdPengajuan(rs.getString("idPengajuan"));
                System.out.println("id da"+getIdPengajuan());
                if (pengajuan.getId().equals(getIdPengajuan())) {
//                    System.out.println("leo");
                    setJmlhUang(rs.getInt("SUM(jumlahUang)"));
                } else {
                    setJmlhUang(0);
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage() );
        }
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        bantuan.id = id;
    }

    public static int getJmlhUang() {
        return jmlhUang;
    }

    public static void setJmlhUang(int jmlhUang) {
        bantuan.jmlhUang = jmlhUang;
    }

    public static String getIdPengguna() {
        return idPengguna;
    }

    public static void setIdPengguna(String idPengguna) {
        bantuan.idPengguna = idPengguna;
    }

    public static String getIdPengajuan() {
        return idPengajuan;
    }

    public static void setIdPengajuan(String idPengajuan) {
        bantuan.idPengajuan = idPengajuan;
    }

    public static String getJmlh() {
        return jmlh;
    }

    public static void setJmlh(String jmlh) {
        bantuan.jmlh = jmlh;
    }

    
    
}

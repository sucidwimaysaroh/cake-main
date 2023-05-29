/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author user
 */
public class ukm {
    private static String id, idPengguna,namaUsaha, thnBerdiri, jnsUsaha, keuangan, proposal, status, jmlh, idCek;

    public static String getIdCek() {
        return idCek;
    }

    public static void setIdCek(String idCek) {
        ukm.idCek = idCek;
    }
    dbconnect koneksi = new dbconnect();
    boolean queryResult;
    Blob blob;
    
    public void jmlh(){
        koneksi.connect();
        try {
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT count(*) FROM dataukm"));
            while (rs.next()) {                
                setJmlh(rs.getString("count(*)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ukmDB(String id){
        koneksi.connect();
//        System.out.println("idnec"+dataAkun.getId());
        try {
//            System.out.println(" ukmdb");
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT `idUkm`, `namaUsaha`, `tahunBerdiri`, `jenisUsaha`, `idPengguna`, `statusVerif` FROM `dataukm` where idPengguna='%s'", dataAkun.getId()));
//            System.out.println("ukkk");
            
            while (rs.next()) {
                System.out.println("while");
                setId(rs.getString("idUkm"));
                setNamaUsaha(rs.getString("namaUsaha"));
                setThnBerdiri(rs.getString("tahunBerdiri"));
                setJnsUsaha(rs.getString("jenisUsaha"));
//                setKeuangan(rs.getString(5));
                setIdPengguna(rs.getString("idPengguna"));
                setStatus(rs.getString("statusVerif"));
//                System.out.println("model"+getNamaUsaha());
            }
        } catch (SQLException e) {
//            System.out.println("hmm");
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean addUkm(String namaUsaha, String thnBerdiri, String jnsUsaha, String filename) throws FileNotFoundException{
        koneksi.connect();
        try {
            System.out.println(filename);
            System.out.println(dataAkun.getId());
            System.out.println(pengajuan.getId());
            String sql = "insert into dataukm (namaUsaha, tahunBerdiri, jenisUsaha, riwayatKeuangan, idPengguna) VALUES (?, ?, ?, ?, ?)";
               PreparedStatement ps = koneksi.con.prepareStatement(sql);
               InputStream is = new FileInputStream(new File(filename));
               ps.setBlob(4, is);
               ps.setString(1, namaUsaha);
               ps.setString(2, thnBerdiri);
               ps.setString(3, jnsUsaha);
               ps.setString(5, dataAkun.getId());
               ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            queryResult = false;
        }
        return queryResult;
    }
    
    public boolean ubahUkm(String namaUsaha, String thnBerdiri, String jnsUsaha, String filename) throws FileNotFoundException{
        koneksi.connect();
        try {
            System.out.println(filename);
            System.out.println(dataAkun.getId());
            System.out.println(pengajuan.getId());
            String sql = "UPDATE `dataUkm` SET `namaUsaha`= ? ,`tahunBerdiri`= ? ,`jenisUsaha`= ? ,`riwayatKeuangan`= ? WHERE idUkm= ? ";
               PreparedStatement ps = koneksi.con.prepareStatement(sql);
               InputStream is = new FileInputStream(new File(filename));
               ps.setBlob(4, is);
               ps.setString(1, namaUsaha);
               ps.setString(2, thnBerdiri);
               ps.setString(3, jnsUsaha);
               ps.setString(5, ukm.getId());
               ps.executeUpdate();
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
                    "SELECT riwayatKeuangan FROM dataUkm du JOIN datapengajuan dp on du.idPengguna=dp.idPengguna where idPengajuan='%s'", pengajuan.getPembuat()));
//            System.out.println("ukkk");
            while (rs.next()) {
//                setIdPengajuan(rs.getString("idPengajuan"));
                String filename = "Laporan keuangan "+ukm.getNamaUsaha()+".pdf";
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
    
    public void ukmAd(String id){
        koneksi.connect();
//        System.out.println("idnec"+dataAkun.getId());
        try {
//            System.out.println(" ukmdb");
            Statement stmt = koneksi.con.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT `idUkm`, `namaUsaha`, `tahunBerdiri`, `jenisUsaha`, `idPengguna`, `statusVerif` FROM `dataUkm` where idUkm='%s'", getIdCek()));
//            System.out.println("ukkk");
            
            while (rs.next()) {
                System.out.println("while");
                setId(rs.getString("idUkm"));
                setNamaUsaha(rs.getString("namaUsaha"));
                setThnBerdiri(rs.getString("tahunBerdiri"));
                setJnsUsaha(rs.getString("jenisUsaha"));
//                setKeuangan(rs.getString(5));
                setIdPengguna(rs.getString("idPengguna"));
                setStatus(rs.getString("statusVerif"));
//                System.out.println("model"+getNamaUsaha());
            }
        } catch (SQLException e) {
//            System.out.println("hmm");
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    public boolean addUkm(String namaUsaha, String thnBerdiri, String jnsUsaha, String keuangan){
//        boolean hasil = true;
////        System.out.println("add user terpanggil");
//        koneksi.connect();
//        try {
////            System.out.println("masuk");
//            Statement stmt = koneksi.con.createStatement();
//            queryResult = stmt.executeUpdate(String.format(
//                    "insert into dataukm (namaUsaha, tahunBerdiri, jenisUsaha, riwayatKeuangan, idPengguna) values('%s', '%s', '%s', '%s', '%s')", 
//                    namaUsaha, thnBerdiri, jnsUsaha, keuangan, dataAkun.getId())) > 0;
//        } catch (SQLException e) {
//            System.out.println(e);
//            JOptionPane.showMessageDialog(null, e);
//            hasil = false;
//        }
//        return hasil;
//    }
    
//    public boolean ubahUkm(String namaUsaha, String thnBerdiri, String jnsUsaha, String keuangan){
//        koneksi.connect();
//        try {
////            System.out.println("masuk ubah");
//            Statement stmt = koneksi.con.createStatement();
//            queryResult = stmt.executeUpdate(String.format(
//                    "UPDATE `dataUkm` SET `namaUsaha`='%s',`tahunBerdiri`='%s',`jenisUsaha`='%s',`riwayatKeuangan`='%s' WHERE idUkm='%s'", 
//                    namaUsaha, thnBerdiri, jnsUsaha, keuangan, ukm.getId())) > 0;
////            System.out.println(queryResult);
//        } catch (SQLException e) {
//            System.out.println(e);
//            JOptionPane.showMessageDialog(null, e);
//            queryResult = false;
//        }
//        ukmDB(ukm.getId());
//        return queryResult;
//    }
    
    public boolean verif(String Status){
        koneksi.connect();
        try {
//            System.out.println("masuk ubah");
            Statement stmt = koneksi.con.createStatement();
            queryResult = stmt.executeUpdate(String.format(
                    "UPDATE `dataUkm` SET `statusVerif`='%s' WHERE dataUkm.idUkm='%s'", 
                    Status, id)) > 0;
//            System.out.println(queryResult);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            queryResult = false;
        }
        ukmDB(pengajuan.getId());
        return queryResult;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        ukm.id = id;
    }

    public static String getNamaUsaha() {
        return namaUsaha;
    }

    public static String getIdPengguna() {
        return idPengguna;
    }

    public static void setIdPengguna(String idPengguna) {
        ukm.idPengguna = idPengguna;
    }

    public static void setNamaUsaha(String namaUsaha) {
        ukm.namaUsaha = namaUsaha;
    }

    public static String getThnBerdiri() {
        return thnBerdiri;
    }

    public static void setThnBerdiri(String thnBerdiri) {
        ukm.thnBerdiri = thnBerdiri;
    }

    public static String getJnsUsaha() {
        return jnsUsaha;
    }

    public static void setJnsUsaha(String jnsUsaha) {
        ukm.jnsUsaha = jnsUsaha;
    }

    public static String getKeuangan() {
        return keuangan;
    }

    public static void setKeuangan(String keuangan) {
        ukm.keuangan = keuangan;
    }

    public static String getProposal() {
        return proposal;
    }

    public static void setProposal(String proposal) {
        ukm.proposal = proposal;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        ukm.status = status;
    }

    public static String getJmlh() {
        return jmlh;
    }

    public static void setJmlh(String jmlh) {
        ukm.jmlh = jmlh;
    }
    
}

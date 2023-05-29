/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.cProfil;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class dataAkun {
    private static String  id, role, username, password, namauser, noKtp, noRek, namaRek, email, bank, jenisKelamin, noTelp, tanggalLahir;
    String sql;
    dbconnect koneksi = new dbconnect();
    boolean queryResult;
    
    public void akunUser(String username, String password) {
//        int role;
        koneksi.connect();
        try {
            Statement stat = koneksi.con.createStatement();
            sql = "SELECT * FROM datapengguna WHERE username='"+username+"' AND password='"+password+"'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
//                System.out.println(rs.getString(3)+" "+rs.getString(4));
                dataAkun.setId(String.valueOf(rs.getInt(1)));
                dataAkun.setUsername(rs.getString(3));
                dataAkun.setPassword(rs.getString(4));
                dataAkun.setRole(String.valueOf(rs.getInt(2)));
                dataAkun.setNamauser(rs.getString(5));
                dataAkun.setNoKtp(rs.getString(6));
                dataAkun.setNoRek(rs.getString(7));
                dataAkun.setNamaRek(rs.getString(8));
                dataAkun.setEmail(rs.getString(9));
                dataAkun.setBank(rs.getString(10));
                dataAkun.setJenisKelamin(rs.getString(11));
                dataAkun.setNoTelp(rs.getString(12));
                dataAkun.setTanggalLahir(rs.getString(13));
            }
            System.out.println("idnya"+getId());
//            System.out.println(rs);
        } catch (SQLException e) {
            System.out.println("Database notfound");
        }
    }
  
    public boolean addUser(int role, String username, String password, String namaUser, String noKtp,String noRek,String namaRek, String email, String bank, String jenisKelamin, String noTelp, String tanggal){
        boolean hasil = true;
        koneksi.connect();
        try {
//            System.out.println("masuk add");
            Statement stmt = koneksi.con.createStatement();
            queryResult = stmt.executeUpdate(String.format(
                    "insert into dataPengguna (role, username, password, namaUser, noKtp, noRek, namaRek, email, bank, jenisKelamin, noTelp, tanggalLahir) values(%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
                    role, username, password, namaUser, noKtp, noRek, namaRek, email, bank, jenisKelamin, noTelp, tanggal)) > 0;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            hasil = false;
        }
        return hasil;
    }
    
    public boolean ubahUser(String username, String password, String namaUser, String noKtp,String noRek,String namaRek, String email, String bank, String jenisKelamin, String noTelp, String tanggal){
        koneksi.connect();
        try {
//            System.out.println("masuk ub

            Statement stmt = koneksi.con.createStatement();
            queryResult = stmt.executeUpdate(String.format(
                    "UPDATE `dataPengguna` SET `username`='%s',`password`='%s',`namaUser`='%s',`noKtp`='%s',`noRek`='%s',`namaRek`='%s',`email`='%s',`bank`='%s',`jenisKelamin`='%s',`noTelp`='%s',`tanggalLahir`='%s' WHERE idPengguna ='%s'", 
                    username, password, namaUser, noKtp, noRek, namaRek, email, bank, jenisKelamin, noTelp, tanggal, getId())) > 0;
//            System.out.println(queryResult);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
            queryResult = false;
        }
        akunUser(getUsername(), getPassword());
        return queryResult;
    }
    
    public void user() {
//        int role;
        koneksi.connect();
        try {
            Statement stat = koneksi.con.createStatement();
            sql = "SELECT * FROM datapengguna WHERE username='"+username+"' AND password='"+password+"'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
//                System.out.println(rs.getString(3)+" "+rs.getString(4));
                dataAkun.setId(String.valueOf(rs.getInt(1)));
                dataAkun.setUsername(rs.getString(3));
                dataAkun.setPassword(rs.getString(4));
                dataAkun.setRole(String.valueOf(rs.getInt(2)));
                dataAkun.setNamauser(rs.getString(5));
                dataAkun.setNoKtp(rs.getString(6));
                dataAkun.setNoRek(rs.getString(7));
                dataAkun.setNamaRek(rs.getString(8));
                dataAkun.setEmail(rs.getString(9));
                dataAkun.setBank(rs.getString(10));
                dataAkun.setJenisKelamin(rs.getString(11));
                dataAkun.setNoTelp(rs.getString(12));
                dataAkun.setTanggalLahir(rs.getString(13));
            }
            System.out.println("idnya"+getId());
//            System.out.println(rs);
        } catch (SQLException e) {
            System.out.println("Database notfound");
        }
    }
    
    public static void setEmail(String email) {
        dataAkun.email = email;
    }

    public static void setId(String id) {
        dataAkun.id = id;
    }

    public static void setRole(String role) {
        dataAkun.role = role;
    }

    public static void setUsername(String username) {
        dataAkun.username = username;
    }

    public static void setPassword(String password) {
        dataAkun.password = password;
    }

    public static void setNamauser(String namauser) {
        dataAkun.namauser = namauser;
    }

    public static void setNoKtp(String noKtp) {
        dataAkun.noKtp = noKtp;
    }

    public static void setNoRek(String noRek) {
        dataAkun.noRek = noRek;
    }

    public static void setNamaRek(String namaRek) {
        dataAkun.namaRek = namaRek;
    }
    
    public static void setBank(String bank) {
        dataAkun.bank = bank;
    }

    public static String getJenisKelamin() {
        return jenisKelamin;
    }

    public static void setJenisKelamin(String jenisKelamin) {
        dataAkun.jenisKelamin = jenisKelamin;
    }

    public static String getNoTelp() {
        return noTelp;
    }

    public static void setNoTelp(String noTelp) {
        dataAkun.noTelp = noTelp;
    }


    public static void setTanggalLahir(String tanggalLahir) {
        dataAkun.tanggalLahir = tanggalLahir;
    }

    public static String getId() {
        return id;
    }

    public static String getRole() {
        return role;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getNamauser() {
        return namauser;
    }

    public static String getNoKtp() {
        return noKtp;
    }

    public static String getNoRek() {
        return noRek;
    }

    public static String getNamaRek() {
        return namaRek;
    }

    public static String getEmail() {
        return email;
    }

    public static String getBank() {
        return bank;
    }
    
    public static String getTanggalLahir() {
        return tanggalLahir;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.dataAkun;
import model.pengajuan;
import model.ukm;
import view.Login;
import view.awal;
import view.dashboard;
import view.dashboardAdmin;

public class cLogin {
    Login view;
    dataAkun akun = new dataAkun();
    ukm ukmModel = new ukm();
    pengajuan ajuanModel = new pengajuan();
//    Connection con;
//    Statement stat;
//    ResultSet rs;
//    String sql;
//    dbconnect koneksi = new dbconnect();
    
    public cLogin(Login view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogin(new tmblLogin());
        this.view.klikKembali(new tmblKembali());
//        if (dataAkun.getId().equals("3")) {
//            System.out.println("admin");
//        }
//        else{


//        }

//        koneksi.connect();
//        con = koneksi.con;
//        stat = koneksi.stmt;
    }
    
//    public void akunUser(String username, String password) {
////        int role;
//        koneksi.connect();
//        try {
//            Statement stat = koneksi.con.createStatement();
//            sql = "SELECT * FROM datapengguna WHERE username='"+username+"' AND password='"+password+"'";
//            rs = stat.executeQuery(sql);
//            while (rs.next()) {                
//                System.out.println(rs.getString(3)+" "+rs.getString(4));
//                dataAkun.setId(String.valueOf(rs.getInt(1)));
//                dataAkun.setUsername(rs.getString(3));
//                dataAkun.setPassword(rs.getString(4));
//                dataAkun.setRole(String.valueOf(rs.getInt(2)));
//                dataAkun.setNamauser(rs.getString(5));
//                dataAkun.setNoKtp(rs.getString(6));
//                dataAkun.setNoRek(rs.getString(7));
//                dataAkun.setNamaRek(rs.getString(8));
//                dataAkun.setEmail(rs.getString(9));
//                dataAkun.setBank(rs.getString(10));
//                dataAkun.setJenisKelamin(rs.getString(11));
//                dataAkun.setNoTelp(rs.getString(12));
//                dataAkun.setTanggalLahir(rs.getString(13));
//            }
//            System.out.println(rs);
//        } catch (SQLException e) {
//            System.out.println("Database notfound");
//        }
//    }
    
    public void login(){
        int role;
        akun.akunUser(view.getUsername().getText(),view.getPass().getText());
        if(view.getUsername().getText().equals(dataAkun.getUsername()) && view.getPass().getText().equals(dataAkun.getPassword()) && dataAkun.getRole().equalsIgnoreCase("1")){
            controller.cDashboard home = new controller.cDashboard(new dashboard());
            view.setVisible(false);
            JOptionPane.showMessageDialog(null, "Selamat datang "+dataAkun.getNamauser()+"");
        } else if (view.getUsername().getText().equals(dataAkun.getUsername()) && view.getPass().getText().equals(dataAkun.getPassword()) && dataAkun.getRole().equalsIgnoreCase("3")) {
            controller.cDashboardAdmin homeAdmin = new controller.cDashboardAdmin(new dashboardAdmin());
            view.setVisible(false);
            JOptionPane.showMessageDialog(null, "Selamat datang Admin");
        } else if (view.getUsername().getText().equals(dataAkun.getUsername()) && view.getPass().getText().equals(dataAkun.getPassword()) && dataAkun.getRole().equalsIgnoreCase("2")) {
            controller.cDashboard home = new controller.cDashboard(new dashboard());
            view.setVisible(false);
            JOptionPane.showMessageDialog(null, "Selamat datang "+dataAkun.getNamauser()+"");
            view.setVisible(false);
        } else if (view.getUsername().getText().equals("") && view.getPass().getText().equals("")){
            JOptionPane.showMessageDialog(null, "username atau password harap diisi");
        } else {
            JOptionPane.showMessageDialog(null, "username atau password tidak ditemukan");
        }
    }
    
    private class tmblLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            akunUser();
            login();
            System.out.println("wwww"+dataAkun.getId());
            ukmModel.ukmDB(dataAkun.getId());
            ajuanModel.pengajuanDB(dataAkun.getId());
        }
    }

    private class tmblKembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }
 
}

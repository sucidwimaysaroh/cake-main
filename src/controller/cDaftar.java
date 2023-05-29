/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import view.Login;
import view.daftar;
import view.awal;
import model.dataAkun;
import model.dbconnect;

public class cDaftar {
    daftar view;
    dataAkun akunModel = new dataAkun();
    dbconnect koneksi = new dbconnect();
    boolean queryResult;

    public cDaftar(daftar view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikDaftar(new tmblDaftar());
        this.view.klikKembali(new tmblKembali());
    }
    
    private class tmblKembali implements ActionListener {

        public tmblKembali() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }
//    public void addUser(int role, String username, String password, String namaUser, String noKtp,String noRek,String namaRek, String email, String bank, String jenisKelamin, String noTelp, String date){
//        System.out.println("add user terpanggil");
//        koneksi.connect();
//        try {
//            System.out.println("masuk");
//            Statement stmt = koneksi.con.createStatement();
//            queryResult = stmt.executeUpdate(String.format(
//                    "insert into dataPengguna (role, username, password, namaUser, noKtp, noRek, namaRek, email, bank, jenisKelamin, noTelp, tanggalLahir) values(%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')", 
//                    role, username, password, namaUser, noKtp, noRek, namaRek, email, bank, jenisKelamin, noTelp, date)) > 0;
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

    private class tmblDaftar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getNama().equals("") || view.getUsername().equals("")&& view.getPasswo().equals("")&& 
                view.getNoKtp().equals("") && view.getNoRek().equals("") && view.getNamaRek().equals("") && 
                view.getNoTelp().equals("") && view.getBoxBank().equals("") && view.getJenisK().equals("") &&
                view.getEmail().equals("") && view.getTanggal().equals("")) 
            {
                JOptionPane.showMessageDialog(null, "Semua Form Wajib Diisi");
            } else {
                System.out.println("sukses");
                if(akunModel.addUser(view.getRole().getSelectedIndex(), view.getUsername().getText(), view.getPasswo().getText(), view.getNama().getText(), view.getNoKtp().getText(), view.getNoRek().getText(), view.getNamaRek().getText(), view.getEmail().getText(), (String) view.getBoxBank().getSelectedItem(), (String) view.getJenisK().getSelectedItem(), view.getNoTelp().getText(), view.getTanggal())){
                    JOptionPane.showMessageDialog(null, "berhasil daftar");
                view.setVisible(false);
                controller.cLogin login = new controller.cLogin(new Login());
                }
                
            }
        }
    }
}

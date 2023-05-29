/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.dataAkun;
import model.dbconnect;
import view.ajukan;
import view.awal;
import view.beriP;
import view.dashboard;
import view.profil;
import view.profillanjutan;
import java.sql.Statement;
import model.pengajuan;
import view.ajukanlama;

public class cProfil {
    profil view;
    dataAkun akunModel = new dataAkun();
    dbconnect koneksi = new dbconnect();
    boolean queryStmt;
    
    public cProfil(profil view){
        this.view = view;
        this.view.setVisible(true);
        bio();
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikAjukan(new tmblAjukan());
        this.view.klikBeri(new tmblBeri());
        this.view.klikLanjut(new tmblLanjutP());
        this.view.klikSimpan(new tmblSimpan());
    }
    public void bio() {
//        String id = dataAkun.getId();
//        System.out.println(id);
        view.getUsername().setText(dataAkun.getUsername());
        view.getNama().setText(dataAkun.getNamauser());
        view.getNamarek().setText(dataAkun.getNamaRek());
        view.getPassw().setText(dataAkun.getPassword());
        view.getNoktp().setText(dataAkun.getNoKtp());
        view.getNorek().setText(dataAkun.getNoRek());
        view.getEmail().setText(dataAkun.getEmail());
        view.getBank().setSelectedItem(dataAkun.getBank());
        view.getJnsKel().setSelectedItem(dataAkun.getJenisKelamin());
        view.getNamaRek().setText(dataAkun.getNamaRek());
        view.getNoRek().setText(dataAkun.getNoRek());
        view.getNoTelp().setText(dataAkun.getNoTelp());
        view.getTglLahir().setDateFormatString(dataAkun.getTanggalLahir());
    }
    

    
    private class tmblSimpan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            akunModel.ubahUser(view.getUsername().getText(), view.getPassw().getText(), view.getNama().getText(), view.getNoktp().getText(), view.getNoRek().getText(), view.getNamaRek().getText(), view.getEmail().getText(), (String) view.getBank().getSelectedItem(), (String) view.getJnsKel().getSelectedItem(), view.getNoTelp().getText(), view.getTanggal());
            controller.cProfil profil = new controller.cProfil(new profil());
            view.setVisible(false);
        }
    }
    
    private class tmblLanjutP implements ActionListener {

        public tmblLanjutP() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cProfilLanjut profillanjutan = new controller.cProfilLanjut(new profillanjutan());
            view.setVisible(false);
        }
    }

    private class tmblLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }
    
    private class tmblDashboard implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cDashboard dashboard = new controller.cDashboard(new dashboard());
            view.setVisible(false);
        }
    }
    
    private class tmblAjukan implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (pengajuan.getPembuat() == null) {
                controller.cAjukan ajukan = new controller.cAjukan(new ajukan());
                view.setVisible(false);
//                System.out.println("masok pak");
            } else {
                controller.cAjuLama ajukanlama = new controller.cAjuLama(new ajukanlama());
                view.setVisible(false);
//                System.out.println("masok else ");
            }
        }
    }
            
    private class tmblBeri implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cBeri beri = new controller.cBeri(new beriP());
            view.setVisible(false);
        }
    }                  
}

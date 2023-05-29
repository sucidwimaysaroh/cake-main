/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bantuan;
import model.dataAkun;
import model.pengajuan;
import model.ukm;
import view.ajukan;
import view.ajukanlama;
import view.awal;
import view.beriP;
import view.beriPrincian;
import view.dashboard;
import view.profil;

/**
 *
 * @author user
 */
public class cBeriRinci {
    beriPrincian view;
    bantuan bantu = new bantuan();
    ukm uk = new ukm();
    
    public cBeriRinci(beriPrincian view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikProfil(new tmblProfil());
        this.view.klikAjukan(new tmblAjukan());
        this.view.klikBantu(new tmblBantu());
        this.view.klikTampil(new tmblTampil());
        tampilData();
    }
    
    public void tampilData(){
        System.out.println("");
        view.getNama().setText(ukm.getNamaUsaha());
        view.getAlasan().setText(pengajuan.getAlasanPeminjaman());
        view.getThn().setText(ukm.getThnBerdiri());
        view.getJaminan().setText(pengajuan.getJaminan());
        view.getJmlh().setText(pengajuan.getJmlhPinjam());
        view.getWktu().setText(pengajuan.getWktPelunasan());
        System.out.println("test"+pengajuan.getAlasanPeminjaman());
    }

    private class tmblTampil implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("dw");
            try {
                System.out.println("dsw");
                uk.tampilLap(dataAkun.getId());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(cLapTampil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class tmblBantu implements ActionListener {

        public tmblBantu() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cBeri bari = new controller.cBeri(new beriP());
            bantu.addBantuan(view.getJmlhBantuan().getText());
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
    
    private class tmblProfil implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cProfil profil = new controller.cProfil(new profil());
            view.setVisible(false);
        }
    }
    
    private class tmblAjukan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pengajuan.getPembuat().equals(dataAkun.getId())) {
                controller.cAjuLama ajukanlama = new controller.cAjuLama(new ajukanlama());
                view.setVisible(false);
            } else {
                controller.cAjukan ajukan = new controller.cAjukan(new ajukan());
                view.setVisible(false);
            }
        }
    }

    private class tmblLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }
    
}


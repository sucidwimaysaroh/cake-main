/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.bantuan;
import model.dataAkun;
import model.lapora;
import model.pengajuan;
import view.awal;
import view.dashboard;
import view.profil;
import view.ajukan;
import view.ajukanlama;
import view.beriP;
import view.lapTampil;
import view.laporan;

public class cDashboard {
    dashboard view;
//    ukm ukmModel = new ukm();
//    pengajuan ajuanModel = new pengajuan();
    
    public cDashboard(dashboard view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikProfil(new tmblProfil());
        this.view.klikAjukan(new tmblAjukan());
        this.view.klikBeri(new tmblBeri());
        this.view.klikLaporan(new tmblLaporan());
        
//        ukmModel.ukmDB(dataAkun.getId());
//        ajuanModel.pengajuanDB(dataAkun.getId());
        System.out.println(dataAkun.getId());
        System.out.println(pengajuan.getPembuat());
    }

    
    private class tmblLaporan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (lapora.getIdPengajuan() == null) {
//                controller.cLaporan laporan = new controller.cLaporan(new laporan());
                    controller.cLapTampil tampil = new controller.cLapTampil(new lapTampil());
                view.setVisible(false);
            } else {
                controller.cLapTampil tampil = new controller.cLapTampil(new lapTampil());
                view.setVisible(false);
            }
            
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

            if (bantuan.getIdPengguna().equals(dataAkun.getId())) {
//                if (da) {
//                    
//                }
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

    private class tmblLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }

    
}

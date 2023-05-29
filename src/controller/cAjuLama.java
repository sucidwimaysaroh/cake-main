/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.bantuan;
import model.pengajuan;
import view.ajukanlama;
import view.awal;
import view.beriP;
import view.dashboard;
import view.profil;

/**
 *
 * @author user
 */
public class cAjuLama {
    ajukanlama view;
    pengajuan ajuan = new pengajuan();
    bantuan bantu = new bantuan();
    
    public cAjuLama(ajukanlama view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikProfil(new tmblProfil());
        bantu.getBantuan(pengajuan.getId());
        vTampilkan();
    }
    
        public void vTampilkan(){
        view.getAlasan().setText(pengajuan.getAlasanPeminjaman());
        view.getCicilan().setSelectedItem(pengajuan.getWktCicilan());
        view.getJaminan().setText(pengajuan.getJaminan());
        view.getJmlh().setText(pengajuan.getJmlhPinjam());
//        view.getBukti().setText(pengajuan.getBuktiKepemilikan());
        view.getWaktu().setSelectedItem(pengajuan.getWktPelunasan());
        view.getStatus().setText(pengajuan.getStatus());
        view.getTerkumpul().setText(String.valueOf(bantuan.getJmlhUang()));
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

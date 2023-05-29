/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.pengajuan;
import view.ajukanbaru;
import view.ajukanlama;
import view.awal;
import view.beriP;
import view.dashboard;
import view.profil;

/**
 *
 * @author user
 */
public class cAjuBaru {
    ajukanbaru view;
    pengajuan pengajuanModel = new pengajuan();
    
    public cAjuBaru(ajukanbaru view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikProfil(new tmblProfil());
        this.view.klikKirim(new tmblKirim());
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
    
    private class tmblKirim implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getAlasan().equals("") && view.getCicilan().equals("") && view.getJaminan().equals("") && view.getJmlh().equals("") && view.getWaktu().equals("") && view.getUploadBukti().equals("")) {
                JOptionPane.showMessageDialog(null, "Semua Form Wajib Diisi");
            } else {
                pengajuanModel.addPengajuan(view.getJmlh().getText(), (String) view.getWaktu().getSelectedItem(), (String) view.getCicilan().getSelectedItem(), view.getJaminan().getText(), view.getUploadBukti().getText(), view.getAlasan().getText());
                JOptionPane.showMessageDialog(null, "Pengajuan berhasil");
                controller.cAjuLama ajulama = new controller.cAjuLama(new ajukanlama());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ukm;
import view.ajukan;
import view.ajukanbaru;
import view.awal;
import view.beriP;
import view.dashboard;
import view.profil;

/**
 *
 * @author user
 */
public class cAjukan {
    ajukan view;
    ukm ukmModel = new ukm();
    
    public cAjukan(ajukan view){
//        String id = ukm.getStatus();
//        System.out.println(id);
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikProfil(new tmblProfil());
        this.view.klikBeri(new tmblBeri());
        this.view.klikPengajuan(new tmblAjuBaru());
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
    
    private class tmblAjuBaru implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ukm.getStatus().equals("Terverifikasi")) {
                controller.cAjuBaru ajukanbaru = new controller.cAjuBaru(new ajukanbaru());
                view.setVisible(false);
            }
            else if(ukm.getStatus().equals("Ditolak")){
                JOptionPane.showMessageDialog(null, "Data Profil Ukm Anda Ditolak");
            }
            else{
                JOptionPane.showMessageDialog(null, "Data Profil Ukm Anda belum terverifikasi");
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

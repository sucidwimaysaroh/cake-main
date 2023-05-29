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
import javax.swing.JOptionPane;
import model.dataAkun;
import model.lapora;
import model.pengajuan;
import view.ajukan;
import view.ajukanlama;
import view.awal;
import view.beriP;
import view.dashboard;
import view.laporan;
import view.profil;

/**
 *
 * @author user
 */
public class cLaporan {
    laporan view;
    lapora lapor = new lapora();
    
    public cLaporan(laporan view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikLogout(new tmblLogout());
        this.view.klikProfil(new tmblProfil());
        this.view.klikAjukan(new tmblAjukan());
        this.view.klikBeri(new tmblBeri());
        this.view.klikSimpan(new tmblSimpan());
        
//        ukmModel.ukmDB(dataAkun.getId());
//        ajuanModel.pengajuanDB(dataAkun.getId());
        System.out.println(dataAkun.getId());
        System.out.println(pengajuan.getPembuat());
    }
    
    private class tmblSimpan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getFilename() == null ) 
            {
                JOptionPane.showMessageDialog(null, "semua form harap diisi");
            } else {
                System.out.println("sukses");
                try {
                    if(lapor.upLaporan(view.getFilename(), dataAkun.getId())){
                        JOptionPane.showMessageDialog(null, "gambar berhasil disimpan"); 
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(cLaporan.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
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

    private class tmblLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }
}

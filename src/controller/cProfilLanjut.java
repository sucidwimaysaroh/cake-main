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
import model.pengajuan;
import model.ukm;
import view.ajukan;
import view.ajukanlama;
import view.awal;
import view.dashboard;
import view.profil;
import view.profillanjutan;


/**
 *
 * @author user
 */

public class cProfilLanjut { 
    profillanjutan view; //view data ukm
    ukm ukmModel = new ukm(); 

    public cProfilLanjut(profillanjutan view){
        this.view = view;
        this.view.setVisible(true);
//        ukmModel.ukmDB(dataAkun.getId());
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikProfil(new tmblProfil());
        this.view.klikAjukan(new tmblAjukan());
        this.view.klikSimpan(new tmblSimpan());
        vUkm();
//        if (ukm.getIdPengguna().equals(dataAkun.getId())) {
//            vUkm();
//        } else {
//            System.out.println("idP"+dataAkun.getId());
//        }
    }

    public void vUkm() {
//        String id = ukm.getStatus();
//        System.out.println(id);
//        System.out.println("nama"+ukm.getNamaUsaha());
        if (dataAkun.getId().equals(ukm.getIdPengguna())) {
            view.getUsaha().setText(ukm.getNamaUsaha());
            view.getJenisUsaha().setText(ukm.getJnsUsaha());
            view.getThnDiri().setSelectedItem(ukm.getThnBerdiri());
            view.getKeuangan().setText(ukm.getKeuangan());
        }
    }
    
    private  class tmblSimpan implements ActionListener {

        public tmblSimpan() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (view.getUsaha().equals("") && view.getThnDiri().equals("") && view.getJenisUsaha().equals("") && view.getFilename().equals("") ) {
                JOptionPane.showMessageDialog(null, "Semua Form Wajib Diisi");
            } else {
                if (dataAkun.getId().equals(ukm.getIdPengguna())) {
                    try {
                        ukmModel.ubahUkm(view.getUsaha().getText(), (String) view.getThnDiri().getSelectedItem(), view.getJenisUsaha().getText(), view.getFilename());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(cProfilLanjut.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Data UKM berhasil diperbarui");
                } else {
                    try {
                        ukmModel.addUkm(view.getUsaha().getText(), (String) view.getThnDiri().getSelectedItem(), view.getJenisUsaha().getText(),view.getFilename());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(cProfilLanjut.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Data UKM berhasil ditambah");
                    controller.cProfil profil = new controller.cProfil(new profil());
                    view.setVisible(false);
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

    private class tmblLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cAwal awal = new controller.cAwal(new awal());
            view.setVisible(false);
        }
    }
    
}
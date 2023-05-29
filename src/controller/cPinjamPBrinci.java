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
import model.ukm;
import view.a_inves;
import view.a_pinjamanPB1;
import view.a_pinjamanPB2;
import view.awal;
import view.dashboardAdmin;

/**
 *
 * @author user
 */
public class cPinjamPBrinci {
    a_pinjamanPB2 view;
    pengajuan ajuan = new pengajuan();
    
    public cPinjamPBrinci (a_pinjamanPB2 view) {
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikPinjam(new tmblPinjam());
        this.view.klikInves(new tmblInves());
        this.view.klikHome(new tmblHome());
        this.view.klikSimpan(new tmblSimpan());
        tampilData();
    }
    
    public void tampilData(){
        System.out.println("");
        view.getUsaha().setText(ukm.getNamaUsaha());
        view.getAlasan().setText(pengajuan.getAlasanPeminjaman());
        view.getThn().setText(ukm.getThnBerdiri());
        view.getCicil().setSelectedItem(pengajuan.getWktCicilan());
        view.getJaminan().setText(pengajuan.getJaminan());
        view.getJmlh().setText(pengajuan.getJmlhPinjam());
        view.getLunas().setSelectedItem(pengajuan.getWktPelunasan());
        view.getStatus().setSelectedItem(pengajuan.getStatus());
        System.out.println("test"+pengajuan.getAlasanPeminjaman());
    }
    
    private class tmblHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cDashboardAdmin home = new controller.cDashboardAdmin(new dashboardAdmin());
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
    
    private class tmblPinjam implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cPinjamPB a_pinjamanPB1 = new controller.cPinjamPB(new a_pinjamanPB1());
            view.setVisible(false);
        }
    }
    
    private class tmblSimpan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ajuan.verif((String) view.getStatus().getSelectedItem(), pengajuan.getId());
            System.out.println(pengajuan.getId());
            System.out.println((String) view.getStatus().getSelectedItem());
            JOptionPane.showMessageDialog(null,"Berhasil verifikasi");
            controller.cPinjamPB a_pinjamanPB1 = new controller.cPinjamPB(new a_pinjamanPB1());
            view.setVisible(false);
        }
    }
    
    private class tmblInves implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cInves inves = new controller.cInves(new a_inves());
            view.setVisible(false);
        }
    }
    
}

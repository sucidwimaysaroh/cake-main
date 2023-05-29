/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.dataAkun;
import model.pengajuan;
import view.ajukan;
import view.ajukanlama;
import view.awal;
import view.beriP;
import view.beriPrincian;
import view.dashboard;
import view.profil;
import javax.swing.table.DefaultTableModel;
import model.dbconnect;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ukm;

/**
 *
 * @author user
 */
public class cBeri{
    beriP view;
    pengajuan p = new pengajuan();
    dbconnect koneksi = new dbconnect();
    private final DefaultTableModel model;
    
    public cBeri(beriP view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikDashboard(new tmblDashboard());
        this.view.klikProfil(new tmblProfil());
        this.view.klikAjukan(new tmblAjukan());
//        this.view.klikBantu(new tmblBantuRinci());
        this.view.klikCek(new tmblCek());
        
        model = new DefaultTableModel ();
        view.getPengajuan().setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Usaha");
        model.addColumn("Jenis Usaha");
        model.addColumn("Jumlah Pengajuan Pinjaman (Rp)");
        model.addColumn("Status");
        getData();
    }
    
    public void getData(){
        koneksi.connect();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Statement stmt = koneksi.con.createStatement();
            String sql     = "Select * from datapengajuan as p join dataukm as du on p.idPengguna=du.idPengguna";
            ResultSet rs   = stmt.executeQuery(sql);
            while(rs.next ()){
                ukm.setThnBerdiri(rs.getString("tahunBerdiri"));
                ukm.setNamaUsaha(rs.getString("namaUsaha"));
                Object[ ] obj = new Object[5];
                obj[0] = rs.getString("idPengajuan");
                obj[1] = rs.getString("namaUsaha");
                obj[2] = rs.getString("jenisUsaha");
                obj[3] = rs.getString("jumlahPinjaman");
                obj[4] = rs.getString("statusVerif");
                pengajuan.setPembuat(rs.getString("idPengguna"));
                pengajuan.setStatus(rs.getString("statusVerif"));
                System.out.println(pengajuan.getStatus());
                if (pengajuan.getStatus().equals("Terverifikasi")) {
                    if (!pengajuan.getPembuat().equals(dataAkun.getId())) {
                        model.addRow(obj);
                    }
                } else {
                    System.out.println("Tidak ada");
                }
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage() );
        }
    } 
    
    private class tmblCek implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            p.pengajuanDB(view.getIda());
            System.out.println(view.getIda());
            p.setId(view.getIda());
            controller.cBeriRinci rinci = new controller.cBeriRinci(new beriPrincian());
            view.setVisible(false);
        }
    }

    private  class tmblBantuRinci implements ActionListener {

        public tmblBantuRinci() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cBeriRinci beri = new controller.cBeriRinci(new beriPrincian());
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

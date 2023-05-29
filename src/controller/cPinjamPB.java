/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.dbconnect;
import view.a_inves;
import view.a_pinjamanPB1;
import view.a_pinjamanPB2;
import view.awal;
import view.dashboardAdmin;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.pengajuan;
import model.ukm;

public class cPinjamPB {
    private final DefaultTableModel model;
    a_pinjamanPB1 view;
    pengajuan p = new pengajuan();
    dbconnect koneksi = new dbconnect();
    
    public cPinjamPB (a_pinjamanPB1 view) {
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikPinjam(new tmblPinjam());
        this.view.klikInves(new tmblInves());
        this.view.klikHome(new tmblHome());
        this.view.klikCek(new tmblCek());
        
        model = new DefaultTableModel ();
        view.getPinjaman().setModel(model);
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
           //membuat statemen pemanggilan data pada table tblGaji dari database
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
                pengajuan.setId(rs.getString("idPengajuan"));
                pengajuan.setJmlhPinjam(rs.getString("jumlahPinjaman"));
                pengajuan.setAlasanPeminjaman(rs.getString("alasanPeminjaman"));
                pengajuan.setJaminan(rs.getString("jaminan"));
                pengajuan.setWktCicilan(rs.getString("waktucicilan"));
                pengajuan.setWktPelunasan(rs.getString("waktuPelunasan"));

                model.addRow(obj);
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
            controller.cPinjamPBrinci rinci = new controller.cPinjamPBrinci(new a_pinjamanPB2());
            view.setVisible(false);
        }
    }
    
    private class tmblRinci implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            controller.cPinjamPBrinci rinci = new controller.cPinjamPBrinci(new a_pinjamanPB2());
            view.setVisible(false);
        }
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
    
    private class tmblInves implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cInves inves = new controller.cInves(new a_inves());
            view.setVisible(false);
        }
    }
}

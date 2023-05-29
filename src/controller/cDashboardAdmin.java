package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bantuan;
import model.dbconnect;
import model.pengajuan;
import model.ukm;
import view.aUkm;
import view.a_inves;
import view.a_pinjamanPB1;
import view.awal;
import view.dashboardAdmin;


public class cDashboardAdmin {
    dashboardAdmin view;
    private final DefaultTableModel model;
    dbconnect koneksi = new dbconnect();    
    ukm ukmModel = new ukm();
    pengajuan p = new pengajuan();
    bantuan b = new bantuan();
    
    public cDashboardAdmin (dashboardAdmin view) {
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikPinjam(new tmblPinjam());
        this.view.klikInves(new tmblInves());
        this.view.klikCek(new tmblCek());
//        this.view.klikProfil(new tmblProfil());
        ukmModel.jmlh();
        p.jmlh();
        b.jmlh();
        model = new DefaultTableModel ();
        view.getUkm().setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Usaha");
        model.addColumn("Jenis Usaha");
        model.addColumn("Status");
        getData();
        view.getPengaju().setText(pengajuan.getJmlh());
        view.getSumbang().setText(bantuan.getJmlh());
        view.getJukm().setText(ukm.getJmlh());
    }
    
    public void getData(){
        koneksi.connect();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
           //membuat statemen pemanggilan data pada table tblGaji dari database
            Statement stmt = koneksi.con.createStatement();
            String sql     = "Select * from dataUkm";
            ResultSet rs   = stmt.executeQuery(sql);

            while(rs.next ()){
//                ukm.setThnBerdiri(rs.getString("tahunBerdiri"));
//                ukm.setNamaUsaha(rs.getString("namaUsaha"));
                Object[ ] obj = new Object[5];
                obj[0] = rs.getString("idUkm");
                obj[1] = rs.getString("namaUsaha");
                obj[2] = rs.getString("jenisUsaha");
                obj[3] = rs.getString("statusVerif");

                model.addRow(obj);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage() );
        }
    } 

    private class tmblCek implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ukmModel.ukmAd(ukm.getIdCek());
            controller.cAUkm ukm = new controller.cAUkm(new aUkm());
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
    
                
//    coming soon kurang ini aja
//    private class tmblProfil implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            controller.cProfilAdmin pAdmin = new controller.cProfilAdmin(new profilAdmin());
//            view.setVisible(false);
//        }
//    }
}

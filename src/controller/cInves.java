/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.a_inves;
import view.a_pinjamanPB1;
import view.a_pinjamanPB2;
import view.awal;
import view.dashboardAdmin;

/**
 *
 * @author user
 */
public class cInves {
    a_inves view;
    
    public cInves (a_inves view) {
        this.view = view;
        this.view.setVisible(true);
        this.view.klikLogout(new tmblLogout());
        this.view.klikPinjam(new tmblPinjam());
        this.view.klikInves(new tmblInves());
        this.view.klikHome(new tmblHome());
    }
    
    private class tmblRinci implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cPinjamPBrinci rinci = new controller.cPinjamPBrinci(new a_pinjamanPB2());
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


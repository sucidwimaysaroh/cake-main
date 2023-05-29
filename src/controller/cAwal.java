/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.awal;
import view.Login;
import view.daftar;


/**
 *
 * @author user
 */
public class cAwal {
    awal view;
    
    public cAwal(awal view){
        this.view = view;
        this.view.setVisible(true);
        this.view.klikDaftar(new tmblDaftar());
        this.view.klikLogin(new tmblLogin());
    }
    
    private class tmblDaftar implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cDaftar daftar = new controller.cDaftar(new daftar());
            view.setVisible(false);
        }
    }
    private class tmblLogin implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            controller.cLogin login = new controller.cLogin(new Login());
            view.setVisible(false);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import base.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Alat;
import model.Inzenjer;
import model.Lokacija;
import model.Masina;
import model.Upotreba;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instance;
    private List<Inzenjer> inz;
    private DBBroker dbb = DBBroker.getInstance();

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    private Controller() {
        inz = new ArrayList<>();

        Inzenjer i1 = new Inzenjer(1, "Inz1", "1234", "Vojko", "Vojislavovic");
        Inzenjer i2 = new Inzenjer(2, "Inz2", "12345", "Mirko", "Vukmirovic");
        Inzenjer i3 = new Inzenjer(3, "Inz3", "123456", "Marko", "Markovic");

        inz.add(i1);
        inz.add(i2);
        inz.add(i3);
    }

    public List<Inzenjer> getInz() {
        return inz;
    }

    public void setInz(List<Inzenjer> inz) {
        this.inz = inz;
    }

    public Inzenjer postojiUBazi(String username, String pass) {
        for (Inzenjer i : inz) {

            if (username.equals(i.getUsername()) && pass.equals(i.getSifra())) {
                return i;
            }
        }

        return null;
    }

    public List<Masina> vratiListuMasina() {
        return dbb.vratiListuMasina();
    }

    public List<Lokacija> vratuListuLokacija() {
        return dbb.vratiListuLokacija();
    }

    public void dodajMasinu(Masina m) {
        dbb.dodajMasinu(m);
    }

    public boolean proveriLok(Lokacija lok) {
        return dbb.proveriLok(lok);
    }

    public List<Alat> vratiListuAlata() {
        return dbb.vratiListuAlata();
    }

    public List<Upotreba> vratiListuUpotreba() {
        return dbb.vratiListuUpotreba();
    }

    public void dodajUpotrebu(Upotreba u) {
        dbb.dodajUpotrebu(u);
    }

    public void obrisiUpotrebu(int id) {
        dbb.obrisiUpotrebu(id);
    }

    public void obrisiMasinu(int id) {
        dbb.obrisiMasinu(id);
    }

    public void oduzmiStanje(int stanje) {
        dbb.oduzmiStanje(stanje);
    }

    public void dodajStanje(int id) {
        dbb.dodajStanje(id);
    }

}

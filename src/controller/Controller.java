/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Inzenjer;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instance;
    private List<Inzenjer> inz;

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

}

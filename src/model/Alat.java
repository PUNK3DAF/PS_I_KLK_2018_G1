/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author vldmrk
 */
public class Alat {

    private int id;
    private String naziv;
    private int stanje;

    public Alat() {
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alat other = (Alat) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.stanje != other.stanje) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    public Alat(int id, String naziv, int stanje) {
        this.id = id;
        this.naziv = naziv;
        this.stanje = stanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

}

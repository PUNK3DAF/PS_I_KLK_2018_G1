/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author vldmrk
 */
public class Masina {

    private int id;
    private String naziv;
    private String proizvodjac;
    private int radniVek;
    private int godProizvodnje;
    private Date datumPocetka = new Date();
    private TipMasine tip;
    private Lokacija lok;

    public Masina() {
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Masina other = (Masina) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.radniVek != other.radniVek) {
            return false;
        }
        if (this.godProizvodnje != other.godProizvodnje) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.proizvodjac, other.proizvodjac)) {
            return false;
        }
        if (!Objects.equals(this.datumPocetka, other.datumPocetka)) {
            return false;
        }
        if (this.tip != other.tip) {
            return false;
        }
        return Objects.equals(this.lok, other.lok);
    }

    public Masina(int id, String naziv, String proizvodjac, int radniVek, int godProizvodnje, Date datumPocetka, TipMasine tip, Lokacija lok) {
        this.id = id;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.radniVek = radniVek;
        this.godProizvodnje = godProizvodnje;
        this.datumPocetka = datumPocetka;
        this.tip = tip;
        this.lok = lok;
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

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getRadniVek() {
        return radniVek;
    }

    public void setRadniVek(int radniVek) {
        this.radniVek = radniVek;
    }

    public int getGodProizvodnje() {
        return godProizvodnje;
    }

    public void setGodProizvodnje(int godProizvodnje) {
        this.godProizvodnje = godProizvodnje;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public TipMasine getTip() {
        return tip;
    }

    public void setTip(TipMasine tip) {
        this.tip = tip;
    }

    public Lokacija getLok() {
        return lok;
    }

    public void setLok(Lokacija lok) {
        this.lok = lok;
    }

}

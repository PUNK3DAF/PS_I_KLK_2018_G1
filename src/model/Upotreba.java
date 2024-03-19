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
public class Upotreba {

    private int id;
    private Alat alat;
    private Masina masina;

    public Upotreba() {
    }

    @Override
    public String toString() {
        return "Upotreba{" + "alat=" + alat + ", masina=" + masina + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Upotreba other = (Upotreba) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.alat, other.alat)) {
            return false;
        }
        return Objects.equals(this.masina, other.masina);
    }

    public Upotreba(int id, Alat alat, Masina masina) {
        this.id = id;
        this.alat = alat;
        this.masina = masina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alat getAlat() {
        return alat;
    }

    public void setAlat(Alat alat) {
        this.alat = alat;
    }

    public Masina getMasina() {
        return masina;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alat;
import model.Lokacija;
import model.Masina;
import model.TipMasine;
import model.Upotreba;

/**
 *
 * @author vldmrk
 */
public class DBBroker {

    private static DBBroker instance;
    private List<Masina> masine;
    private List<Lokacija> lokacije;
    private List<Alat> alati;
    private List<Upotreba> upotrebe;
    private final Connection connection = Konekcija.getInstance().getConnection();

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }

        return instance;
    }

    private DBBroker() {

    }

    public List<Masina> vratiListuMasina() {
        try {
            masine = new ArrayList<>();

            String upit = "SELECT * FROM masine m JOIN lokacije l ON m.lokacija=l.id";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("m.id");
                String naziv = rs.getString("naziv");
                String proizvodjac = rs.getString("proizvodjac");
                int radniVek = rs.getInt("radniVek");
                int godProizvodnje = rs.getInt("godProizvodnje");
                Date datumPocetka = rs.getDate("datumPocetka");
                TipMasine tip = TipMasine.valueOf(rs.getString("tipMasine"));
                int idLok = rs.getInt("l.id");
                String nazivLok = rs.getString("l.naziv");
                Lokacija lok = new Lokacija(idLok, nazivLok);
                Masina m = new Masina(id, naziv, proizvodjac, radniVek, godProizvodnje, datumPocetka, tip, lok);
                masine.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return masine;
    }

    public List<Lokacija> vratiListuLokacija() {

        try {
            lokacije = new ArrayList<>();

            String upit = "SELECT * FROM lokacije ORDER BY naziv ASC";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                Lokacija l = new Lokacija(id, naziv);
                lokacije.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lokacije;

    }

    public void dodajMasinu(Masina m) {
        try {
            String upit = "INSERT INTO masine (id,naziv,proizvodjac,radniVek,godProizvodnje,datumPocetka,tipMasine,lokacija) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, m.getId());
            ps.setString(2, m.getNaziv());
            ps.setString(3, m.getProizvodjac());
            ps.setInt(4, m.getRadniVek());
            ps.setInt(5, m.getGodProizvodnje());
            ps.setDate(6, new java.sql.Date(m.getDatumPocetka().getTime()));
            ps.setString(7, String.valueOf(m.getTip()));
            ps.setInt(8, m.getLok().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean proveriLok(Lokacija lok) {
        masine = vratiListuMasina();
        for (Masina m : masine) {
            if (m.getLok().getId() == lok.getId()) {
                return false;
            }
        }

        return true;
    }

    public List<Alat> vratiListuAlata() {
        try {
            alati = new ArrayList<>();

            String upit = "SELECT * FROM alati";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                int stanje = rs.getInt("stanje");
                Alat a = new Alat(id, naziv, stanje);
                alati.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alati;
    }

    public List<Upotreba> vratiListuUpotreba() {
        try {
            upotrebe = new ArrayList<>();

            String upit = "SELECT * FROM upotreba u JOIN alati a ON u.alatId=a.id JOIN masine m ON u.masinaId=m.id JOIN lokacije l ON m.lokacija=l.id";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("u.id");
                int alatId = rs.getInt("a.id");
                String alatNaziv = rs.getString("a.naziv");
                int stanje = rs.getInt("stanje");
                Alat a = new Alat(alatId, alatNaziv, stanje);
                int masId = rs.getInt("m.id");
                String masNaziv = rs.getString("m.naziv");
                String proizvodjac = rs.getString("proizvodjac");
                int radniVek = rs.getInt("radniVek");
                int godProizvodnje = rs.getInt("godProizvodnje");
                Date datumPocetka = rs.getDate("datumPocetka");
                TipMasine tip = TipMasine.valueOf(rs.getString("tipMasine"));
                int idLok = rs.getInt("l.id");
                String nazivLok = rs.getString("l.naziv");
                Lokacija lok = new Lokacija(idLok, nazivLok);
                Masina m = new Masina(masId, masNaziv, proizvodjac, radniVek, godProizvodnje, datumPocetka, tip, lok);
                Upotreba u = new Upotreba(id, a, m);
                upotrebe.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upotrebe;
    }

    public void dodajUpotrebu(Upotreba u) {
        try {
            String upit = "INSERT INTO upotreba (id,alatId,masinaId) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, u.getId());
            ps.setInt(2, u.getAlat().getId());
            ps.setInt(3, u.getMasina().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void obrisiUpotrebu(int id) {
        try {
            String upit = "DELETE FROM upotreba WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obrisiMasinu(int id) {
        try {
            String upit = "DELETE FROM masine WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void oduzmiStanje(int stanje) {
        try {
            String upit = "UPDATE alati SET stanje=stanje-1 WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, stanje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodajStanje(int id) {
        try {
            String upit = "UPDATE alati SET stanje=stanje+1 WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

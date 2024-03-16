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
import model.Lokacija;
import model.Masina;
import model.TipMasine;

/**
 *
 * @author vldmrk
 */
public class DBBroker {

    private static DBBroker instance;
    private List<Masina> masine;
    private List<Lokacija> lokacije;
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
            connection.commit();
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Upotreba;

/**
 *
 * @author vldmrk
 */
public class ModelTabeleUpotreba extends AbstractTableModel {

    private final List<Upotreba> upotrebe = Controller.getInstance().vratiListuUpotreba();
    private final String[] kolone = {"Naziv alata", "Naziv masine", "Stanje alata"};

    public List<Upotreba> getUpotrebe() {
        return upotrebe;
    }

    @Override
    public int getRowCount() {
        return upotrebe.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return upotrebe.get(rowIndex).getAlat().getNaziv();
            }

            case 1 -> {
                return upotrebe.get(rowIndex).getMasina().getNaziv();
            }

            case 2 -> {
                return upotrebe.get(rowIndex).getAlat().getStanje();
            }

            default ->
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void osveziPodatke() {
        fireTableDataChanged();
    }

}

package com.smartbudget.dao;

import com.smartbudget.model.Depense;
import com.smartbudget.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contract for expense data access (TP1).
 * Every method is scoped to one account (compteId).
 */
public interface DepenseDAOImpl {

    /** All expenses of an account, newest first. Empty list if none. */
    List<Depense> listerParCompte(int compteId) throws SQLException;

    /**
     * Expenses of an account between two dates, both inclusive.
     * Caller must ensure debut is not after fin.
     */
    List<Depense> rechercherParPeriode(int compteId, LocalDate debut, LocalDate fin) throws SQLException;

    /** Expenses of an account whose amount is greater than or equal to min. */
    List<Depense> rechercherParMontantMinimum(int compteId, BigDecimal min) throws SQLException;
}

public class DepenseDAOImpl implements DepenseDAO {{
     
    private static final String BASE =
        "SELECT id, montant, date_depense, description, mode_paiement, compte_id FROM depense ";
     @Override
    public List<Depense> listerParCompte(int compteId) throws SQLException {
        String sql = BASE + "WHERE compte_id = ? ORDER BY date_depense DESC, id DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, compteId);
            return executer(ps);
        }
    }
     @Override
    public List<Depense> rechercherParPeriode(int compteId, LocalDate debut, LocalDate fin) throws SQLException {
        String sql = BASE + "WHERE compte_id = ? AND date_depense BETWEEN ? AND ? "
                          + "ORDER BY date_depense DESC, id DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, compteId);
            ps.setDate(2, Date.valueOf(debut));
            ps.setDate(3, Date.valueOf(fin));
            return executer(ps);
        }
    }
     @Override
    public List<Depense> rechercherParMontantMinimum(int compteId, BigDecimal min) throws SQLException {
        String sql = BASE + "WHERE compte_id = ? AND montant >= ? ORDER BY montant DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, compteId);
            ps.setBigDecimal(2, min);
            return executer(ps);
        }
    }

    private List<Depense> executer(PreparedStatement ps) throws SQLException {
        List<Depense> liste = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                liste.add(new Depense(
                    rs.getInt("id"),
                    rs.getBigDecimal("montant"),
                    rs.getDate("date_depense").toLocalDate(),
                    rs.getString("description"),
                    rs.getString("mode_paiement"),
                    rs.getInt("compte_id")));
            }
        }
        return liste;
    }
}
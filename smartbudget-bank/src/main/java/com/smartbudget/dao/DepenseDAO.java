package com.smartbudget.dao;

import com.smartbudget.model.Depense;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Contract for expense data access (TP1).
 * Every method is scoped to one account (compteId).
 */
public interface DepenseDAO {

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
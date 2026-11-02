package com.smartbudget.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Depense {
    private int id;
    private BigDecimal montant;
    private LocalDate dateDepense;
    private String description;
    private String modePaiement;
    private int compteId;   // many expenses -> one account

    public Depense() {}

    public Depense(int id, BigDecimal montant, LocalDate dateDepense,
                   String description, String modePaiement, int compteId) {
        this.id = id;
        setMontant(montant);
        this.dateDepense = dateDepense;
        this.description = description;
        this.modePaiement = modePaiement;
        this.compteId = compteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public BigDecimal getMontant() { return montant; }
    /** An expense is money going out: amount must be strictly positive (also enforced by CHECK in SQL). */
    public void setMontant(BigDecimal montant) {
        if (montant == null || montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être strictement positif.");
        }
        this.montant = montant;
    }

    public LocalDate getDateDepense() { return dateDepense; }
    public void setDateDepense(LocalDate dateDepense) { this.dateDepense = dateDepense; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getModePaiement() { return modePaiement; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
    public int getCompteId() { return compteId; }
    public void setCompteId(int compteId) { this.compteId = compteId; }

    @Override
    public String toString() {
        return dateDepense + " | " + description + " | " + montant + " TND | " + modePaiement;
    }
}
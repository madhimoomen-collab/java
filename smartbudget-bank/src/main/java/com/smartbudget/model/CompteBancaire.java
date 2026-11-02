package com.smartbudget.model;

import java.math.BigDecimal;

public class CompteBancaire {
    private int id;
    private String numeroCompte;
    private BigDecimal solde;
    private int clientId;   // many accounts -> one client

    public CompteBancaire() {}

    public CompteBancaire(int id, String numeroCompte, BigDecimal solde, int clientId) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.clientId = clientId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumeroCompte() { return numeroCompte; }
    public void setNumeroCompte(String numeroCompte) { this.numeroCompte = numeroCompte; }
    public BigDecimal getSolde() { return solde; }
    public void setSolde(BigDecimal solde) { this.solde = solde; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    @Override
    public String toString() { return "Compte " + numeroCompte + " (solde " + solde + ")"; }
}
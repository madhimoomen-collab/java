package com.smartbudget.controller;

import com.smartbudget.dao.DepenseDAO;
import com.smartbudget.model.Depense;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@WebServlet("/depenses")
public class DepenseServlet extends HttpServlet {
    private final DepenseDAO dao = new DepenseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int compteId = parseInt(req.getParameter("compteId"), 1); // no login yet in TP1
        String debutStr = req.getParameter("debut");
        String finStr = req.getParameter("fin");

        List<Depense> depenses = Collections.emptyList();
        String erreur = null;

        try {
            boolean hasDebut = debutStr != null && !debutStr.isEmpty();
            boolean hasFin = finStr != null && !finStr.isEmpty();

            if (hasDebut && hasFin) {
                LocalDate debut = LocalDate.parse(debutStr);
                LocalDate fin = LocalDate.parse(finStr);
                if (debut.isAfter(fin)) {
                    erreur = "La date de début ne peut pas dépasser la date de fin.";
                } else {
                    depenses = dao.rechercherParPeriode(compteId, debut, fin);
                }
            } else if (hasDebut || hasFin) {
                erreur = "Renseignez les deux dates (début et fin).";
            } else {
                depenses = dao.listerParCompte(compteId);
            }
        } catch (DateTimeParseException e) {
            erreur = "Format de date invalide.";
        } catch (SQLException e) {
            erreur = "Erreur base de données : " + e.getMessage();
        }

        BigDecimal total = BigDecimal.ZERO;
        for (Depense d : depenses) total = total.add(d.getMontant());

        req.setAttribute("depenses", depenses);
        req.setAttribute("total", total);
        req.setAttribute("erreur", erreur);
        req.setAttribute("compteId", compteId);
        req.setAttribute("debut", debutStr);
        req.setAttribute("fin", finStr);
        req.getRequestDispatcher("/WEB-INF/views/depenses.jsp").forward(req, resp);
    }

    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
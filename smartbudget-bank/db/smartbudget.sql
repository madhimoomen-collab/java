INSERT INTO client (nom, prenom, email) VALUES
 ('Ben Ali','Sami','sami@mail.tn'),
 ('Trabelsi','Lina','lina@mail.tn');

INSERT INTO compte_bancaire (numero_compte, solde, client_id) VALUES
 ('TN001', 1500.000, 1),
 ('TN002', 2300.000, 2);

INSERT INTO depense (montant, date_depense, description, mode_paiement, compte_id) VALUES
 (45.500, '2026-10-02', 'Courses Carrefour', 'CARTE', 1),
 (120.000,'2026-10-05', 'Chaussures', 'CARTE', 1),
 (30.000, '2026-10-07', 'Taxi', 'ESPECES', 1),
 (85.250, '2026-10-12', 'Restaurant', 'CARTE', 1),
 (60.000, '2026-10-15', 'Facture internet', 'VIREMENT', 1),
 (25.000, '2026-10-20', 'Cinéma', 'ESPECES', 2),
 (210.000,'2026-10-22', 'Vêtements', 'CARTE', 2),
 (18.750, '2026-10-25', 'Pharmacie', 'ESPECES', 2),
 (95.000, '2026-10-28', 'Courses Monoprix', 'CARTE', 2),
 (40.000, '2026-11-01', 'Carburant', 'CARTE', 2);
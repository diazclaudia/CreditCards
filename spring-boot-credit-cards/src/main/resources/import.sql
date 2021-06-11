/* Populate tables */
INSERT INTO dbtest.client (name) VALUES('Andres');
INSERT INTO dbtest.client (name) VALUES('John');

/* Creamos algunas facturas */
INSERT INTO dbtest.card (card_name, client_id, cvv,card_number,card_date) VALUES('card1', 1,123,'321654987','04/2025');
INSERT INTO dbtest.card (card_name, client_id, cvv,card_number,card_date) VALUES('card2',1,123,'654319654','10/2024');

-- Database Schema Compatibile per Concessionario
-- Integra i dati forniti con la struttura esistente del progetto

CREATE DATABASE IF NOT EXISTS concessionario 
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE concessionario;

-- Drop existing tables
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS veicolo;
DROP TABLE IF EXISTS casa_automobilistica;
DROP TABLE IF EXISTS categoria_veicolo;
SET FOREIGN_KEY_CHECKS = 1;

-- Tabella categorie compatibile con i dati forniti
CREATE TABLE categoria_veicolo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descrizione VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Inserisci categorie dal tuo schema
INSERT INTO categoria_veicolo (nome, descrizione) VALUES 
('automobile', 'Veicoli automobile'),
('motocicletta', 'Motoveicoli'),
('furgone', 'Veicoli commerciali leggeri');

-- Tabella case automobilistiche
CREATE TABLE casa_automobilistica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    nazione VARCHAR(50),
    sito_web VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Inserisci case automobilistiche dai tuoi dati
INSERT INTO casa_automobilistica (nome, nazione) VALUES 
('Fiat', 'Italia'),
('Volkswagen', 'Germania'),
('BMW', 'Germania'),
('Mercedes', 'Germania'),
('Ducati', 'Italia'),
('Yamaha', 'Giappone'),
('Ford', 'USA');

-- Tabella veicoli modificata per compatibilità
CREATE TABLE veicolo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modello VARCHAR(100) NOT NULL,
    anno_produzione INT DEFAULT 2024,
    categoria_id INT NOT NULL,
    casa_automobilistica_id INT NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL,
    quantita_disponibile INT DEFAULT 1,
    immagine VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categoria_veicolo(id),
    FOREIGN KEY (casa_automobilistica_id) REFERENCES casa_automobilistica(id)
);

-- Inserisci veicoli dai tuoi dati
INSERT INTO veicolo (marca, modello, anno_produzione, categoria_id, casa_automobilistica_id, prezzo, quantita_disponibile, immagine)
VALUES
-- AUTOMOBILI
('Fiat', 'Panda', 2024, 
 (SELECT id FROM categoria_veicolo WHERE nome = 'automobile'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Fiat'),
 9500.00, 1, 'https://cdn.motor1.com/images/mgl/Wkk6BM/s3/fiat-pandina.jpg'),

('Volkswagen', 'Golf 8', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'automobile'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Volkswagen'),
 21500.00, 1, 'https://cdn.motor1.com/images/mgl/ZqqX1/s3/volkswagen-golf-8.jpg'),

('BMW', 'Serie 1', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'automobile'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'BMW'),
 28900.00, 1, 'https://cdn.motor1.com/images/mgl/6xopx/s3/bmw-serie-1.jpg'),

('Mercedes', 'Classe A', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'automobile'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Mercedes'),
 31000.00, 1, 'https://cdn.motor1.com/images/mgl/1kkpJ/s3/mercedes-classe-a.jpg'),

-- MOTOCICLETTE
('Ducati', 'Monster 797', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'motocicletta'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Ducati'),
 9200.00, 1, 'https://cdn.motor1.com/images/mgl/J66nA/s3/ducati-monster-797.jpg'),

('Yamaha', 'MT-07', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'motocicletta'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Yamaha'),
 7600.00, 1, 'https://cdn.motor1.com/images/mgl/oPPK0/s3/yamaha-mt-07.jpg'),

-- FURGONI
('Ford', 'Transit', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'furgone'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Ford'),
 29500.00, 1, 'https://cdn.motor1.com/images/mgl/BB8pA/s3/ford-transit.jpg'),

('Mercedes', 'Sprinter', 2024,
 (SELECT id FROM categoria_veicolo WHERE nome = 'furgone'),
 (SELECT id FROM casa_automobilistica WHERE nome = 'Mercedes'),
 42000.00, 1, 'https://cdn.motor1.com/images/mgl/7kkx0/s3/mercedes-sprinter.jpg');

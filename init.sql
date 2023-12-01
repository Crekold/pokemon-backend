CREATE DATABASE pokemon_db;

USE pokemon_db;

-- Creación de tabla user
CREATE TABLE user (
  user_id varchar(50) PRIMARY KEY,
  nickname varchar(50)
);

-- Creación de tabla team
CREATE TABLE team (
  team_id serial PRIMARY KEY,
  team_name varchar(50),
  user_user_id varchar(50) REFERENCES user(user_id)
);

-- Creación de tabla type_element
CREATE TABLE type_element (
  type_element_id serial PRIMARY KEY,
  type_element_name varchar(50)
);

-- Modificación de la tabla pokemon
CREATE TABLE pokemon (
  pokemon_id varchar(50) PRIMARY KEY,
  pokemon_name varchar(50),
  image_url varchar(255)
);

-- Tabla de estadisticas
CREATE TABLE pokemon_stats (
  id_stats serial PRIMARY KEY,
  hp int NOT NULL,
  attack int NOT NULL,
  defense int NOT NULL,
  special_attack int NOT NULL,
  special_defense int NOT NULL,
  pokemon_pokemon_id varchar(50) REFERENCES pokemon(pokemon_id)
);

-- Creación de tabla pokemon_type
CREATE TABLE pokemon_type (
  pokemon_type_id serial PRIMARY KEY,
  pokemon_id varchar(50) REFERENCES pokemon(pokemon_id),
  type_element_id integer REFERENCES type_element(type_element_id)
);

-- Creación de tabla team_pokemon
CREATE TABLE team_pokemon (
  team_pokemon_id serial PRIMARY KEY,
  pokemon_pokemon_id varchar(50) REFERENCES pokemon(pokemon_id),
  team_team_id integer REFERENCES team(team_id)
);

-- Creación de tabla team_stats 
CREATE TABLE team_stats (
  team_stats_id serial PRIMARY KEY,
  hp_prom int NOT NULL,
  attack_prom int NOT NULL,
  defense_prom int NOT NULL,
  SA_prom int NOT NULL,
  SE_prom int NOT NULL,
  team_team_id integer REFERENCES team(team_id)
);

-- Creación de tabla suggestion 
CREATE TABLE team_suggestion (
  team_suggestion_id serial PRIMARY KEY,
  suggestion_explanation text NOT NULL, 
  team_team_id integer REFERENCES team(team_id) 
);

-- Crear tabla histórica para team
CREATE TABLE team_history (
    team_id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(50),
    user_user_id VARCHAR(50),
    valid_from TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla histórica para team_pokemon
CREATE TABLE team_pokemon_history (
    team_pokemon_id INT AUTO_INCREMENT PRIMARY KEY,
    pokemon_pokemon_id VARCHAR(50),
    team_team_id INT,
    valid_from TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Eliminar trigger existente para team_history
DROP TRIGGER IF EXISTS team_history_trigger;

-- Crear trigger para team_history
DELIMITER //
CREATE TRIGGER team_history_trigger
AFTER INSERT ON team
FOR EACH ROW
BEGIN
    INSERT INTO team_history (team_name, user_user_id, valid_from)
    VALUES (NEW.team_name, NEW.user_user_id, CURRENT_TIMESTAMP);
END;
//
DELIMITER ;

-- Eliminar trigger existente para team_pokemon_history
DROP TRIGGER IF EXISTS team_pokemon_history_trigger;

-- Crear trigger para team_pokemon_history
DELIMITER //
CREATE TRIGGER team_pokemon_history_trigger
AFTER INSERT ON team_pokemon
FOR EACH ROW
BEGIN
    INSERT INTO team_pokemon_history (pokemon_pokemon_id, team_team_id, valid_from)
    VALUES (NEW.pokemon_pokemon_id, NEW.team_team_id, CURRENT_TIMESTAMP);
END;
//
DELIMITER ;

-- Insertar datos de ejemplo en la tabla user
INSERT INTO user (user_id, nickname) VALUES
('user1', 'AshK'),
('user2', 'MistyW'),
('user3', 'BrockS');

-- Insertar datos de ejemplo en la tabla team
INSERT INTO team (team_name, user_user_id) VALUES
('Team Pikachu', 'user1'),
('Team Eevee', 'user2'),
('Team Onix', 'user3');



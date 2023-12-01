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

-- Insertar datos de ejemplo en la tabla user
INSERT INTO user (user_id, nickname) VALUES
('user1', 'AshK'),
('user2', 'MistyW'),
('user3', 'BrockS'),
('user4', 'JessieR'),
('user5', 'JamesR');

-- Insertar datos de ejemplo en la tabla team
INSERT INTO team (team_name, user_user_id) VALUES
('Team Rocket', 'user4'),
('Team Pikachu', 'user1'),
('Team Eevee', 'user2'),
('Team Onix', 'user3'),
('Team Meowth', 'user5');

-- Insertar datos de ejemplo en la tabla type_element
INSERT INTO `type_element` VALUES (1, 'grass');
INSERT INTO `type_element` VALUES (2, 'poison');
INSERT INTO `type_element` VALUES (3, 'fire');
INSERT INTO `type_element` VALUES (4, 'flying');
INSERT INTO `type_element` VALUES (5, 'water');
INSERT INTO `type_element` VALUES (6, 'bug');

-- Insertar datos de ejemplo en la tabla pokemon
INSERT INTO `pokemon` VALUES ('1', 'bulbasaur', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png');
INSERT INTO `pokemon` VALUES ('10', 'caterpie', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png');
INSERT INTO `pokemon` VALUES ('2', 'ivysaur', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png');
INSERT INTO `pokemon` VALUES ('3', 'venusaur', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png');
INSERT INTO `pokemon` VALUES ('4', 'charmander', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png');
INSERT INTO `pokemon` VALUES ('5', 'charmeleon', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png');
INSERT INTO `pokemon` VALUES ('6', 'charizard', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png');
INSERT INTO `pokemon` VALUES ('7', 'squirtle', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png');
INSERT INTO `pokemon` VALUES ('8', 'wartortle', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png');
INSERT INTO `pokemon` VALUES ('9', 'blastoise', 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png');

-- Insertar datos de ejemplo en la tabla pokemon_stats
INSERT INTO `pokemon_stats` (`id_stats`, `hp`, `attack`, `defense`, `special_attack`, `special_defense`, `pokemon_pokemon_id`) VALUES
(1, 45, 49, 49, 65, 65, '1'),
(2, 50, 20, 55, 25, 25, '10'),
(3, 60, 62, 63, 80, 80, '2'),
(4, 80, 82, 83, 100, 100, '3'),
(5, 39, 52, 43, 60, 50, '4'),
(6, 58, 64, 58, 80, 65, '5'),
(7, 78, 84, 78, 109, 85, '6'),
(8, 44, 48, 65, 50, 64, '7'),
(9, 59, 63, 80, 65, 80, '8'),
(10, 79, 83, 100, 85, 105, '9');

-- Insertar datos de ejemplo en la tabla pokemon_type
INSERT INTO `pokemon_type` VALUES (1, '1', 1);
INSERT INTO `pokemon_type` VALUES (2, '1', 2);
INSERT INTO `pokemon_type` VALUES (3, '2', 1);
INSERT INTO `pokemon_type` VALUES (4, '2', 2);
INSERT INTO `pokemon_type` VALUES (5, '3', 1);
INSERT INTO `pokemon_type` VALUES (6, '3', 2);
INSERT INTO `pokemon_type` VALUES (7, '4', 3);
INSERT INTO `pokemon_type` VALUES (8, '5', 3);
INSERT INTO `pokemon_type` VALUES (9, '6', 3);
INSERT INTO `pokemon_type` VALUES (10, '6', 4);
INSERT INTO `pokemon_type` VALUES (11, '7', 5);
INSERT INTO `pokemon_type` VALUES (12, '8', 5);
INSERT INTO `pokemon_type` VALUES (13, '9', 5);
INSERT INTO `pokemon_type` VALUES (14, '10', 6);


-- Suponiendo que ya existen IDs generados para team y pokemon, insertar datos de ejemplo en la tabla team_pokemon
INSERT INTO team_pokemon (pokemon_pokemon_id, team_team_id) VALUES
('1', 1),
('2', 1),
('3', 1),
('4', 1),
('5', 1),
('6', 1);
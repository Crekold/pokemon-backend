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
INSERT INTO type_element (type_element_name) VALUES
('Fire'),
('Water'),
('Grass'),
('Electric'),
('Rock');

-- Insertar datos de ejemplo en la tabla pokemon
INSERT INTO pokemon (pokemon_id, pokemon_name, image_url) VALUES
('001', 'Bulbasaur', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png'),
('005', 'Charmeleon', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png'),
('025', 'Pikachu', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png'),
('075', 'Graveler', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/075.png'),
('076', 'Golem', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/076.png');

-- Insertar datos de ejemplo en la tabla pokemon_type
INSERT INTO pokemon_type (pokemon_id, type_element_id) VALUES
('001', 3),
('005', 1),
('025', 4),
('075', 5),
('076', 5); 

-- Suponiendo que ya existen IDs generados para team y pokemon, insertar datos de ejemplo en la tabla team_pokemon
INSERT INTO team_pokemon (pokemon_pokemon_id, team_team_id) VALUES
('001', 2),
('004', 3),
('007', 1),
('025', 2),
('074', 4);

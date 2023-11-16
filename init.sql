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

-- Creación de tabla para asociar pokemon con tipos
CREATE TABLE pokemon_type (
  pokemon_id varchar(50),
  type_element_id integer,
  FOREIGN KEY (pokemon_id) REFERENCES pokemon(pokemon_id),
  FOREIGN KEY (type_element_id) REFERENCES type_element(type_element_id)
);

-- Creación de tabla team_pokemon
CREATE TABLE team_pokemon (
  team_pokemon_id serial PRIMARY KEY,
  pokemon_pokemon_id varchar(50) REFERENCES pokemon(pokemon_id),
  team_team_id integer REFERENCES team(team_id)
);

-- Insertar datos de ejemplo en las tablas user, team, type_element, pokemon, y team_pokemon
-- ... (mantén tus inserciones de ejemplo aquí)


# 🌟 PokemonTeamBuilder Backend

Este proyecto es un backend construido con Spring Boot que utiliza la API de PokeAPI para los datos en Frontend de PokemonTeamBuilder.
PokemonTeamBuilder es tu plataforma para crear equipos Pokémon competitivos. Regístrate y empieza a armar tu equipo de ensueño. 

## 🚀 Empezando

Estas instrucciones te ayudarán a configurar una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

### 📋 Pre-requisitos

- Java 11 o superior
- Maven
- Spring Boot
- PostgreSQL
- Acceso a la API de OpenAI (necesitarás tu propia clave de API)

### 🔧 Instalación

1. Clona el repositorio en tu máquina local:
```
git clone https://github.com/tu-usuario/pokemon-backend.git
```

2. Navega al directorio del proyecto y ejecuta Maven para instalar las dependencias:
```
cd pokemon-backend
mvn install
```

3. Configura tu base de datos PostgreSQL y actualiza el archivo `application.properties` con tus credenciales de base de datos.

4. Configura tu clave de API de OpenAI en el archivo de configuración o como una variable de entorno:
```
openai.api.key=your_api_key_here
```

5. Ejecuta la aplicación con Spring Boot:
```
mvn spring-boot:run
```

La aplicación debería estar corriendo y accesible en `http://localhost:8080`.

## 📦 Desarrollo

Para iniciar el servidor en modo de desarrollo, asegúrate de que tienes configurado el perfil de desarrollo en `application.properties` y luego ejecuta:

```
mvn spring-boot:run
```

## 🛠️ Construido con

- [Spring Boot](https://spring.io/projects/spring-boot) - El framework web usado
- [Maven](https://maven.apache.org/) - Manejador de dependencias
- [PokeAPI](https://pokeapi.co) - Tomamos la información más actualizada de los pokemons de aquí
- [OpenAI API](https://beta.openai.com/) - API para generación de sugerencias de equipo

## 📄 Licencia

Este proyecto está bajo la Licencia (tu tipo de licencia) - mira el archivo [LICENSE.md](LICENSE.md) para detalles.

## ✒️ Autores

- **Alex La Fuente**
- **Ezequiel Gomez**


## 📢 Cómo usar la API de OpenAI

Para generar sugerencias de equipo Pokémon, envía una solicitud POST a la API de OpenAI con la información del equipo actual y el nombre de usuario. La respuesta incluirá una lista de Pokémon sugeridos con explicaciones.
```

Recuerda personalizar este README con tu información específica, como tu nombre de usuario de GitHub, detalles de licencia, y otros elementos relevantes para tu proyecto.

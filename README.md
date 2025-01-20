# Foro Hub - Challenge Back End

¡Bienvenido al Challenge BackEnd de Alura!

## Descripción

Foro Hub es una API REST desarrollada con **Spring** que simula el funcionamiento del foro de Alura, donde estudiantes, profesores y moderadores interactúan mediante la creación y gestión de tópicos de discusión. Este proyecto tiene como objetivo comprender cómo se gestionan los datos detrás de un foro, estableciendo relaciones entre tópicos, respuestas y usuarios.

## Funcionalidades

Nuestra API permite realizar las siguientes acciones:

- **Crear un nuevo tópico:** Los usuarios pueden agregar nuevos temas de discusión.
- **Listar todos los tópicos:** Consultar todos los tópicos creados en el foro.
- **Visualizar un tópico específico:** Obtener los detalles de un tópico mediante su identificador.
- **Actualizar un tópico:** Modificar la información de un tópico existente.
- **Eliminar un tópico:** Remover un tópico del foro.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Base de datos MySQL** (según configuración)
- **Maven** (gestor de dependencias)

## Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/dromerof/AluraChallengeForo.git
   ```

2. **Configurar la base de datos:**
   Ajusta el archivo `application.properties` o `application.yml` para la configuración de la base de datos.

3. **Construir el proyecto:**
   ```bash
   ./mvnw clean install
   ```
   o si usas Gradle:
   ```bash
   ./gradlew build
   ```

4. **Ejecutar la aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```
   o
   ```bash
   ./gradlew bootRun
   ```

5. **Acceder a la API:**
   La aplicación estará disponible en: `http://localhost:8080`

## Endpoints de la API

- `POST /topicos` → Crear un nuevo tópico
- `GET /topicos` → Listar todos los tópicos
- `GET /topicos/{id}` → Consultar un tópico específico
- `PUT /topicos/{id}` → Actualizar un tópico existente
- `DELETE /topicos/{id}` → Eliminar un tópico





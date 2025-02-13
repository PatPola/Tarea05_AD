# Tarea05_AD
Aplicación Java e Hibernate-JPA. Tarea 05 de Acceso a datos (MAPEO OBJETO REALCIONAL AVANZADO)|DAM
# Proyecto Hibernate-JPA - Segunda Mano

## Descripción

Este proyecto implementa una aplicación Java utilizando **Hibernate** y **JPA** (Java Persistence API) para la gestión de una bbdd. La aplicación interactúa con una base de datos MySQL que contiene el esquema `segunda_mano` y permite realizar diversas operaciones sobre artículos y categorías.

### Enunciado

Crear un proyecto Hibernate-JPA en Eclipse y generar de forma automática todas las entidades del 
esquema segunda_mano. Posteriormente, crear una aplicación que permita realizar las siguientes 
acciones: 
1. Listar categorías: se listarán las categorías ordenadas alfabéticamente y dentro sus artículos. 
2. Listar artículos: se listarán los artículos ordenados por el título y dentro las categorías a las que 
pertenece cada uno 
3. Insertar artículo: Se solicitarán uno a uno los datos del artículo a excepción del id y del 
id_usuario y se insertará en la BD. 
4. Modificar artículo: Se solicitará el id del artículo a modificar, se irán mostrando los datos 
actuales y solicitando los nuevos y, finalmente, se actualizará en la BD. 
5. Insertar categoría 
6. Insertar artículo en una categoría: se solicitará el id del artículo y el id de la categoría donde 
queremos insertarlo. 
7. Buscar artículo: se solicitará una palabra y se mostrarán todos los artículos que contengan la 
palabra en el título o en la descripción. Hacer uso de una NamedQuery. 
8. Salir 

## Tecnologías utilizadas

- **Java 21** 
- **Hibernate 5** (para la persistencia de datos)
- **JPA 2.2**
- **MySQL 5.7+** (base de datos)
- **Eclipse** (IDE para desarrollo)

## Requisitos

- **Java 8** o superior
- **MySQL** instalado y funcionando
- **Dependencias de Hibernate y JPA** configuradas en el proyecto (mediante Maven o Gradle)
- **Conexión a una base de datos MySQL** con el esquema `segunda_mano` configurado.

## Configuración de la base de datos

1. Clonar o descargar el proyecto desde GitHub.
2. Crear la base de datos en MySQL llamada `segunda_mano`, el script está en el proyecto.
3. Asegúrate de tener los permisos adecuados en la base de datos.
4. En el archivo `persistence.xml` , configura los detalles de la conexión a la base de datos.Hay un archivo de ejemplo en el proyecto




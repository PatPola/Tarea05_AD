package com.accesoadatos.hibernate.tarea05;

import entidades.Articulo;
import entidades.Categoria;
import jdk.jshell.execution.Util;
import util.MyEntityManager;
import util.Utilidades;

import javax.persistence.Query;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Patricia Pola Caballero
 */
public class App {

	/**
	 * 1. Listar categorías: se listarán las categorías ordenadas alfabéticamente y
	 * dentro sus artículos. 2. Listar artículos: se listarán los artículos
	 * ordenados por el título y dentro las categorías a las que pertenece cada uno
	 * 3. Insertar artículo: Se solicitarán uno a uno los datos del artículo a
	 * excepción del id y del id_usuario y se insertará en la BD. 4. Modificar
	 * artículo: Se solicitará el id del artículo a modificar, se irán mostrando los
	 * datos actuales y solicitando los nuevos y, finalmente, se actualizará en la
	 * BD. 5. Insertar categoría 6. Insertar artículo en una categoría: se
	 * solicitará el id del artículo y el id de la categoría donde queremos
	 * insertarlo. 7. Buscar artículo: se solicitará una palabra y se mostrarán
	 * todos los artículos que contengan la palabra en el título o en la
	 * descripción. Hacer uso de una NamedQuery. 8. Salir
	 * 
	 * @param args ion
	 */

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws java.text.ParseException {
		MyEntityManager myEntityManager = new MyEntityManager();

		Query query;
		boolean salir = false;
		int opcion = 0;

		do {
			Utilidades.muestraMenu();

			//compruebo si hay datos en la bbdd
			List<Categoria> categoriasDispo = MyEntityManager.listarCategoriasConArticulos();
			if(categoriasDispo.isEmpty()){
				System.out.println("Empieza insertando una categoria");
			}
			// Comprobamos si hay artículos en la base de datos
			List<Articulo> articulosDispo = MyEntityManager.listarArticulos();
			if (articulosDispo.isEmpty() && (opcion == 2 || opcion == 3 || opcion == 4 || opcion == 6)) {
				System.out.println("No hay artículos en la base de datos. Por favor, inserte al menos un artículo antes de realizar estas operaciones.");
		}
			try {
				opcion = input.nextInt();
				input.nextLine();
				switch (opcion) {
				case 1:
					if (categoriasDispo.isEmpty()) {
						System.out.println("No se pueden listar categorías ya que no hay datos en la base de datos.");
						continue; // Vuelve al menú si no hay categorías
					}
				    System.out.println("---------Listado de categorias----------");
				    List<Categoria> categorias = MyEntityManager.listarCategoriasConArticulos();
				    for (Categoria categoria : categorias) {
				        System.out.println("ID de categoría: " + categoria.getId()); 
				        System.out.println("Categoria: " + categoria.getCategoria());
				        System.out.println("Articulos:");
				        List<Articulo> articulos = categoria.getArticulos();
				        for (Articulo articulo : articulos) {
				        	 System.out.println("  - " + articulo.getTitulo());
				            System.out.println("- ID de artículo: " + articulo.getId());
				        }
				        System.out.println();
				    }
				    break;
				case 2:
					if (articulosDispo.isEmpty()) {
						System.out.println("No hay artículos disponibles para mostrar.");
						continue; // Vuelve al menú si no hay artículos
					}
				    System.out.println("---------Listado de artículos----------");
				    // Obtener todos los artículos ordenados por título
				    List<Articulo> articulos = MyEntityManager.listarArticulos();
				    // Iterar sobre los artículos y mostrar las categorías a las que pertenece cada uno
				    for (Articulo articulo : articulos) {
				        System.out.println("ID de artículo: " + articulo.getId());
				        System.out.println("Título: " + articulo.getTitulo());
				        System.out.println("Descripción: " + articulo.getDescripcion());
				        System.out.println("Precio: " + articulo.getPrecio());
				        System.out.println("Categorías:");
				        for (Categoria categoria : articulo.getCategorias()) {
				        	System.out.println("  - " + categoria.getCategoria());
				            System.out.println("- ID de categoría: " + categoria.getId());
				        }
				        System.out.println();
				    }
				    break;

				case 3:
					System.out.println("---------Insertar artículo----------");

					System.out.println("Introduce descripción");
					String descripcion = Utilidades.solicitaString();
					System.out.println("Introduce precio");
					Float precio = Utilidades.solicitaFloat();
					System.out.println("Introduce título");
					String titulo = Utilidades.solicitaString();
					Date fecha = Utilidades.solicitarFecha();
					// Instancio artículo
					Articulo nuevoArticulo = new Articulo();
					nuevoArticulo.setTitulo(titulo);
					nuevoArticulo.setDescripcion(descripcion);
					nuevoArticulo.setPrecio(precio);
					nuevoArticulo.setFecha(fecha);
					nuevoArticulo.setVendido(false);
					nuevoArticulo.setUsuario(null);
					MyEntityManager.insertarArticulo(nuevoArticulo);
					System.out.println("Artículo insertado correctamente.");
					break;
					case 4:
						System.out.println("---------MODIFICAR ARTÍCULO----------");
						System.out.println("Introduce el id del artículo que quieres modificar");
						int id = Utilidades.solicitaId();

						// Buscar el artículo por ID
						Articulo articulo = MyEntityManager.buscarArticuloPorId(id);

						// Verificar si el artículo existe
						if (articulo != null) {
							System.out.println("Datos actuales del artículo:");
							System.out.println("Título: " + articulo.getTitulo());
							System.out.println("Descripción: " + articulo.getDescripcion());
							System.out.println("Precio: " + articulo.getPrecio());

							// Verificar si el artículo tiene categorías asignadas
							if (articulo.getCategorias() != null && !articulo.getCategorias().isEmpty()) {
								for (Categoria categoria : articulo.getCategorias()) {
									System.out.println("- " + categoria.getCategoria());
								}
							} else {
								System.out.println("Este artículo no tiene categorías asignadas.");
							}

							// Solicitar los nuevos datos al usuario
							System.out.println("Introduce los nuevos datos del artículo:");
							System.out.print("Nuevo título: ");
							String nuevoTitulo = Utilidades.solicitaString();
							System.out.print("Nueva descripción: ");
							String nuevaDescripcion = Utilidades.solicitaString();
							System.out.print("Nuevo precio: ");
							float nuevoPrecio = Utilidades.solicitaFloat();

							// Actualizar el artículo con los nuevos datos
							MyEntityManager.actualizarArticulo(articulo, nuevoTitulo, nuevaDescripcion, nuevoPrecio);
							System.out.println("Artículo actualizado correctamente.");
						} else {
							// Si no se encuentra el artículo, mostrar un mensaje
							System.out.println("El artículo con el ID proporcionado no existe.");
						}
						break;
				case 5:
					System.out.println("---------INSERTAR CATEGORIA----------");
					System.out.println("Introduce nombre para la categoria:");
					String nombreCategoria = Utilidades.solicitaString();
					Categoria nuevaCategoria = new Categoria();
					nuevaCategoria.setCategoria(nombreCategoria);
					MyEntityManager.insertarCategoria(nuevaCategoria);

					break;
				case 6:
					System.out.println("-------INSERTAR ARTÍCULO EN CATEGORIA--------");
					System.out.println("Introduce el id del articulo que quieres insertar en la categoria");
					int idArticulo = Utilidades.solicitaId();
					System.out.println("Introduce el id de la categoria");
					int idCategoria = Utilidades.solicitaId();
					MyEntityManager.insertarArticuloEnCategoria(idArticulo, idCategoria);
					break;
				case 7:
				    System.out.println("-------Buscar artículo por palabra clave--------");
				    System.out.println("Introduce la palabra clave:");
				    String palabraClave = input.nextLine();
				    List<Articulo> articulosEncontrados = MyEntityManager.buscarArticulosPorPalabraClave(palabraClave);
				    if (articulosEncontrados.isEmpty()) {
				        System.out.println("No se encontraron artículos con la palabra clave proporcionada.");
				    } else {
				        System.out.println("Artículos encontrados:");
				        for (Articulo articu : articulosEncontrados) {
				            System.out.println("- " + articu.getTitulo());
				            System.out.println("  " + articu.getDescripcion());
				            System.out.println(); // Espacio entre artículos
				        }
				    }
				    break;

				case 8:
					salir = true;
					break;

				default:
					System.out.println("Las opciones son números del 1 al 8");
				}
			} catch (InputMismatchException e) {
				System.out.println("No has introducido un número");
				input.nextLine();
			}
		} while (!salir);

	}

}

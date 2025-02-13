package util;

import java.util.List;

import javax.persistence.*;

import entidades.Articulo;
import entidades.Categoria;

public class MyEntityManager {
// instancia de EntityManager, que se utilizará para interactuar con la base de datos utilizando JPA (Java Persistence API).
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("tarea05");
	public static EntityManager em = (EntityManager) emf.createEntityManager();

	/**
	 * 
	 * @return
	 */
	public static List<Categoria> listarCategoriasConArticulos() {
	    EntityManager em = emf.createEntityManager();
	    try {
	        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findAllOrderedByName", Categoria.class);
	        
	        List<Categoria> categorias = query.getResultList();
	        // Cargar las colecciones de forma ansiosa (eager loading)
	        for (Categoria categoria : categorias) {
	            categoria.getArticulos().size(); // Carga la colección de artículos
	        }
	        
	        return categorias;
	    } finally {
	        em.close();
	    }
	}

	public static List<Articulo> listarArticulos() {
	    EntityManager em = emf.createEntityManager();
	    try {
	        TypedQuery<Articulo> query = em.createNamedQuery("Articulo.findAllOrderedByTitle", Articulo.class);
	        List<Articulo> articulos = query.getResultList();
	        
	        // Cargar las colecciones de forma ansiosa (eager loading)
	        for (Articulo articulo : articulos) {
	            articulo.getCategorias().size(); // Carga la colección de categorías
	        }
	        
	        return articulos;
	    } finally {
	        em.close();
	    }
	}

	public static void insertarArticulo(Articulo articulo) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(articulo);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	public static void insertarCategoria(Categoria categoria) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	 public static void actualizarArticulo(Articulo articulo, String nuevoTitulo, String nuevaDescripcion, float nuevoPrecio) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            articulo.setTitulo(nuevoTitulo);
	            articulo.setDescripcion(nuevaDescripcion);
	            articulo.setPrecio(nuevoPrecio);
	            em.merge(articulo);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx.isActive()) {
	                tx.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }
	 
	 public static void insertarArticuloEnCategoria(int idArticulo, int idCategoria) {
		    EntityManager em = emf.createEntityManager();
		    EntityTransaction tx = em.getTransaction();
		    try {
		        tx.begin();
		        
		        // Obtener el artículo y la categoría por sus respectivos IDs
		        Articulo articulo = em.find(Articulo.class, idArticulo);
		        Categoria categoria = em.find(Categoria.class, idCategoria);
		        
		        // Verificar si el artículo y la categoría existen
		        if (articulo == null) {
		            System.out.println("No se encontró el artículo con el ID especificado.");
		            return;
		        }
		        
		        if (categoria == null) {
		            System.out.println("No se encontró la categoría con el ID especificado.");
		            return;
		        }
		        
		        // Asociar el artículo con la categoría
		        articulo.getCategorias().add(categoria);
		        categoria.getArticulos().add(articulo);
		        
		        em.merge(articulo);
		        em.merge(categoria);
		        
		        tx.commit();
		        System.out.println("Artículo insertado en la categoría exitosamente.");
		    } catch (Exception e) {
		        if (tx.isActive()) {
		            tx.rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        em.close();
		    }
		}
	public static Articulo buscarArticuloPorId(int idArticulo) {
		EntityManager em = emf.createEntityManager();
		try {
			// Buscar el artículo por su ID
			Articulo articulo = em.find(Articulo.class, idArticulo);
			// Verificar si el artículo fue encontrado
			if (articulo != null) {
				// Si el artículo existe, cargar la colección de categorías de manera ansiosa (eager loading)
				articulo.getCategorias().size();
			}
			return articulo; // Si el artículo es null, retornará null de forma segura.
		} finally {
			em.close();
		}
	}
	 public static List<Articulo> buscarArticulosPorPalabraClave(String palabraClave) {
		    EntityManager em = emf.createEntityManager();
		    try {
		        TypedQuery<Articulo> query = em.createNamedQuery("Articulo.buscarPorPalabraClave", Articulo.class);
		        query.setParameter("palabraClaveUpper", "%" + palabraClave.toUpperCase() + "%");
		        return query.getResultList();
		    } finally {
		        em.close();
		    }
		}

}

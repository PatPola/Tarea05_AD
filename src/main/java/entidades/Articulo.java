package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the articulos database table.
 *
 * 
 */
@Entity
@Table(name="articulos")
@NamedQuery(name="Articulo.findAll", query="SELECT a FROM Articulo a")
@NamedQuery(name = "Articulo.buscarPorPalabraClave", query = "SELECT a FROM Articulo a WHERE UPPER(a.titulo) LIKE :palabraClaveUpper OR UPPER(a.descripcion) LIKE :palabraClaveUpper")
@NamedQuery(name = "Articulo.findAllOrderedByTitle", query = "SELECT a FROM Articulo a ORDER BY a.titulo")

public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String descripcion;

	private Date fecha;

	private float precio;

	private String titulo;

	private boolean vendido;

	//bi-directional many-to-many association to Categoria
	@ManyToMany
	@JoinTable(
		name="articulos_categorias"
		, joinColumns={
			@JoinColumn(name="id_articulo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_categoria")
			}
		)
	private List<Categoria> categorias;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="articulo")
	private List<Compra> compras;

	public Articulo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean getVendido() {
		return this.vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public List<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setArticulo(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setArticulo(null);

		return compra;
	}
	

}
package br.exemplo.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import br.exemplo.model.Produto;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.FetchType;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
@Entity
@XmlRootElement
public class Categoria implements Serializable, Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private int version;

	@Column(length = 20, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Produto> produtos = new HashSet<Produto>();

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		return result;
	}

	public Set<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(final Set<Produto> produtos) {
		this.produtos = produtos;
	}
}
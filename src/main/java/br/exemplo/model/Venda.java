package br.exemplo.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import br.exemplo.model.Item;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@XmlRootElement
public class Venda implements Serializable, Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

	@Column
	private Date data;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Item> itens = new HashSet<Item>();

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
		if (!(obj instanceof Venda)) {
			return false;
		}
		Venda other = (Venda) obj;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Set<Item> getItens() {
		return this.itens;
	}

	public void setItens(final Set<Item> itens) {
		this.itens = itens;
	}
}

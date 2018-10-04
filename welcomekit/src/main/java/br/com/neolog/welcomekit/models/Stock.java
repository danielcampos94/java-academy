package br.com.neolog.welcomekit.models;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_sequence")
	@SequenceGenerator(name = "stock_sequence", initialValue = 1, allocationSize = 1)
	private Integer id;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product", unique = true)
	private Product product;

	@Column
	@NotNull
	private int quantity;
	
	public Stock() {
		
	}

	public Stock(final Product product, final int quantity) {
		this.product = requireNonNull(product);
		this.quantity = quantity;
	}
	
	public Stock(final Integer id, final Product product, final int quantity) {
		this.id = id;
		this.product = requireNonNull(product);
		this.quantity = quantity;
	}
	

	public Integer getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public String toString(){
		return MoreObjects.toStringHelper(Stock.class)
				.add("id", id)
				.add("product", product)
				.add("quantity", quantity).toString();
	}
}

/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package br.exemplo.beans;

import java.io.Serializable;

import br.exemplo.model.Produto;

/**
 *
 * @author tgiunipero
 */
public class ShoppingCartItem implements Serializable {

	Produto product;
	short quantity;

	public ShoppingCartItem(Produto product) {
		this.product = product;
		quantity = 1;
	}

	public Produto getProduct() {
		return product;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}

	public void decrementQuantity() {
		quantity--;
	}

	public double getTotal() {
		double amount = 0;
		amount = (this.getQuantity() * product.getPreco());
		return amount;
	}
}
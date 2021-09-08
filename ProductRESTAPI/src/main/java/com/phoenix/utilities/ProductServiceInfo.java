package com.phoenix.utilities;

import com.phoenix.entities.Product;

public class ProductServiceInfo {
	
	private Product product;
	private int serverPort;
	
	public ProductServiceInfo() {
		// TODO Auto-generated constructor stub
	}

	public ProductServiceInfo(Product product, int serverPort) {
		super();
		this.product = product;
		this.serverPort = serverPort;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	@Override
	public String toString() {
		return "ProductServiceInfo [product=" + product + ", serverPort=" + serverPort + "]";
	}
	
	

}

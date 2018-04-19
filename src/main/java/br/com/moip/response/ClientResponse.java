package br.com.moip.response;

import java.io.Serializable;

import br.com.moip.domain.Client;

public class ClientResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8319628283436915439L;
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientResponse(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Client toClient() {
		Client c = new Client();
		c.setId(Long.valueOf(this.id));
		c.setName(this.name);
		return c;
	}
	
	
}

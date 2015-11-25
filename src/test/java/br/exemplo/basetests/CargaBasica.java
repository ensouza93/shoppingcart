package br.exemplo.basetests;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.Test;

import br.exemplo.model.Categoria;
import br.exemplo.model.Produto;
import br.exemplo.model.Usuario;

public class CargaBasica {

	private final static String BASE_URL = "http://localhost:8080/shoppingcart/rest/";
	
	@Test
	public void cadastrarDadosBasicos() {

		for (int i = 1; i < 10; i++) {
			Usuario user = new Usuario();
			user.setNome("Usuario" + i);
			user.setEmail(user.getNome().toLowerCase() + "@disney.com");
			user.setSenha("1234");
			salvar(user);
		}

		for (int c = 1; c <= 10; c++) {

			Categoria categoria = new Categoria();
			categoria.setNome("Categoria " + c);
			Long id = salvar(categoria);
			categoria.setId(id);

			for (int p = 1; p <= 10; p++) {
				Produto produto = new Produto();
				produto.setNome("Produto " + p + " cat" + c);
				produto.setCategoria(categoria);
				salvar(produto);
			}
		}

	}

	private void salvar(Usuario user) {
		
		System.out.println("Salvando usuario");
		
		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();
		
		Entity<Usuario> usuario = Entity.json(user);
		String uri = BASE_URL + "usuarios";
		System.out.println(uri);
		
		WebTarget target = client.target(uri);

		Response respostaGravacao = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(usuario);
		
		System.out.println("Resposta " + respostaGravacao.getStatus());
		
		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});
		
		assertEquals(Status.CREATED.getStatusCode(), respostaGravacao.getStatus());
		
		client.close();
	}

	private Long salvar(Categoria categoria) {
		System.out.println("Salvando categoria");
		
		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();
		
		Entity<Categoria> categoriaJson = Entity.json(categoria);
		String uri = BASE_URL + "categoria";
		System.out.println(uri);
		
		WebTarget target = client.target(uri);

		Response respostaGravacao = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(categoriaJson);
		
		System.out.println("Resposta " + respostaGravacao.getStatus());
		
		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});
		
		assertEquals(Status.CREATED.getStatusCode(), respostaGravacao.getStatus());
		
		client.close();
		
		String urlCatCriada = (String) respostaGravacao.getHeaders().get("Location").get(0);
		
		return Long.parseLong(urlCatCriada.substring(urlCatCriada.lastIndexOf('/')+1));
	}

	private void salvar(Produto produto) {
		System.out.println("Salvando produto");
		
		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();
		
		Entity<Produto> categoriaJson = Entity.json(produto);
		String uri = BASE_URL + "produtos";
		System.out.println(uri);
		
		WebTarget target = client.target(uri);

		Response respostaGravacao = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(categoriaJson);
		
		System.out.println("Resposta " + respostaGravacao.getStatus());
		
		assertEquals(Status.CREATED.getStatusCode(), respostaGravacao.getStatus());
		
		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});
		
		client.close();
	}
}

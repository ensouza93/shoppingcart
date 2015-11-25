package br.exemplo.basetests;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import br.exemplo.model.Entidade;

public class BaseTest {

	protected final static String BASE_URL = "http://localhost:8080/shoppingcart/rest/";
	private ClientBuilder clientBuilder;

	protected void apagarTodos(String endpoint) {

		clientBuilder = ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class);

		String url = BASE_URL + endpoint;

		List<Entidade> listaCategorias = null;

		Client clienttmp = clientBuilder.build();

		WebTarget destinoGravacao = clienttmp.target(url);

		listaCategorias = destinoGravacao.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Entidade>>() {
				});

		listaCategorias.forEach(e -> {
			Client clientDel = clientBuilder.build();
			WebTarget targetDel = clientDel.target(url + "/" + e.getId());

			targetDel.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();

			clientDel.close();
		});
	}
}

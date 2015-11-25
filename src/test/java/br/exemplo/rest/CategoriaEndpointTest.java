package br.exemplo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.exemplo.basetests.BaseTest;
import br.exemplo.model.Categoria;

public class CategoriaEndpointTest extends BaseTest {

	private static final String ENDPOINT = "categoria";
	private static Client client;
	private static ClientBuilder clientBuilder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clientBuilder = ClientBuilder.newBuilder().register(JacksonJaxbJsonProvider.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client = clientBuilder.build();

//		super.apagarTodos(ENDPOINT);
	}

	@After
	public void tearDown() throws Exception {
		client.close();
		System.out.println("---------------------------------------");
	}

	@Test
	public void testCreate() {

		Categoria categoria = new Categoria();
		categoria.setNome("Teste1");
		
		Entity<Categoria> categoriaJson = Entity.json(categoria);
		WebTarget target = client.target(BASE_URL + ENDPOINT);

		Response respostaGravacao = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(categoriaJson);
		
		assertEquals(Status.CREATED.getStatusCode(), respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println(e.getKey() + "\t" + e.getValue());
		});

		String locationItemCriado = (String) respostaGravacao.getHeaders().get("Location").get(0);

		System.out.println(locationItemCriado);
		
		

	}

	@Ignore
	@Test
	public void testDeleteById() {

		Categoria categoria = new Categoria();
		categoria.setNome("Teste1");

		Entity<Categoria> clienteJson = Entity.json(categoria);
		WebTarget destinoGravacao = client.target("http://localhost:8080/shoppingcart/rest/categoria" + "/2");

		Response respostaGravacao = destinoGravacao.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).delete();

		assertEquals(Status.ACCEPTED.getStatusCode(), respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println(e.getKey() + "\t" + e.getValue());
		});

	}

	@Ignore
	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testListAll() {

		WebTarget destinoGravacao = client.target("http://localhost:8080/shoppingcart/rest/categoria");

		List<Categoria> respostaGravacao = destinoGravacao.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Categoria>>() {
				});

		respostaGravacao.forEach(System.out::println);

		// assertEquals(Status.OK.getStatusCode(),
		// respostaGravacao.getStatus());
		//
		// respostaGravacao.getHeaders().entrySet().forEach(e -> {
		// System.out.println(e.getKey() + "\t" + e.getValue());
		// });
		//
		//
		// List<Categoria> lista = respostaGravacao.readEntity(List.class);
		//
		// respostaGravacao.
		//
		// System.out.println(respostaGravacao.getEntity());

	}

	@Ignore
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}

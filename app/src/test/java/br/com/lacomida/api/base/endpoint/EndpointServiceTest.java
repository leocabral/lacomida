package br.com.lacomida.api.base.endpoint;

import org.junit.Test;

import br.com.lacomida.util.Injector;
import retrofit2.http.GET;

import static org.junit.Assert.assertNotNull;

public class EndpointServiceTest {

	@Test
	public void testGet() {
		EndpointService endpointService = Injector.get(EndpointService.class);
		Object testEndpoint = endpointService.get(TestEndpoint.class);

		assertNotNull(testEndpoint);
	}

	public interface TestEndpoint {

		@GET
		void get();
	}
}
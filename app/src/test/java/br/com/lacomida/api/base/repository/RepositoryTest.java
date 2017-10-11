package br.com.lacomida.api.base.repository;

import org.junit.Test;

import br.com.lacomida.util.Injector;
import retrofit2.http.GET;

import static org.junit.Assert.assertNotNull;

public class RepositoryTest {

	@Test
	public void testGet() {
		TestRepository testRepository = Injector.get(TestRepository.class);

		assertNotNull(testRepository);
	}

	public static class TestRepository extends Repository<TestEndpoint> {

		@Override
		protected Class<TestEndpoint> endpointClass() {
			return TestEndpoint.class;
		}
	}

	private interface TestEndpoint {

		@GET
		void get();
	}
}
package br.com.lacomida.api.base.repository;

import br.com.lacomida.api.base.endpoint.EndpointService;
import br.com.lacomida.util.Injector;

public abstract class Repository<E> {

	@SuppressWarnings("unchecked")
	protected E getEndpoint() {
		return Injector.get(EndpointService.class).get(endpointClass());
	}

	protected abstract Class<E> endpointClass();
}
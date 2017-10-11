package br.com.lacomida.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import br.com.lacomida.persistence.PersistenceLayer;
import br.com.lacomida.persistence.SharedPreferencesPersistenceLayer;
import br.com.lacomida.util.Injector;

public class LaComidaApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		initializeFresco();
		initializePersistenceLayer();
	}

	private void initializeFresco() {
		ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
				.setDownsampleEnabled(true)
				.build();
		Fresco.initialize(this, config);
	}

	private void initializePersistenceLayer() {
		Injector.set(PersistenceLayer.class, SharedPreferencesPersistenceLayer.class);
		Injector.get(PersistenceLayer.class).init(this);
	}
}

package in.careerscale.apps.ocms.integration.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class OAuthServiceProvider {
	
	private OAuthServiceConfig config;
	
	public OAuthServiceProvider() {
	}
	
	public OAuthServiceProvider(OAuthServiceConfig config) {
		this.config = config;
	}

	public OAuthService getService() {
		System.out.println(config);
		if(config.getScope()==null)
			return new ServiceBuilder().provider(config.getApiClass())
								.apiKey(config.getApiKey())
							    .apiSecret(config.getApiSecret())
							    .callback(config.getCallback())
							    .build();
			else{
				return new ServiceBuilder().provider(config.getApiClass())
						.apiKey(config.getApiKey())
					    .apiSecret(config.getApiSecret())
					      .scope(config.getScope())
					    .callback(config.getCallback())
					    .build();
			}
	}
	
}

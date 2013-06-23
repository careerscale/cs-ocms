package in.careerscale.apps.rms.integration.oauth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class OAuthServiceProvider {
	
	Log log = LogFactory.getLog(OAuthServiceProvider.class);
	private OAuthServiceConfig config;
	
	public OAuthServiceProvider() {
	}
	
	public OAuthServiceProvider(OAuthServiceConfig config) {
		this.config = config;
	}

	public OAuthService getService() {		
		log.debug(config);
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

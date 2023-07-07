package com.x.integration.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Map;
import java.util.function.Function;

/**
 * OAuth2客户端配置，比如调用打印服务，配置后可通过注入OAuth2AuthorizedClientManager获取token
 *
 * @author PanLei
 * @version 1.0.0
 * @createTime 2023-06-16
 */
@Configuration
public class OAuth2AuthorizeClientConfig {

    public static final String PRINT_REGISTRATION_ID = "mes";
    public static final  String CLIENT_NAME = "integration";
    private String clientId;
    private String clientSecret;
    private String tokenUri;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        this.initMesConfigProperties();
        return new InMemoryClientRegistrationRepository(this.printClientRegistration());
    }

    private void initMesConfigProperties()
    {
        String mesProtocol = "http";
        String mesHost = "localhost";
        String mesPort = "8881";
        String mesPath = "/mes";
        String mesClientId = "client";
        String mesClientSecret = "secret";


        this.tokenUri = mesProtocol + "://" + mesHost + ":" + mesPort + mesPath + "/oauth/token";
        this.clientId = mesClientId;
        this.clientSecret = mesClientSecret;

    }
    private ClientRegistration printClientRegistration() {
        return ClientRegistration.withRegistrationId(PRINT_REGISTRATION_ID)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .tokenUri(tokenUri)
                .clientName(CLIENT_NAME)
                .build();
    }


    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .refreshToken()
                .clientCredentials()
                .password()
                .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        authorizedClientManager.setContextAttributesMapper(new Function<OAuth2AuthorizeRequest, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply(OAuth2AuthorizeRequest oAuth2AuthorizeRequest) {
                return oAuth2AuthorizeRequest.getAttributes();
            }
        });

        return authorizedClientManager;
    }
}

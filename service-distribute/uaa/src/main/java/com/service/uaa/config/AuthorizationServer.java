package com.service.uaa.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@PropertySource("classpath:application-uaa.properties")
@EnableAuthorizationServer
public class AuthorizationServer extends
        AuthorizationServerConfigurerAdapter {

    @Value("${AccessTokenValiditySeconds}")  private int AccessTokenValiditySeconds;
    @Value("${RefreshTokenValiditySeconds}") private int RefreshTokenValiditySeconds;
    @Value("${Client.a}") private String ClientA;
    @Value("${secret.a}") private String SecretA;
    @Value("${resourceIds.a}") private String ResourceIdsA;
    @Value("${scopes.a}") private String ScopesA;
    @Value("${redirectUris.a}") private  String RedirectUrisA;
    @Value("${Client.b}") private String ClientB;
    @Value("${secret.b}") private String SecretB;
    @Value("${resourceIds.b}") private String ResourceIdsB;
    @Value("${scopes.b}") private String ScopesB;
    @Value("${redirectUris.b}") private  String RedirectUrisB;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        services.setAccessTokenValiditySeconds(AccessTokenValiditySeconds);
        services.setRefreshTokenValiditySeconds(RefreshTokenValiditySeconds);
        return services;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)  {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(ClientA)
                .secret(NoOpPasswordEncoder.getInstance().encode(SecretA))
                .resourceIds(ResourceIdsA)
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                .scopes(ScopesA)
                .autoApprove(false)
                .redirectUris(RedirectUrisA)
                .and()
                .inMemory()
                .withClient(ClientB)
                .secret(new BCryptPasswordEncoder().encode(SecretB))
                .resourceIds(ResourceIdsB)
                .authorizedGrantTypes("authorization_code",
                        "password","client_credentials","implicit","refresh_token")
                .scopes(ScopesB)
                .autoApprove(false)
                .redirectUris(RedirectUrisB);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}

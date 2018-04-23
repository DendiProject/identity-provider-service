package com.dendiproject.identityproviderservice.config;

import com.dendiproject.identityproviderservice.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private ClientDetailsService clientDetailsService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AppConfig appConfig;
  @Autowired
  private TokenStore tokenStore;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

    security.checkTokenAccess("isAuthenticated()");

  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(appConfig.dataSource());
//                 clients.inMemory()
//                        .withClient("ui")
//                        .secret("uipass")
//                        .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
//                        .scopes("read","read","trust")
//                        .authorizedGrantTypes("client_credentials")
//                        .accessTokenValiditySeconds(30000);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    endpoints.authenticationManager(authenticationManager)
            .tokenStore(tokenStore);
  }
}

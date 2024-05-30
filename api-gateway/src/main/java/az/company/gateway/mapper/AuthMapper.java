package az.company.gateway.mapper;

import az.company.gateway.model.AuthResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static az.company.gateway.mapper.DateMapper.DATE_MAPPER;

public enum AuthMapper {
    AUTH_MAPPER;

    public AuthResponse buildAuthResponse(OidcUser oidcUser,
                                          OAuth2AuthorizedClient client) {
        return AuthResponse.builder()
                .userId(oidcUser.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expiresAt(DATE_MAPPER.toLocalDateTime(client.getAccessToken().getExpiresAt()))
                .authorities(
                        oidcUser.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                )
                .build();
    }
}

package az.company.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;
    private Collection<String> authorities;
}

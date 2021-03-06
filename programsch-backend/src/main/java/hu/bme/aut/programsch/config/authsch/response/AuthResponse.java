package hu.bme.aut.programsch.config.authsch.response;

import hu.bme.aut.programsch.config.authsch.struct.Scope;

import java.io.Serializable;
import java.util.List;


public final class AuthResponse implements Serializable {

    private static final long serialVersionUID = 4140354200501250401L;

    private final String accessToken;
    private final long expiresIn;
    private final String tokenType;
    private final List<Scope> scopes;
    private final String refreshToken;

    public AuthResponse(String accessToken, long expiresIn,
                        String tokenType, List<Scope> scopes, String refreshToken) {

        this.accessToken = accessToken;
        this.expiresIn = System.currentTimeMillis() + (expiresIn * 1000);
        this.tokenType = tokenType;
        this.scopes = scopes;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "AuthResponse [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", tokenType=" + tokenType
                + ", scopes=" + scopes + ", refreshToken=" + refreshToken + "]";
    }

}
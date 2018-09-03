package chatapp.tokens;

import net.bytebuddy.utility.RandomString;

public class TokenGenerator {
    private String token;
    private static final int length = 64;

    public TokenGenerator() {
        token = RandomString.make(length);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

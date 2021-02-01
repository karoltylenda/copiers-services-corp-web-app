package com.tytanisukcesu.copiers.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expirationTime;
    private final String secret;

    public RestAuthenticationSuccessHandler(
            @Value("${jwt.expirationTime}") long expirationTime,
            @Value("${jwt.secret}") String secret) {
        this.expirationTime = expirationTime;
        this.secret = secret;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetails principal = (UserDetails) authentication.getPrincipal(); //pobieramy nasze user details

        //przekazujemy je pozniej do tokena
        String token = JWT.create()
                .withSubject(principal.getUsername()) //nasz token bedzie zawieral nasz username
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime)) //date wygasniecia
                .sign(Algorithm.HMAC256(secret)); //oraz nasz secret do zrobienia sygnatury
        response.addHeader("Authorization", "Bearer " + token);
        response.getOutputStream().print("{\"token\": \"" + token + "\"}");
    }

}


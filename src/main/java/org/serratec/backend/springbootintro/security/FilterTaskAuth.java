package org.serratec.backend.springbootintro.security;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

/**
 * Classes que o spring gerencia
 * precisa estar com suas anotacoes
 * como controller, service, component etc.
 */

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // autenticacao (usuario e senha)

       var authorization = request.getHeader("Authorization");


        /**
         * substring - ele possui o metodo para remocao de algo
         */

       var authDecoded = authorization.substring("Basic".length()).trim();

          byte[] authDecod = Base64.getDecoder().decode(authDecoded);

        System.out.println("Authorization");
        System.out.println(authorization);


        // validacao (usuario)

        // validacao (senha)

        // tudo ok
        filterChain.doFilter(request, response);
    }
}


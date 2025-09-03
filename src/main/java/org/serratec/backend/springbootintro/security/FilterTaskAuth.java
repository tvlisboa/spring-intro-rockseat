package org.serratec.backend.springbootintro.security;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.serratec.backend.springbootintro.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

/**
 * Classes que o spring gerencia
 * precisa estar com suas anotacoes
 * como controller, service, component etc.
 */

/**
 * Rever aula de implementacao de auth
 */

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private PessoaRepository pessoaRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //validacao da rota
        var servletPath = request.getServletPath();
            if (servletPath.equals("/tasks/")) {
                // autenticacao (usuario e senha)
                var authorization = request.getHeader("Authorization");


                /**
                 * substring - ele possui o metodo para extrair algum conteudo
                 */

                var authEncoded = authorization.substring("Basic".length()).trim();

                /**
                 * Conversao do array de bytes para uma string
                 */
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                var authString = new String(authDecode);

                //[username: "admin", password: "calabresa123"]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
                System.out.println("Authorization");
                System.out.println("username: " + username);
                System.out.println("password: " + password);


                // validacao (usuario)
                var user = this.pessoaRepository.findByUsername(username);
                if(user == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Username not found");
                }else{
                    // validacao (senha)
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if(passwordVerify.verified){
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    }else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Password");
                    }
                }

            }else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
    }
}


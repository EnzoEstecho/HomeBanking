package com.mindhub.homebanking.Configurations;

import com.mindhub.homebanking.Services.ClientServices;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//Primero exitencia y despues lo demas( me fijo quien es)
@Configuration // Le indicamos que yo quiero trabajar con mis configuraciones propias de seguridad

//las clases pueden heredar de las clases y de las interfaces

public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    ClientServices clientServices;

    @Bean // este bean se encarga de codificar la contraseña
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //clave encriptada

    }


    @Override // este metodo tiene que ser igual al que esta por defecto ( corrobora que estas sobreescribiendo)

    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(email-> { //localiza el usuario segun el email(email) , autenticacion a tu gusto
            Client client = clientServices.findByEmail(email);

            if (client != null) {
                if (client.getEmail().contains("ADMIN")) {
                    return new User(client.getEmail(), client.getPassword(), //new user va a contener el session id
                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {

                    return new User(client.getEmail(), client.getPassword(), //new user va a contener el session id
                            AuthorityUtils.createAuthorityList("CLIENT")); // con esto cramos el rol de cliente
                }
                } else{
                    throw new UsernameNotFoundException("Unknown email" + email); //no se encontro el user
                }


        });
    }



}

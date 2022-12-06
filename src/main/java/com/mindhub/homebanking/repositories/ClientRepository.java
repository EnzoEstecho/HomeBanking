package com.mindhub.homebanking.repositories;

//interface que conecta h2 con la clase
// aca se hereda de jpa( dependencia del principio) TOOD LO DE JPA LO HEREDA CLIENTREPOSITORY
// le hereda client y long a client repository
//jpa es dependencia de spring

import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource // trabaja con restricciones de rest
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByEmail(String email); //usa repositorio para buscar cliente por email


}

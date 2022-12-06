package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//h2 es una base de datos relacional y no persistente


@Entity // genero tabla en base de datos donde almaceno datos de client

public class Client {

    @Id // indico que id va a ser la clave primaria

    @GeneratedValue(strategy = GenerationType.AUTO) // genera valor del id en nuestra columna (automatico)

    @GenericGenerator(name="native", strategy = "native")// se fija si existe el id para no repetir
    private long id;


    private String firstName;
    private String lastName;
    private String email;

    private String password;
    @OneToMany(mappedBy = "client", fetch= FetchType.EAGER) // uno a muchos , asocia cliente de account con cliente
    Set<Account> accounts = new HashSet<>(); //es desordenado para que sea mas facil no repetirse

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();


    public Client(){

    }

    public Client(String firstName, String LastName, String email, String password) { // contruis client con atributos
        this.firstName= firstName;
        this.lastName= LastName;
        this.email= email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {  // metodo para obtener al valor
        return firstName; // cuando ejecute me retorna el primer nombre del cliente , que es un string
    }

    public void setFirstName(String firstName) {  // metodo para cambiar el valor ( void retorna vacio)
        this.firstName = firstName; // La propiedad first name ahora va  a ser el primer nombre que ingrese
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Account> getAccounts() {  // aca adentro vienen todas las cuentas que queres guardar
        return accounts;
    }


    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public Set<Card> getCards() {
        return cards;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() { //retornq un string compuesto asi
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

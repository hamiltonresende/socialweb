package com.socialweb.socialweb.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// O lombok através do @Data implementa os métodos getters and setters, construtores e mais por default
@Data
@Entity
// A anotacao @Table possibilita setar o nome customizado para a tabela users
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String userName;

    private String displayName;

    private String password;
}

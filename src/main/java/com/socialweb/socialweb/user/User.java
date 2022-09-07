package com.socialweb.socialweb.user;

import lombok.Data;

// O lombok através do @Data implementa os métodos getters and setters, construtores e mais por default
@Data
public class User {
    private String userName;

    private String displayName;

    private String password;
}

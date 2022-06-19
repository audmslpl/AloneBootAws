package com.DogbirdFoot.AloneBootAws.config.auth.dto;

import com.DogbirdFoot.AloneBootAws.domain.user.NormalUser;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(NormalUser normalUser) {
        this.name = normalUser.getName();
        this.email = normalUser.getEmail();
        this.picture = normalUser.getPicture();
    }
}

package com.acmspring.co.gestioncomercial.enums;

import lombok.*;

@Getter
public enum RoleEnum {
    ADMIN ("ADMIN"),
    USER ("USER"),
    MODERATOR ("MODERATOR");
    private final String rolenName;
    RoleEnum(String rolenName) {
        this.rolenName = rolenName;
    }
}

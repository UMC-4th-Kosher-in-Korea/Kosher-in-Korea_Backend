package com.kusher.kusher_in_korea.auth.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GoogleUserInfo {
    private String id;

    private String email;

    @JsonProperty("verified_email")
    private boolean verified_email;

    private String name;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    private String picture;

    private String locale;

    private String hd;
}
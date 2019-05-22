package com.packt.springsecurity.backend.persistence.setup;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public final class SecurityConstants {

    // to be cleared out

    public static final String ADMIN1_USERNAME = "admin1";
    public static final String ADMIN1_PASS = "admin1Pass";
    public static final String ADMIN2_USERNAME = "admin2";
    public static final String ADMIN2_PASS = "admin2Pass";
    public static final String USER1_USERNAME = "user1";
    public static final String USER1_PASS = "user1Pass";

    // privileges

    public static final class Authorities {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    private SecurityConstants() {
        throw new AssertionError();
    }

    // API

    public static Set<String> getNamesOfAuthoritiesForAdmin() {
        return ImmutableSet.of(// @formatter:off
            Authorities.USER,
            Authorities.ADMIN
        );
        // @formatter:on
    }

    public static Set<String> getNamesOfAuthoritiesForUser() {
        return ImmutableSet.of(Authorities.USER);
    }

    public static String[] getNamesOfAuthoritiesForAdminAsArray() {
        return new String[] { Authorities.USER, Authorities.ADMIN };
    }

    public static String[] getNamesOfAuthoritiesForUserAsArray() {
        return new String[] { Authorities.USER };
    }

}

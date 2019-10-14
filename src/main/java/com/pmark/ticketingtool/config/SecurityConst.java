package com.pmark.ticketingtool.config;

public class SecurityConst {

    public static final String SECRET = "bmVtIHR1ZG9tIG1pIGxlZ3llbiBhIHNlY3JldCBtZXJ0IG5hZ3lvbiBzb2sga2FyYWt0ZXIga2VsbA==";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final long EXPIRATION = 86400000L;


    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "matrix-guard";
    public static final String TOKEN_AUDIENCE = "matrix-user";

    public static final String AUTH_URL = "/authenticate";





}

package hr.ispit.drzave.dto;
public record TokenResponse(String accessToken,String refreshToken,String tokenType) { public TokenResponse(String a,String r){this(a,r,"Bearer");} }


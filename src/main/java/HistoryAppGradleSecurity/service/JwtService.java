package HistoryAppGradleSecurity.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    UserDetails extractUserFromToken(String token);
}

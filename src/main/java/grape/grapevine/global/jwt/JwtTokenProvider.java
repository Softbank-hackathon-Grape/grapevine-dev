package grape.grapevine.global.jwt;

import grape.grapevine.application.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider implements InitializingBean {
  private final String secret;
  private final long accessTokenValidityInMilliseconds;
  private SecretKey key;
  public JwtTokenProvider(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.access-token-validity-in-milliseconds}") long accessTokenValidityInMilliseconds) {
    this.secret = secret;
    this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
  }

  @Override
  public void afterPropertiesSet() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String createAccessToken(User user) {
    long now = System.currentTimeMillis();
    Date expiry = new Date(now + accessTokenValidityInMilliseconds);

    return Jwts.builder()
        .setSubject(user.getUserId())
        .setIssuedAt(new Date(now))
        .setExpiration(expiry)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.info("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.info("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT 토큰이 잘못되었습니다.");
    }
    return false;
  }

  private Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public Long getExpiration(String accessToken) {
    Date expiration = getClaims(accessToken).getExpiration();
    long now = System.currentTimeMillis();
    return expiration.getTime() - now;
  }
}
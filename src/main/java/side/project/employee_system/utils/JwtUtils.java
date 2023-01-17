package side.project.employee_system.utils;

import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="side-project.jwt")
public class JwtUtils {

  private Long expire;

  private String secret;

  private String header;

  // 產生 JWT
  public String generatorJwtToken(String username) {
    Date nowDate = new Date();
    Date expireDate = new Date(nowDate.getTime() + 1000 * expire);
    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setSubject(username)
        .setIssuedAt(nowDate)
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  // 讀取 JWT
  public Claims getClaimsByToken(String jwt) {
    try {
      return Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(jwt)
          .getBody();
    } catch (Exception e) {
      return null;
    }
  }

  // JWT 過期驗證
  public Boolean isTokenExpired(Claims claims) {
    return claims.getExpiration().before(new Date());
  }

}

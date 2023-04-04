import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JWTGenerator {

    // Method to generate a JWT token
    public String generateToken(String secretKey, String issuer, String subject, long ttlMillis) {

        // Set the JWT signature algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // Set the JWT issued and expiration dates
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + ttlMillis);

        // Build the JWT claims
        Claims claims = Jwts.claims().setSubject(subject);
        claims.setIssuer(issuer);
        claims.setIssuedAt(now);
        claims.setExpiration(exp);

        // Generate the JWT token
        String jwtToken = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, secretKey).compact();

        return jwtToken;
    }
}

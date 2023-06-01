package reddit.restapi.models.security;
import org.springframework.security.core.GrantedAuthority;
public class CustomGrantedAuthorityDTO implements GrantedAuthority{

    private String authority;

    public CustomGrantedAuthorityDTO(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

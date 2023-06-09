package reddit.restapi.models.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reddit.restapi.models.User;

import java.util.Collection;
import java.util.List;
public class UserPrincipalDTO implements UserDetails{
    private Long userId;
    private String email;
    private String password;
    private String username;
    private  List<CustomGrantedAuthorityDTO> authorities;


    public UserPrincipalDTO(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.authorities = List.of(new CustomGrantedAuthorityDTO("USER_AUTHENTICATION"));
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return username;
    }


}

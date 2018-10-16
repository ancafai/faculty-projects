package ro.ubb.downWork.apigateway.security;

/**
 * Created by langchristian96 and CristianCosmin on 20.10.2017.
 */
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.downWork.apigateway.dto.ApiGatewayPersonDto;
import ro.ubb.downWork.apigateway.service.PersonService;

@Service
public class UserDetailsServiceImpl
        implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private PersonService apiGatewayUserService;

    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiGatewayPersonDto user = apiGatewayUserService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true,
                true, true, grantedAuthorities);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

    }
}

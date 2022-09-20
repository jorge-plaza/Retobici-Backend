package es.plaza.retobici.config;

import es.plaza.retobici.user.Rider;
import es.plaza.retobici.user.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Rider rider = riderRepository.findRiderByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No Rider registered with that email"));
        return new User(rider.getEmail(), rider.getPassword(), mapearRoles(null));
    }

    //TODO cambiar por clase ROl
    private Collection<? extends GrantedAuthority> mapearRoles(Set<Object> roles){
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.toString()))
                .toList();
    }
}

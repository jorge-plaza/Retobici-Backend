package es.plaza.retobici.config;

import es.plaza.retobici.user.rider.Rider;
import es.plaza.retobici.user.rider.RiderRepository;
import es.plaza.retobici.user.role.Role;
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

    private final RiderRepository riderRepository;

    @Autowired
    public CustomUserDetailsService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Rider rider = riderRepository.findRiderByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No Rider registered with that email"));
        return new User(rider.getEmail(), rider.getPassword(), mapRoles(rider.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Set<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}

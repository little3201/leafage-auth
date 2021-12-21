package io.leafage.leafage.auth.service;

import io.leafage.leafage.auth.domain.Account;
import io.leafage.leafage.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

/**
 * jdbc 用户服务接口
 *
 * @author liwenqiang 2021-12-21 17:02
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JdbcUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.getByUsername(username);
        return new User(account.getUsername(), account.getPassword(), account.isEnabled(), account.isAccountNonExpired(),
                account.isCredentialsNonExpired(), account.isAccountNonLocked(), Collections.emptyList());
    }
}

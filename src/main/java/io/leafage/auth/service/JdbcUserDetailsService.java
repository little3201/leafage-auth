package io.leafage.auth.service;

import io.leafage.auth.domain.Account;
import io.leafage.auth.domain.AccountRole;
import io.leafage.auth.domain.Role;
import io.leafage.auth.repository.AccountRepository;
import io.leafage.auth.repository.RoleRepository;
import io.leafage.auth.repository.AccountRoleRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * userDetailsService
 *
 * @author liwenqiang 2021-12-21 17:02
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final RoleRepository roleRepository;

    public JdbcUserDetailsService(AccountRepository accountRepository, AccountRoleRepository accountRoleRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.getByUsername(username);
        if (null == account) {
            throw new UsernameNotFoundException("user not found.");
        }
        List<AccountRole> accountRoles = accountRoleRepository.findByAccountId(account.getId());
        if (CollectionUtils.isEmpty(accountRoles)) {
            throw new AuthenticationServiceException("user not authorization.");
        }
        // ????????????????????????
        List<Role> roles = accountRoles.stream().map(accountRole ->
                        roleRepository.findById(accountRole.getRoleId()).orElse(null))
                .filter(Objects::nonNull).collect(Collectors.toList());

        Set<GrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());

        LocalDateTime now = LocalDateTime.now();
        boolean isAccountNonExpired = account.getAccountExpiresAt().isAfter(now);
        boolean isCredentialsNonExpired = account.getCredentialsExpiresAt().isAfter(now);

        return new User(account.getUsername(), account.getPassword(), account.isEnabled(), isAccountNonExpired,
                isCredentialsNonExpired, !account.isAccountLocked(), authorities);
    }
}

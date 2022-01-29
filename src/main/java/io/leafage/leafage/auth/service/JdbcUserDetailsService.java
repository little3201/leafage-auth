package io.leafage.leafage.auth.service;

import io.leafage.leafage.auth.domain.Account;
import io.leafage.leafage.auth.domain.Role;
import io.leafage.leafage.auth.domain.AccountRole;
import io.leafage.leafage.auth.repository.AccountRepository;
import io.leafage.leafage.auth.repository.RoleRepository;
import io.leafage.leafage.auth.repository.UserRoleRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * jdbc 用户服务接口
 *
 * @author liwenqiang 2021-12-21 17:02
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public JdbcUserDetailsService(AccountRepository accountRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.getByUsername(username);
        if (null == account) {
            throw new UsernameNotFoundException("user not found.");
        }
        List<AccountRole> accountRoles = userRoleRepository.findByUserId(account.getId());
        if (CollectionUtils.isEmpty(accountRoles)) {
            throw new AuthenticationServiceException("user not authorization.");
        }
        // 检查角色是否配置
        List<Role> roles = accountRoles.stream().map(accountRole ->
                        roleRepository.findById(accountRole.getRoleId()).orElse(null))
                .filter(Objects::nonNull).collect(Collectors.toList());

        Set<GrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());

        return new User(account.getUsername(), account.getPassword(), account.isEnabled(), account.isAccountNonExpired(),
                account.isCredentialsNonExpired(), account.isAccountNonLocked(), authorities);
    }
}

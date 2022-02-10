package io.leafage.auth.service;

import io.leafage.auth.domain.Account;
import io.leafage.auth.domain.AccountRole;
import io.leafage.auth.domain.Role;
import io.leafage.auth.repository.AccountRepository;
import io.leafage.auth.repository.AccountRoleRepository;
import io.leafage.auth.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;

/**
 * userDetailsService 接口测试
 *
 * @author liwenqiang 2022-02-10 15:59
 **/
@ExtendWith(MockitoExtension.class)
class JdbcUserDetailsServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountRoleRepository accountRoleRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private JdbcUserDetailsService jdbcUserDetailsService;

    @Test
    void loadUserByUsername() {
        Account account = new Account();
        account.setId(1L);
        account.setUsername("test");
        account.setPassword("12323");
        account.setAccountExpiresAt(LocalDateTime.now().plusDays(3L));
        account.setCredentialsExpiresAt(LocalDateTime.now().plusHours(2L));
        given(this.accountRepository.getByUsername(Mockito.anyString())).willReturn(account);

        AccountRole accountRole = new AccountRole();
        accountRole.setAccountId(account.getId());
        accountRole.setRoleId(2L);
        given(this.accountRoleRepository.findByAccountId(Mockito.anyLong())).willReturn(List.of(accountRole));

        Role role = new Role();
        role.setId(accountRole.getRoleId());
        role.setCode("121212");
        given(this.roleRepository.findById(Mockito.anyLong())).willReturn(Optional.of(role));

        UserDetails userDetails = jdbcUserDetailsService.loadUserByUsername("test");
        Assertions.assertNotNull(userDetails);
    }
}
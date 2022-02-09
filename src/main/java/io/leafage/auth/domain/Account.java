package io.leafage.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Model class for Account
 *
 * @author liwenqiang 2021-12-21 17:44
 */
@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    /**
     * 账户
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号有效期
     */
    @Column(name = "account_expires_at")
    private LocalDateTime accountExpiresAt;
    /**
     * 是否锁定
     */
    @Column(name = "is_account_locked")
    private boolean accountLocked;
    /**
     * 密码有效期
     */
    @Column(name = "credentials_expires_at")
    private LocalDateTime credentialsExpiresAt;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getAccountExpiresAt() {
        return accountExpiresAt;
    }

    public void setAccountExpiresAt(LocalDateTime accountExpiresAt) {
        this.accountExpiresAt = accountExpiresAt;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public LocalDateTime getCredentialsExpiresAt() {
        return credentialsExpiresAt;
    }

    public void setCredentialsExpiresAt(LocalDateTime credentialsExpiresAt) {
        this.credentialsExpiresAt = credentialsExpiresAt;
    }

}

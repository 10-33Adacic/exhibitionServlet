package com.exhibition.model.entity;

import java.util.Objects;

public class User {

    private Long id;

    private String username;

    private String password;

    private Long accountMoney;

    private boolean active;

    private Role role;

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            User.this.id = id;
            return this;
        }

        public Builder username(String username) {
            User.this.username = username;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder accountMoney(Long accountMoney) {
            User.this.accountMoney = accountMoney;
            return this;
        }

        public Builder active(Boolean active) {
            User.this.active = active;
            return this;
        }

        public Builder role(Role role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Long accountMoney) {
        this.accountMoney = accountMoney;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountMoney=" + accountMoney +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}

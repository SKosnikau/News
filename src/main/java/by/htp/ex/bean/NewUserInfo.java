package by.htp.ex.bean;

import com.google.common.base.Objects;

public class NewUserInfo {
    private String userName;
    private String userSurname;
    private String login;
    private String password;
    private String email;
    private String role;
    private Integer id;

    public NewUserInfo() {

    }

    public NewUserInfo(String userName, String userSurname, String login, String email, String role) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.email = email;
        this.role = role;
    }

    public NewUserInfo(String userName, String userSurname, String login, String password, String email, String role) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //TODO: Ask if that's a good idea to have "setPassword" in Bean?
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NewUserInfo that = (NewUserInfo) obj;
        return Objects.equal(userName, that.userName) && Objects.equal(userSurname, that.userSurname) && Objects.equal(login, that.login)
                && Objects.equal(email, that.email) && Objects.equal(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName, userSurname, login, password, email, role);
    }

    @Override
    public String toString() {
        return "NewUserInfo{" +
                "username='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
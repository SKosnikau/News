package by.htp.ex.bean;

public class NewUserInfo {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String birthday;

    public NewUserInfo() {
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password
                + ", birthday=" + birthday + "]";
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

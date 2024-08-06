package HistoryAppGradleSecurity.model.binding;

import HistoryAppGradleSecurity.model.validators.PropMatch;
import jakarta.validation.constraints.*;
@PropMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserSubscribeBindingModel {
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 symbols!")
    @NotNull(message = "Username must not be empty!")
    private String username;
    @NotEmpty
    @Size(min = 2,max = 20,message = "Full name must be between 2 and 20 symbols!")
    private String fullName;
    @Email(message = "Email must be valid format!")
    @NotBlank(message = "Email must not be empty!")
    private String email;
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 symbols!")
    @NotNull(message = "Password must not be empty!")
    private String password;
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 symbols!")
    @NotNull(message = "Password must not be empty!")
    private String confirmPassword;
    @Min(value = 18,message = "Over 18 years! ")
    @Max(90)
    @NotNull
    @Positive
    private int age;

    public UserSubscribeBindingModel() {
    }


    public int getAge() {
        return age;
    }

    public UserSubscribeBindingModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserSubscribeBindingModel{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : "[N/A]") + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", age=" + age +
                '}';
    }
}

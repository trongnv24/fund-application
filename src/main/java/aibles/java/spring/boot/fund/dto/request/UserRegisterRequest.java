package aibles.java.spring.boot.fund.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank(message = "userName không được rỗng")
    private String userName;

    @NotBlank(message = "password không được rỗng")
    @Size(min = 6, message = "password phải có ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "email không được rỗng")
    @Email(message = "email không hợp lệ")
    private String email;

    @NotBlank(message = "phone không được rỗng")
    private String phone;

    @NotBlank(message = "fullName không được rỗng")
    private String fullName;

    @NotBlank(message = "address không được rỗng")
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

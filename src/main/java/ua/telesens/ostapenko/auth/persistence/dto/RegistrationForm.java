package ua.telesens.ostapenko.auth.persistence.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import ua.telesens.ostapenko.auth.annotation.UniqueUserEmail;
import ua.telesens.ostapenko.auth.annotation.UniqueUserName;
import ua.telesens.ostapenko.auth.persistence.model.User;

/**
 * @author root
 * @since 26.01.16
 */
@Data
public class RegistrationForm {

    @Length.List({
            @Length(min = User.MIN_LENGTH_NAME, message = "{UserDto.error.name.size.min}"),
            @Length(max = User.MAX_LENGTH_NAME, message = "{UserDto.error.name.size.max}")
    })
    @UniqueUserName(message = "{UserDto.error.name.size.unique}")
    private String name;

    @NotEmpty(message = "{UserDto.error.email}")
    @Email(message = "{UserDto.error.email}")
    @UniqueUserEmail(message = "{UserDto.error.email.unique}")
    private String email;

    @Length(min = User.MIN_LENGTH_PASSWORD, message = "{UserDto.error.password.size.min}")
    private String password;

}

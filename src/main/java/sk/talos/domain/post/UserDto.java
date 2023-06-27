package sk.talos.domain.post;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class UserDto extends BaseEntityDto {

    @NotBlank(message = "name_field_is_mandatory")
    private String name;

    @NotBlank(message = "user_name_field_is_mandatory")
    private String username;

    @NotBlank(message = "email_field_is_mandatory")
    @Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b",
            message = "email_address_has_invalid_format ")
    private String email;

    private String phone;

    private String website;

    private AddressDto address;

    private CompanyDto company;


}

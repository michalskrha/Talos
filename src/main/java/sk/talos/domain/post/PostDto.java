package sk.talos.domain.post;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class PostDto extends BaseEntityDto {

    @NotEmpty(message = "Post title is required.")
    @NotNull(message = "Post title is mandatory field.")
    private String title;

    private String body;

    @NotNull(message = "User ID is mandatory field.")
    private Long userId;

}

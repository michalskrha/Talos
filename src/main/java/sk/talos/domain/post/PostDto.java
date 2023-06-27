package sk.talos.domain.post;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class PostDto extends BaseEntityDto {

    private String title;

    private String body;

    @NotNull
    private Long userId;

}

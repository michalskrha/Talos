package sk.talos.domain.post;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class CompanyDto extends BaseEntityDto {

    private String name;

    private String catchPhrase;

    private String bs;

}

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
public class AddressDto {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private GeoDto geo;


}

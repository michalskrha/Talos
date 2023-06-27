package sk.talos.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Embeddable
public class Address {

    private String street;

    private String suite;

    private String city;

    @Column(name = "zip_code")
    private String zipcode;

    @Column(name = "geo_lat")
    private String geoLat;

    @Column(name = "geo_lng")
    private String geoLng;


}

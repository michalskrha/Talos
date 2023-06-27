package sk.talos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import sk.talos.model.common.BaseEntity;
import sk.talos.model.embedded.Address;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String name;

    @Column(name = "user_name")
    private String username;

    @Column(unique = true)
    private String email;

    private String phone;

    private String website;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}

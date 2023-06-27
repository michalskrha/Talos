package sk.talos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import sk.talos.model.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "catch_phrase")
    private String catchPhrase;

    @Column(name = "bs")
    private String bs;

}

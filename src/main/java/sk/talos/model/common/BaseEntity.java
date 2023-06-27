package sk.talos.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@FieldNameConstants
@MappedSuperclass
public class BaseEntity extends Auditable<String> implements Serializable {

    public static final String ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

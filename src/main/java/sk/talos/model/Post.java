package sk.talos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import sk.talos.model.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

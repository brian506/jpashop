package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("top")
@Getter
@Setter
public class Top extends Item{

    private String outer;
    private String sweats;

}

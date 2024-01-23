package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("shoes")
@Getter @Setter
public class Shoes extends Item{

    private String boots;
    private String sneakers;
    private String sandals;
}

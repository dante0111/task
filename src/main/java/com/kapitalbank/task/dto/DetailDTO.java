package com.kapitalbank.task.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString@Entity
@NamedNativeQuery(
        name = "DetailDTO",
        query =
                "select d.id as id, d.quantity as quantity from product p inner join detail d on p.id = d.pr_id where p.id = ?1",
        resultSetMapping = "details"
)
@SqlResultSetMapping(
        name = "details",
        classes = @ConstructorResult(
                targetClass = DetailDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "quantity", type = Short.class)
                }
        )
)
public class DetailDTO {

    @Id
    private int id;
    private short quantity;
}

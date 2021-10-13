package com.kapitalbank.task.dto;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@NamedNativeQuery(
        name = "HighDemandProductsDTO",
        query =
                "select p.id as id, sum(quantity) as quantity " +
                        "from product p " +
                                 "inner join detail d on p.id = d.pr_id "+
                        "group by p.id "+
                        "having sum(quantity) > 10",
        resultSetMapping = "highDemand"
)
@SqlResultSetMapping(
        name = "highDemand",
        classes = @ConstructorResult(
                targetClass = HighDemandProductsDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "quantity", type = Short.class)
                }
        )
)
public class HighDemandProductsDTO {

    @Id
    private int id;
    private Short quantity;
}

package com.kapitalbank.task.dto;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@NamedNativeQuery(
        name = "BulkProductsDTO",
        query =
                "select p.id as id, price" +
                        "            from product p" +
                        "            inner join detail d on p.id = d.pr_id" +
                        "            where quantity > 8",
        resultSetMapping = "bulkProducts"
)
@SqlResultSetMapping(
        name = "bulkProducts",
        classes = @ConstructorResult(
                targetClass = BulkProductsDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "price", type = BigDecimal.class)
                }
        )
)
public class BulkProductsDTO {

    @Id
    private int id;
    private BigDecimal price;
}

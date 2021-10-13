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
        name = "NumberOfProductsInYearDTO",
        query =
                "select c.country as country, count(*) as number" +
                        "            from cust_order co " +
                        "                      inner join customer c on co.cust_id = c.id " +
                        "             where extract(year from co.date) = '2016' " +
                        "             group by c.country",
        resultSetMapping = "numberProducts"
)
@SqlResultSetMapping(
        name = "numberProducts",
        classes = @ConstructorResult(
                targetClass = NumberOfProductsInYearDTO.class,
                columns = {
                        @ColumnResult(name = "country", type = String.class),
                        @ColumnResult(name = "number", type = Long.class)
                }
        )
)
public class NumberOfProductsInYearDTO {

    @Id
    private String country;
    private Long number;
}

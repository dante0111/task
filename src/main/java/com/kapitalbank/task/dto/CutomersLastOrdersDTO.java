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
        name = "CutomersLastOrdersDTO",
        query =
                "select c.id as id, c.name as name, max(co.date) as co_date\n" +
                        "from customer c\n" +
                        "         inner join cust_order co on c.id = co.cust_id\n" +
                        "group by c.id, c.name",
        resultSetMapping = "lastOrders"
)
@SqlResultSetMapping(
        name = "lastOrders",
        classes = @ConstructorResult(
                targetClass = CutomersLastOrdersDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "co_date", type = String.class)
                }
        )
)
public class CutomersLastOrdersDTO {

    @Id
    private int id;
    private String name;
    private String o_date;
}

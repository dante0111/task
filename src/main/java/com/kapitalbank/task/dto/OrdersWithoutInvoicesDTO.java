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
        name = "OrdersWithoutInvoicesDTO",
        query =
                "select co.id as id, co.date as co_date, p.price * quantity as total_price, sum(quantity) as quantity, price as unit_price\n" +
                        "from invoice i\n" +
                        "         right join cust_order co on i.ord_id = co.id\n" +
                        "         inner join detail d on co.id = d.ord_id\n" +
                        "         inner join product p on d.pr_id = p.id\n" +
                        "where i.ord_id is null\n" +
                        "group by co.id, co.date, p.price * quantity, price",
        resultSetMapping = "wihtoutInvoices"
)
@SqlResultSetMapping(
        name = "wihtoutInvoices",
        classes = @ConstructorResult(
                targetClass = OrdersWithoutInvoicesDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "co_date", type = String.class),
                        @ColumnResult(name = "total_price", type = Integer.class),
                        @ColumnResult(name = "quantity", type = Short.class),
                        @ColumnResult(name = "unit_price", type = BigDecimal.class),
                }
        )
)
public class OrdersWithoutInvoicesDTO {

    @Id
    private int id;
    private String co_date;
    private int total_price;
    private short quantity;
    private BigDecimal unit_price;
}
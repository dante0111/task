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
        name = "InvoiceIssuedBeforeOrder",
        query =
                "select i.id as id, issued as issued, o.id as o_id, o.date as o_date\n" +
                        "from invoice i\n" +
                        "         inner join cust_order o on i.ord_id = o.id\n" +
                        "where i.issued < o.date",
        resultSetMapping = "BeforeOrder"
)
@SqlResultSetMapping(
        name = "BeforeOrder",
        classes = @ConstructorResult(
                targetClass = InvoiceIssuedBeforeOrderDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "issued", type = String.class),
                        @ColumnResult(name = "o_id", type = Integer.class),
                        @ColumnResult(name = "o_date", type = String.class)
                }
        )
)
public class InvoiceIssuedBeforeOrderDTO {

    @Id
    private int id;
    private String issued;
    private int o_id;
    private String o_date;
}

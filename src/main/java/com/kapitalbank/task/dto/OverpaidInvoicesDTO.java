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
        name = "OverpaidInvoicesDTO",
        query =
                "select i.id as id, count(*) * i.amount as amount \n" +
                        "from invoice i \n" +
                        "         inner join payment p on i.id = p.inv_id \n " +
                        "group by i.id, i.amount\n " +
                        "having count(p.inv_id) > 1",
        resultSetMapping = "overpaid"
)
@SqlResultSetMapping(
        name = "overpaid",
        classes = @ConstructorResult(
                targetClass = OverpaidInvoicesDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "amount", type = BigDecimal.class)
                }
        )
)
public class OverpaidInvoicesDTO {

    @Id
    private int id;
    private BigDecimal amount;
}
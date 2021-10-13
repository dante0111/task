package com.kapitalbank.task.dto;

import com.kapitalbank.task.entity.Detail;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter
@ToString
public class OrderDetailsAndProductName {

    private Detail detail;
    private String pr_name;
}

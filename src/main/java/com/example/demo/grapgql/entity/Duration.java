

package com.example.demo.grapgql.entity;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Duration {
    private String start;
    private String end;
    private Step step;
}

package com.texoit.exercises.awardsapi.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducerInterval {
    private String producer;
    private Long interval;
    private Long previousWin;
    private Long followingWin;
}
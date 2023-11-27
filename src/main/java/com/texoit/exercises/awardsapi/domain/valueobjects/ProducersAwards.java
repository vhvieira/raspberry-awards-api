package com.texoit.exercises.awardsapi.domain.valueobjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProducersAwards {
    private List<ProducerInterval> min = new ArrayList<>();
    private List<ProducerInterval> max = new ArrayList<>();
}

package com.texoit.exercises.awardsapi.response;

import lombok.Data;
import java.util.List;

@Data
public class ProducersAwards {
    private List<ProducerInterval> min;
    private List<ProducerInterval> max;
}

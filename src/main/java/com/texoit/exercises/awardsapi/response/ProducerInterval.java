package com.texoit.exercises.awardsapi.response;

import lombok.Data;

@Data
public class ProducerInterval {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
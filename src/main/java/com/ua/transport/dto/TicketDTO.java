package com.ua.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private Long userId;
    private Long planeId;

}

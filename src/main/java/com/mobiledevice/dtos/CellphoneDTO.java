package com.mobiledevice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CellphoneDTO {

    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
}

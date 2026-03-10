package com.broberson4.futurestracker.model;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String symbol;

    private String side;

    private String status;

    private Integer filled;

    @Column(name = "total_qty")
    private Integer totalQty;

    private BigDecimal price;

    @Column(name = "avg_price")
    private BigDecimal avgPrice;

    @Column(name = "time_in_force")
    private String timeInForce;

    @Column(name = "placed_time")
    private LocalDateTime placedTime;

    @Column(name = "filled_time")
    private LocalDateTime filledTime;

    // getters and setters done with Lombok
}
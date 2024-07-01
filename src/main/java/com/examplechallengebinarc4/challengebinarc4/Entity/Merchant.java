package com.examplechallengebinarc4.challengebinarc4.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "merchant")
public class Merchant extends AbstrackFood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "merchant_name", length = 255)
    private String merchant_name;

    @Column(name = "merchant_location", length = 255)
    private String merchant_location;

    @Column(name = "open", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

}

package com.example.gift.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "charity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Charity {

    @Id
    @GeneratedValue(generator = "charity_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "charity_gen", sequenceName = "charity_seq", allocationSize = 1)
    private Long id;

    private String giftName;

    private boolean isBlocked;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Size(max = 10000)
    private String description;

    private String image;

    private Boolean isBlock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}

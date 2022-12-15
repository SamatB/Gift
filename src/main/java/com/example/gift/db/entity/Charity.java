package com.example.gift.db.entity;

import com.example.gift.enums.CharityStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate createdAt;

    @OneToOne(mappedBy = "charity", cascade = CascadeType.ALL)
    private Booking booking;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @Enumerated(EnumType.STRING)
    private CharityStatus charityStatus;

    @OneToMany(mappedBy = "charity",cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL)
    private List <Complaints> complaints;

    public void addNotification(Notification notification){
        notifications.add(notification);
    }
}

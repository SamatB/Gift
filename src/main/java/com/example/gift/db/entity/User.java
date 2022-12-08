package com.example.gift.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1, initialValue = 3)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String image;

    @Email
    private String email;

    @Column(name = "city")
    private String city;

    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 10000)
    private String hobbies;

    @Size(max = 10000)
    @Column(name = "important_to_know")
    private String importantToKnow;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isSubscribeToNewsletter = false;

    private Boolean isBlock;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ClothingSize> clothingSize;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ShoeSize> shoeSize;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<WishList> wishLists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Charity> charities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Holiday> holidays;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Complaints> complaints;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "friends")
    @JsonIgnore
    private List<User> friends = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> requestToFriends = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "receivers")
    private List<Notification> notificationList = new ArrayList<>();

    public void deleteNotification(Notification notification) {
        this.notifications.remove(notification);
    }

    public void sendRequestToFriend(User user) {
        if (CollectionUtils.isEmpty(requestToFriends)) {
            requestToFriends = new ArrayList<>();
        }
        requestToFriends.add(user);
    }

    public void addUserToFriend(User user) {
        if (CollectionUtils.isEmpty(friends)) {
            friends = new ArrayList<>();
        }
        friends.add(user);
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

}

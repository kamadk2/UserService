package com.kamad.user.service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_user")
public class User {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name ="NAME" , length = 50)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}

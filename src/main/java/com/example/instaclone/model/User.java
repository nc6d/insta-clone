package com.example.instaclone.model;

import com.example.instaclone.model.notification.Notification;
import com.example.instaclone.model.picture.ProfilePicture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_registered")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    private String mainAvatar;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "follower")
    private Set<Subscription> followers;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "followed")
    private Set<Subscription> following;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
    private Set<Notification> notifications;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<ProfilePicture> userProfilePictures;

    public void addAvatar(ProfilePicture profilePicture) {
        userProfilePictures.add(profilePicture);
    }


}

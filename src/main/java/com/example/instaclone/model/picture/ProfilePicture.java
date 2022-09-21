package com.example.instaclone.model.picture;

import com.example.instaclone.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@PrimaryKeyJoinColumn
@Table(name = "user_picture")
public class ProfilePicture extends Picture {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    protected User owner;

    public ProfilePicture(String uri, User owner) {
        super(uri);
        this.owner = owner;

    }

}

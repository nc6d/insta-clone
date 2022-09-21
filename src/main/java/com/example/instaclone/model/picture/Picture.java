package com.example.instaclone.model.picture;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    protected Long id;

    protected String uri;

    public Picture(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}

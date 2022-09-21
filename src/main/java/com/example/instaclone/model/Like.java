package com.example.instaclone.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "post_likes")
public class Like {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

    private LocalDateTime publicationDate;

    @PrePersist
    public void preCreate() {
        this.publicationDate = LocalDateTime.now();
    }

}

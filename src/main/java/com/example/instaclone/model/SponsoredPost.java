package com.example.instaclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sponsored_post")
public class SponsoredPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsored_post_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sponsor_id")
    private User sponsor;

}

package com.example.instaclone.model.picture;

import com.example.instaclone.model.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Setter
//@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn
@Data
@Table(name = "post_picture")
public class PostPicture extends Picture {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    protected Post post;

//    public String getUri(){
//        return super(uri);
//    }

    public PostPicture(String uri, Post post) {
        super(uri);
        this.post = post;

    }
}

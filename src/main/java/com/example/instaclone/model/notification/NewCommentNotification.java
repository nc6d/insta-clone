package com.example.instaclone.model.notification;

import com.example.instaclone.model.Post;
import com.example.instaclone.model.User;
import lombok.Data;

@Data
public class NewCommentNotification implements Notification {

    private Post post;
    private User postAuthor;

    public NewCommentNotification(Post post, User postAuthor) {
        this.post = post;
        this.postAuthor = postAuthor;
        recipients.add(post.getAuthor());
    }

    @Override
    public String sendNotification() {
        return String.format("Post (id: %d) of %s was commented by %s",
                post.getId(), post.getAuthor(), postAuthor.getUsername());
    }
}

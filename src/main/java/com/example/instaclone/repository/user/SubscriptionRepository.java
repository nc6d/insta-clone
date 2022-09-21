package com.example.instaclone.repository.user;

import com.example.instaclone.model.Subscription;
import com.example.instaclone.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    void deleteByFollowerAndFollowed(User follower, User followed);

    List<Subscription> findAllFollowedByFollowerId(Long followerId);

    List<Subscription> findAllFollowersByFollowedId(Long followedId);

    Subscription getSubscriptionByFollowerAndFollowed(User follower, User followed);

    Boolean existsByFollowerAndFollowed(User follower, User followed);
}

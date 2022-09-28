package com.example.instaclone.service.impl;

import com.example.instaclone.exception.entity.Status404PostNotFoundException;
import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.model.Post;
import com.example.instaclone.model.SponsoredPost;
import com.example.instaclone.model.Subscription;
import com.example.instaclone.model.picture.PostPicture;
import com.example.instaclone.repository.post.PostRepository;
import com.example.instaclone.repository.post.SponsoredPostRepository;
import com.example.instaclone.service.PostService;
import com.example.instaclone.service.UserService;
import com.example.instaclone.service.facade.IAuthFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageStorageService imageStorageService;
    private final IAuthFacade authFacade;
    private final UserService userService;
    private final SponsoredPostRepository sponsoredPostRepository;

    private List<PostPicture> parsePicturesFromEntryFilesAndSave(Post post, @NonNull MultipartFile[] files) {

        List<PostPicture> postPictures = new ArrayList<>();
        Arrays.stream(files)
                .forEach(file -> {
                    PostPicture postPicture = new PostPicture(
                            imageStorageService.upload(file, post.getAuthor()).getUri(), post);

                    postPictures.add(postPicture);
                    imageStorageService.savePostPicture(postPicture);

                });

        return postPictures;
    }


    @Override
    public Post createPost(MultipartFile[] files, String caption) throws Status404UserNotFoundException {

        Post post = postRepository.save(Post.builder()
                .author(userService.findByUsername(authFacade.getUsername()))
                .caption(caption)
                .build());

        post.setImageUris(parsePicturesFromEntryFilesAndSave(post, files).stream()
                .map(PostPicture::getUri)
                .toList());
//        post.setImages(parsePicturesFromEntryFilesAndSave(post, files));

        return post;
    }

    @Override
    public SponsoredPost createPost(MultipartFile[] files, String caption, Long sponsorId)
            throws Status404UserNotFoundException {

        return sponsoredPostRepository.save(
                SponsoredPost.builder()
                        .post(createPost(files, caption))
                        .sponsor(userService.findById(sponsorId))
                        .build()
        );

    }

    @Override
    public Post findPostById(Long id) throws Status404PostNotFoundException {
        return postRepository.findById(id).orElseThrow(() ->
                new Status404PostNotFoundException("Post with " + id + " id not found"));
    }

    @Override
    public List<Post> findAllPostsByUserId(Long id) {
        return postRepository.findAllByAuthorId(id);
    }

    public List<Post> getAllPostsBySubscriptions() throws Status404UserNotFoundException {
        return userService.findByUsername(authFacade.getUsername()).getFollowers()
                .stream()
                .map(Subscription::getFollowed)
                .flatMap(user -> user.getPosts()
                        .stream()
                        .sorted(Comparator.comparing(Post::getPublicationDate).reversed()))
                .toList();
    }

}

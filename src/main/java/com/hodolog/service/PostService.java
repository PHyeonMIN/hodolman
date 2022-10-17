package com.hodolog.service;

import com.hodolog.domain.Post;
import com.hodolog.repository.PostRepository;
import com.hodolog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        // postCreate -> Entity

        Post post = Post.builder()
                        .title(postCreate.getTitle())
                        .content(postCreate.getContent())
                        .build();
        postRepository.save(post);
//        return postRepository.save(post);
//        return post.getId();
    }

    public Post get(Long id) {
        // optional같은 데이터는 바로 꺼내주는 것이 좋다.
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        return post;
    }
}

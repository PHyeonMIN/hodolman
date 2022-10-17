package com.hodolog.service;

import com.hodolog.domain.Post;
import com.hodolog.repository.PostRepository;
import com.hodolog.request.PostCreate;
import com.hodolog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponse get(Long id) {
        // optional같은 데이터는 바로 꺼내주는 것이 좋다.
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        PostResponse response = PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return response;
    }

    public List<PostResponse> getList() {
       return postRepository.findAll().stream()
//               .map(post -> new PostResponse(post)) // 밑과 같음
               .map(PostResponse::new)
               .collect(Collectors.toList());
    }
}

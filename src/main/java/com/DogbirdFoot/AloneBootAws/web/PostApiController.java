package com.DogbirdFoot.AloneBootAws.web;

import com.DogbirdFoot.AloneBootAws.service.posts.PostService;
import com.DogbirdFoot.AloneBootAws.web.dto.PostsResponseDto;
import com.DogbirdFoot.AloneBootAws.web.dto.PostsSaveRequestDto;
import com.DogbirdFoot.AloneBootAws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }

}

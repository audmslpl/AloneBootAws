package com.DogbirdFoot.AloneBootAws.web;

import com.DogbirdFoot.AloneBootAws.config.auth.LoginUser;
import com.DogbirdFoot.AloneBootAws.config.auth.dto.SessionUser;
import com.DogbirdFoot.AloneBootAws.service.posts.PostService;
import com.DogbirdFoot.AloneBootAws.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/")
    public String index(Model model ,@LoginUser SessionUser user){
        model.addAttribute("posts",postService.findAllDesc());
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}

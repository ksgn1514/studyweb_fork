package com.weavus.studyweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weavus.studyweb.entity.Post;
import com.weavus.studyweb.entity.PostCategory;
import com.weavus.studyweb.service.CommunityService;


@Controller
@RequestMapping("/community")
public class CommunityController {
    
    @Autowired
    private CommunityService communityService;

    @GetMapping("") //continue 파라미터 포함 대응
    public String startCommnity(@RequestParam(required = false) String cont, Model model) {
       
        // 카테고리 조회
        List<PostCategory> categoryList = communityService.findAllPostCategory();
        //첫번째는 공지사항이므로 제거
        categoryList.remove(0);
        model.addAttribute("categoryList", categoryList);

         // 게시글 조회
        List<Post> postList = communityService.findAllPost();
        model.addAttribute("postList", postList);

        return "community/community";
    }

    @GetMapping("/{category}")
    public String categoryCommunity(@PathVariable String category, Model model) {
        
        // 카테고리 조회
        List<PostCategory> categoryList = communityService.findAllPostCategory();
        //첫번째는 공지사항이므로 제거
        categoryList.remove(0);
        model.addAttribute("categoryList", categoryList);

        // 카테고리별 게시글 조회 
        List<Post> postList = communityService.findPostByCategory(category);
        model.addAttribute("postList", postList);

        return "community/community";
    }

    //글작성 페이지
    @GetMapping("/post")
    public String postCommunity() {
        return "community/post";
    }

    @PostMapping("/createPost")
    public String postCommunity(String title, String content) {
        // 게시글 등록 로직 작성
        return "redirect:/community";
    }


}

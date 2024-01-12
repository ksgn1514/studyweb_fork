package com.weavus.studyweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.weavus.studyweb.auth.PrincipalDetails;
import com.weavus.studyweb.dto.PostCreateDTO;
import com.weavus.studyweb.dto.PostDetailDTO;
import com.weavus.studyweb.entity.Post;
import com.weavus.studyweb.entity.PostCategory;
import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.service.CommunityService;
import com.weavus.studyweb.utility.CommonUtility;


@Controller
@RequestMapping("/community")
public class CommunityController {
    
    @Autowired
    private CommunityService communityService;
     @Autowired
    private CommonUtility util;

    @GetMapping("") //continue 파라미터 포함 대응
    public String startCommnity(@RequestParam(required = false) String cont, Model model) {
       
        // 카테고리 조회
        List<PostCategory> categoryList = communityService.findAllPostCategory();
        //첫번째는 공지사항이므로 제거
        categoryList.remove(0);
        model.addAttribute("categoryList", categoryList);

         // 게시글 조회
        List<Post> postList = communityService.findAllPost();
        List<PostDetailDTO> postDetailList = util.toPostDetailDto(postList);

        model.addAttribute("postList", postDetailList);

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
        List<PostDetailDTO> postDetailList = util.toPostDetailDto(postList);
        model.addAttribute("postList", postDetailList);

        return "community/community";
    }

    //글작성 페이지
    @GetMapping("/post")
    public String postCommunity(Model model) {
         // 카테고리 조회
        List<PostCategory> categoryList = communityService.findAllPostCategory();

        model.addAttribute("categoryList", categoryList);

        return "community/post";
    }

    //글작성
    @PostMapping("/createPost")
    public String createPost(@Validated @ModelAttribute PostCreateDTO postCreateDTO, MultipartFile file)  throws IllegalStateException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User loginUser = principalDetails.getUser();

        Post post = new Post();
        post.setAuthor(loginUser);
        post.setCategory(communityService.findPostCategoryByUrl(postCreateDTO.getCategoryUrl()));
        post.setContents(postCreateDTO.getContents());
        post.setTitle(postCreateDTO.getTitle());
        post.setViewCount(0);

        if (file.getOriginalFilename() != "") {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\img\\post";

            UUID uuid = UUID.randomUUID();
            String temp = file.getOriginalFilename();
            String fileName =  "post_" + uuid + temp.substring(file.getOriginalFilename().lastIndexOf("."));

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            post.setFilepath("/assets/img/post/" + fileName);
        }

        communityService.createPost(post);

        return "redirect:/community";
    }


}

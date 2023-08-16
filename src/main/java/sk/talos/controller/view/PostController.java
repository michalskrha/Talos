package sk.talos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sk.talos.domain.post.PostDto;
import sk.talos.mapper.PostMapper;
import sk.talos.model.Post;
import sk.talos.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/")
    public ModelAndView index()
    {
        List<Post> userPosts = postService.getPosts();
        List<PostDto> postDtoList = PostMapper.INSTANCE.postsToPostDtoList(userPosts);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("postList", postDtoList);

        return modelAndView;
    }

    @GetMapping(value="/new")
    public ModelAndView newPost()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-post");

        Post post = new Post();
        modelAndView.addObject("post", post);

        return modelAndView;
    }


    @PostMapping(value="/create")
    public String createPost(@Valid PostDto postDto,
                             BindingResult result,
                             Model model) {

        model.addAttribute("post", postDto);

        if (result.hasErrors()) {
            handleValidationErrors(result, model);
            return "new-post";
        }

        try {
            postService.createPost(postDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "new-post";
        }

        return "redirect:/";
    }


    @GetMapping(value="/update/{postId}")
    public ModelAndView updatePosts(@PathVariable Long postId) {
        Optional<Post> post = postService.getPost(postId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("post", post.map(PostMapper.INSTANCE::postToPostDto).orElseGet(PostDto::new));

        return modelAndView;
    }


    @PostMapping(value="/save")
    public String savePost(@Valid PostDto postDto,
                           BindingResult result,
                           Model model) {
        model.addAttribute("post", postDto);

        if (result.hasErrors()) {
            handleValidationErrors(result, model);
            return "update";
        }

        postService.updatePost(postDto.getId(), postDto);

        return "redirect:/";
    }


    @GetMapping(value="/delete/{postId}")
    public String deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);

        return "redirect:/";
    }



    @RequestMapping(value="/users/{userId}/posts")
    public ModelAndView getPosts(@PathVariable Long userId)
    {
        List<Post> userPosts = postService.getUserPosts(userId);
        List<PostDto> postDtoList = PostMapper.INSTANCE.postsToPostDtoList(userPosts);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("postList", postDtoList);
        return modelAndView;
    }


    private void handleValidationErrors(BindingResult result, Model model) {
        if (result.getFieldError("userId") != null) {
            model.addAttribute("userIdError", result.getFieldError("userId").getDefaultMessage());
        }
        if (result.getFieldError("title") != null) {
            model.addAttribute("titleError", result.getFieldError("title").getDefaultMessage());
        }
    }


}

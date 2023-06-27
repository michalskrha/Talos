package sk.talos.controller.view;

import io.swagger.models.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sk.talos.domain.http.response.CommonResponseData;
import sk.talos.domain.http.response.ResponseErrorData;
import sk.talos.domain.post.PostDto;
import sk.talos.mapper.PostMapper;
import sk.talos.model.Post;
import sk.talos.service.PostService;

import java.util.List;

@Controller
@RequestMapping(path = "")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public ModelAndView index()
    {
        List<Post> userPosts = postService.getPosts();
        List<PostDto> postDtoList = PostMapper.INSTANCE.postsToPostDtoList(userPosts);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("postList", postDtoList);

        return modelAndView;
    }

    @RequestMapping("/new")
    public ModelAndView newPost()
    {
//        List<Post> userPosts = postService.getPosts();
//        List<PostDto> postDtoList = PostMapper.INSTANCE.postsToPostDtoList(userPosts);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new");

        Post post = new Post();
        modelAndView.addObject("post", post);

        return modelAndView;
    }


    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String createPost(@ModelAttribute PostDto postDto,
                             BindingResult errors,
                             Model model)
    {

        postService.createPost(postDto);

        return "redirect:/";
    }


    @RequestMapping(value="/delete/{postId}")
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
//        modelAndView.setViewName("user-data");
        modelAndView.addObject("postList", postDtoList);
        return modelAndView;
    }



}

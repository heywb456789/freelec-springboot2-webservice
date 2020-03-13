package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    //httpSession 추가
    private final HttpSession httpSession;

    /**
     * 머스테치 스타터 덕분에 컨트롤러와 문자열을 반환할떄 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
     * 앞의 경로 /src/main/resources/templates 와
     * 뒤의 확장자 .mustache가 붙어 반환되고
     * ViewResolver가 처리하게 된다.
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장한다. 여기서는 postsService.findAllDesc() 가져온 결과를 index.mustache에 넘긴다.
        model.addAttribute("posts",postsService.findAllDesc());

        //google Oauth2 로직 시작
        //@LoginUser를 통해 생략이 된다.
        //기존에 (SessionUser)httpSession.getAttribute("user") 통해 가져오던 값이 어느 컨트롤러에서 @LoginUser로 값을 가져올수 있게 되었다.
        //SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";

    }




}

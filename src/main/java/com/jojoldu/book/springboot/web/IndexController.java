package com.jojoldu.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    /**
     * 머스테치 스타터 덕분에 컨트롤러와 문자열을 반환할떄 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
     * 앞의 경로 /src/main/resources/templates 와
     * 뒤의 확장자 .mustache가 붙어 반환되고
     * ViewResolver가 처리하게 된다.
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}

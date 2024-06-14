package kr.ac.seoil.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kr.ac.seoil.vo.SampleVO;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/sample/*")
public class SampleController {

    @RequestMapping("/board/list.do")
    public String list() {
        return "list";
    }

    @RequestMapping("/board/view.do")
    public String view() {
        return "view";
    }

    @RequestMapping("/index.do")
    public String index() {
        return "index";
    }

    @GetMapping("/ex01")
    public String ex01(SampleVO vo) {
        log.info(vo);
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("userName") String name) {
        log.info(name);
        return "ex01";
    }

    @GetMapping("/ex04")
    public String ex04(Model model, SampleVO vo, int page) {
        log.info("vo:" + vo);
        log.info("page:" + page);

        model.addAttribute("page", page);
        return "sample/ex04";
    }

    @GetMapping("/ex04_1")
    public String ex04(SampleVO vo, @ModelAttribute("page") int page) {
        log.info("vo:" + vo);
        log.info("page:" + page);

        return "sample/ex04";
    }

    @GetMapping("/ex05")
    public String ex05() {
        return "redirect:/sample/index.do";
    }

    @GetMapping("/ex06")
    public @ResponseBody SampleVO ex06() {
        SampleVO vo = new SampleVO();
        vo.setUserName("홍길동");
        vo.setUserAge(30);

        return vo;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        String msg = "{\"name\":\"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }
}

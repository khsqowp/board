package kr.ac.seoil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.seoil.mapper.UserMapper;
import kr.ac.seoil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kr.ac.seoil.vo.UserVO;
import lombok.extern.log4j.Log4j;
import java.text.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@Log4j
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    UserVO newUser = new UserVO();

    @RequestMapping("/seoil/list.do")
    public String list() {
        return "list";
    }

    @RequestMapping("/seoil/view.do")
    public String view() {
        return "view";
    }

    @RequestMapping("/index.do")
    public String index() {
        return "index";
    }

    @GetMapping("/ex01")
    public String ex01(UserVO vo) {
        log.info(vo);
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("userId") String name) {
        log.info(name);
        return name;
    }
    //http://localhost:8080/user/ex02?userId=userId값 아무거나 입력

    @GetMapping("/ex04")
    public String ex04(Model model, UserVO vo, int page) {
        log.info("vo:" + vo);
        log.info("page:" + page);

        model.addAttribute("page", page);
        return ("vo:" + vo + "\n page : " + page);
    }
    //http://localhost:8080/user/ex04?page=페이지숫자&userName=userId값 아무거나 + &UserVO에서 지정하고싶은변수=값 식으로 추가하기

    @GetMapping("/ex04_1")
    public String ex04(UserVO vo, @ModelAttribute("page") int page) {
        log.info("vo:" + vo);
        log.info("page:" + page);

        return "sample/ex04";
    }
    //http://localhost:8080/user/ex04_1?page=2&userId=userid

    @GetMapping("/ex05")
    public String ex05() {
        return "redirect:/sample/index.do";
    }

    @GetMapping("/ex06")
    public @ResponseBody UserVO ex06() {
        UserVO vo = new UserVO();
        vo.setUserNm("홍길동");
        vo.setUserPw("30");

        return vo;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        String msg = "{\"name\":\"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

//정의하고싶은 데이터를 입력하면 해당 내용이 반영된 행 값들 출력 (NOT NULL, UNIQUE 주의하기)
    @GetMapping("/ex08")
    public @ResponseBody Map<String, Object> ex08_insert() {
        UserVO newUser = new UserVO();
        newUser.setUserId("user12345");
        newUser.setUserNm("user12345");
        newUser.setUserPw("user12345");
        newUser.setUseYn("N");

        userService.insertUser(newUser);

        UserVO insertedUser = userService.getUserByIdAndPw("user12345", "user12345");

        Map<String, Object> result08_insert = new HashMap<>();
        result08_insert.put("seqUser", insertedUser.getSeqUser());
        result08_insert.put("userId", insertedUser.getUserId());
        result08_insert.put("userNm", insertedUser.getUserNm());
        result08_insert.put("userPw", insertedUser.getUserPw());
        result08_insert.put("useYn", insertedUser.getUseYn());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (insertedUser.getRegDt() != null) {
            result08_insert.put("regDt", dateFormat.format(insertedUser.getRegDt()));
        } else {
            result08_insert.put("regDt", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        if (insertedUser.getModDt() != null) {
            result08_insert.put("modDt", dateFormat.format(insertedUser.getModDt()));
        } else {
            result08_insert.put("modDt", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        if (insertedUser.getModId() != null) {
            result08_insert.put("modId", insertedUser.getModId());
        } else {
            result08_insert.put("modId", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        // 로그에 날짜 형식을 지정하여 출력
        log.info("새 사용자가 성공적으로 삽입 되었습니다 : " +
                "seqUser=" + insertedUser.getSeqUser() + ", " +
                "userId=" + insertedUser.getUserId() + ", " +
                "userNm=" + insertedUser.getUserNm() + ", " +
                "userPw=" + insertedUser.getUserPw() + ", " +
                "useYn=" + insertedUser.getUseYn() + ", " +
                "regDt=" + (insertedUser.getRegDt() != null ? dateFormat.format(insertedUser.getRegDt()) : "NULL") + ", " +
                "modDt=" + (insertedUser.getModDt() != null ? dateFormat.format(insertedUser.getModDt()) : "NULL") + ", " +
                "modId=" + (insertedUser.getModId() != null ? insertedUser.getModId() : "NULL"));


        return result08_insert;
    }

    //알고싶은 데이터의 userId와 UserPw를 입력하면 조회된 행 전체를 출력
    @GetMapping("/ex09")
    public @ResponseBody Map<String, Object> ex08_select() {

        UserVO insertedUser = userService.getUserByIdAndPw("testuser1", "testuser1");

        Map<String, Object> result08_select = new HashMap<>();
        result08_select.put("seqUser", insertedUser.getSeqUser());
        result08_select.put("userId", insertedUser.getUserId());
        result08_select.put("userNm", insertedUser.getUserNm());
        result08_select.put("userPw", insertedUser.getUserPw());
        result08_select.put("useYn", insertedUser.getUseYn());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (insertedUser.getRegDt() != null) {
            result08_select.put("regDt", dateFormat.format(insertedUser.getRegDt()));
        } else {
            result08_select.put("regDt", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        if (insertedUser.getModDt() != null) {
            result08_select.put("modDt", dateFormat.format(insertedUser.getModDt()));
        } else {
            result08_select.put("modDt", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        if (insertedUser.getModId() != null) {
            result08_select.put("modId", insertedUser.getModId());
        } else {
            result08_select.put("modId", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        // 로그에 날짜 형식을 지정하여 출력
        log.info("새 사용자가 성공적으로 삽입 되었습니다 : " +
                "seqUser=" + insertedUser.getSeqUser() + ", " +
                "userId=" + insertedUser.getUserId() + ", " +
                "userNm=" + insertedUser.getUserNm() + ", " +
                "userPw=" + insertedUser.getUserPw() + ", " +
                "useYn=" + insertedUser.getUseYn() + ", " +
                "regDt=" + (insertedUser.getRegDt() != null ? dateFormat.format(insertedUser.getRegDt()) : "N/A") + ", " +
                "modDt=" + (insertedUser.getModDt() != null ? dateFormat.format(insertedUser.getModDt()) : "N/A") + ", " +
                "modId=" + (insertedUser.getModId() != null ? insertedUser.getModId() : "NULL"));


        return result08_select;
    }

    //기존 사용자의 정보를 조회하여 데이터를 변경 후 출력
    @GetMapping("/ex10")
    public @ResponseBody Map<String, Object> ex08_update_no_before() {
        // 기존 사용자 정보 변경
        UserVO updateUser = new UserVO();
        updateUser.setUserId("testuser1");
        updateUser.setUserPw("testuser1");
        updateUser.setUserNm("testuser1");
        userService.updateUserNmYn(updateUser);
        // 삽입된 사용자 정보 조회
        UserVO insertedUser = userService.getUserByIdAndPw("testuser1", "securepass1");

        //UserVO 객체를 JSON 문자열로 변환
        Map<String, Object> result08_update = new HashMap<>();
        result08_update.put("seqUser", insertedUser.getSeqUser());
        result08_update.put("userId", insertedUser.getUserId());
        result08_update.put("userNm", insertedUser.getUserNm());
        result08_update.put("userPw", insertedUser.getUserPw());
        result08_update.put("useYn", insertedUser.getUseYn());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (insertedUser.getRegDt() != null) {
            result08_update.put("regDt", dateFormat.format(insertedUser.getRegDt()));
        } else {
            result08_update.put("regDt", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        if (insertedUser.getModDt() != null) {
            result08_update.put("modDt", dateFormat.format(insertedUser.getModDt()));
        } else {
            result08_update.put("modDt", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        if (insertedUser.getModId() != null) {
            result08_update.put("modId", insertedUser.getModId());
        } else {
            result08_update.put("modId", "NULL"); // 기본값 또는 적절한 값을 사용
        }

        // 로그에 날짜 형식을 지정하여 출력
        log.info("새 사용자가 성공적으로 삽입 되었습니다 : " +
                "seqUser=" + insertedUser.getSeqUser() + ", " +
                "userId=" + insertedUser.getUserId() + ", " +
                "userNm=" + insertedUser.getUserNm() + ", " +
                "userPw=" + insertedUser.getUserPw() + ", " +
                "useYn=" + insertedUser.getUseYn() + ", " +
                "regDt=" + (insertedUser.getRegDt() != null ? dateFormat.format(insertedUser.getRegDt()) : "NULL") + ", " +
                "modDt=" + (insertedUser.getModDt() != null ? dateFormat.format(insertedUser.getModDt()) : "NULL") + ", " +
                "modId=" + (insertedUser.getModId() != null ? insertedUser.getModId() : "NULL"));


        return result08_update;
    }
}



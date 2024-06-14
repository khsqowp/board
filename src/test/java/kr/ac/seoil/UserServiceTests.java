package kr.ac.seoil;

import kr.ac.seoil.service.UserService;
import kr.ac.seoil.vo.UserVO;
import kr.ac.seoil.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test       // 아이디 newuser2와 비밀번호 newpassword2를 입력하고 아이디가 newuser2와 일치하면로그인 성공 테스트
    public void testLoginSuccess() {
        UserVO user = userService.getUserByIdAndPw("newuser2", "newpassword2");
        assertNotNull(user);
        assertEquals("newuser2", user.getUserId());
        log.info("로그인에 성공하였습니다.");
    }

    @Test       //로그인 실패
    public void testLoginFailure() {
        UserVO user = userService.getUserByIdAndPw("invaliduser", "invalidpassword");
        assertNull(user);
        log.info("로그인정보가 일치하지 않습니다.");
    }

    @Test
    public void testInsetUserWithSeq() {
        // 새로운 사용자 정보 생성
        UserVO newUser = new UserVO();
        newUser.setUserId("testuser1");
        newUser.setUserNm("testuser1");
        newUser.setUserPw("newpassword2");
        newUser.setUseYn("Y");

        // 사용자 삽입
        userService.insertUser(newUser);

        // 삽입된 사용자 정보 조회
        UserVO insertedUser = userService.getUserByIdAndPw("newuser2", "newpassword2");

        // 검증
        assertNotNull(insertedUser);
        assertEquals("newuser2", insertedUser.getUserId());
        assertEquals("New User2", insertedUser.getUserNm());
        log.info("새 사용자가 성공적으로 삽입되었습니다.");
    }

    @Test
    public void testInsertUserWithoutSeq() {
        UserVO newUser = new UserVO();
        newUser.setUserId("anotheruser1");
        newUser.setUserNm("Another User1");
        newUser.setUserPw("anotherpassword1");
        newUser.setUseYn("Y");

        userService.insertUserWithoutSeq(newUser);

        UserVO insertedUser = userService.getUserByIdAndPw("anotheruser1", "anotherpassword1");

        assertNotNull(insertedUser);
        assertEquals("anotheruser1", insertedUser.getUserId());
        assertEquals("Another User1", insertedUser.getUserNm());
        log.info("새 사용자가 성공적으로 삽입되었습니다. (시퀀스 없이)");
    }

    @Test
    public void testupdateUserNmYn() {
//        UserVO updateUser = userService.getUserByIdAndPw("user1", "securepass2");

        UserVO updateUser = new UserVO();
        updateUser.setUserId("user13");
        updateUser.setUserPw("password1");
        updateUser.setUserNm("수정된 유저");
        updateUser.setUseYn("N");
        userService.updateUserNmYn(updateUser);

        log.info(updateUser);
    }

    @Test
    public void testDeleteUser() {
        String userId = "testuser1";
        userService.deleteUser(userId);
    }

}
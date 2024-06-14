package kr.ac.seoil.mapper;

import kr.ac.seoil.vo.UserVO;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    UserVO getUserByIdAndPw(@Param("userId") String userId, @Param("userPw") String userPw);

//    UserVO getUserAll(@Param("seqUser") Long seqUser, @Param("userId") String userId, @Param("userNm") String userNm, @Param("userPw") String userPw, @Param("userYn") String userYn);

    void insertUser(UserVO user);

    void insertUserWithoutSeq(UserVO user);

    void updateUserNmYn(UserVO user);

    void deleteUser(String userId);
}

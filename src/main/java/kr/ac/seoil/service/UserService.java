package kr.ac.seoil.service;


import kr.ac.seoil.mapper.UserMapper;
import kr.ac.seoil.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserVO getUserByIdAndPw(String userId, String userPw) { return userMapper.getUserByIdAndPw(userId, userPw); }

    public void insertUser(UserVO user){
        userMapper.insertUser(user);
    }

    public void insertUserWithoutSeq(UserVO user){userMapper.insertUserWithoutSeq(user);}

    public void updateUserNmYn(UserVO user){userMapper.updateUserNmYn(user);}

    public void deleteUser(String userId){userMapper.deleteUser(userId);}

}

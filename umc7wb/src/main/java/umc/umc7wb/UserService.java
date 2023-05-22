package umc.umc7wb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.umc7wb.model.*;

import java.util.List;

//@Service
//public class UserService {
//    final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private final UserDao userDao;
//
//    @Autowired
//    public UserService(UserDao userDao) {
//        this.userDao = userDao;
//    }
//    public User createUser(User user){
//        User userIdx = userDao.create(user);
//        return userIdx;
//
//    }
//}

@Service
public class UserService {
    @Autowired
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // 이메일 중복 확인
    public int checkEmail(String email) throws Exception {
        try {
            return userDao.checkEmail(email);
        } catch (Exception e) {
            throw new Exception("데이터베이스 오류");
        }
    }

    // 회원 가입
    public PostUserRes createUser(PostUserReq postUserReq) throws Exception {
        if (checkEmail(postUserReq.getEmail()) == 1) {
            throw new Exception("이미 존재하는 이메일");
        }
        try {
            int userIdx = userDao.createUser(postUserReq);
            return new PostUserRes(userIdx);
        } catch (Exception e) {
            throw new Exception("회원 가입 실패");
        }
    }

    public User findById(Long id) {
        return userDao.getUser(id);
    }

    public void modifyUser(PatchUserReq PatchUserReq) throws Exception {
        try {
            int result = userDao.modifyUser(PatchUserReq);
            if (result == 0) {
                throw new Exception("회원정보 수정 실패");
            }
        } catch (Exception e) {
            throw new Exception("데이터베이스 오류");
        }
    }
//
//        @Transactional
//        public Long save (User user){
//            return userRepository.save(user).getId();
//}
//            // 로그인
//            public PostLoginRes logIn (PostLoginReq postLoginReq) throws Exception {
//                User user = userDao.getPassword(postLoginReq);
//                if (postLoginReq.getPassword().equals(user.getPwd())) {
//                    int userIdx = userDao.getPassword(postLoginReq).getUserIdx();
//                    return new PostLoginRes(userIdx);
//                } else {
//                    throw new Exception("로그인 실패");
//                }
//            }

            @Transactional
            public Long update (Long id, User user) {
                User currentUser = findById(id);
                currentUser.update(user.getPwd(), user.getPhoneNum(), user.getEmail());
                return id;
            }
                // 전체 유저 정보 조회
                public List<GetUserRes> getUsers() throws Exception {
                    try {
                        return userDao.getUsers();
                    } catch (Exception e) {
                        throw new Exception("유저 정보 조회 실패");
                    }
                }

//                @Transactional
//                public void delete (Long id){
//                    User user = findById(id);
//                    userRepository.delete(user);
//                    // 유저 아이디로 정보 조회
//                    public GetUserRes getUser ( int userIdx) throws Exception {
//                        try {
//                            return userDao.getUser(userIdx);
//                        } catch (Exception e) {
//                            throw new Exception("유저 정보 조회 실패");
//                        }
//                    }
//                }
            }

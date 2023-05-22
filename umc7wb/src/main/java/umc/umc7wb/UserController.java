package umc.umc7wb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.umc7wb.model.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입 API
    @ResponseBody
    @PostMapping("/sign-up")
    public PostUserRes createUser(@RequestBody PostUserReq postUserReq) {
        try {
            return userService.createUser(postUserReq);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @ResponseBody
//    @PostMapping("/signup")
//    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
//        if (postUserReq.getEmail() == null) {
//            return new BaseResponse<>(EMPTY_EMAIL);
//        }
//        PostUserRes postUserRes = userService.createUser(postUserReq);
//        return new BaseResponse<>(postUserRes);
//    }

    // 전체 유저 정보 조회 API
    @ResponseBody
    @GetMapping("")
    public List<GetUserRes> getUsers() {
        try {
            return userService.getUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 유저 아이디로 정보 조회 API
    @ResponseBody
    @GetMapping("/{userIdx}")
    public User getUser(@PathVariable("userIdx") Long userIdx) {
        try {
            return userService.findById(userIdx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 정보 수정 API
    @ResponseBody
    @PatchMapping("/{userIdx}")
    public String modifyUser(@PathVariable("userIdx") Long userIdx, @RequestBody String pwd) {
        try {
            userService.modifyUser(new PatchUserReq(userIdx, pwd));
            return "회원 정보가 수정되었습니다.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
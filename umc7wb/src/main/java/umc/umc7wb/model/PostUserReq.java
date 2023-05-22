package umc.umc7wb.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUserReq {
    private String pwd;
    private String phoneNum;
    private String email;
}

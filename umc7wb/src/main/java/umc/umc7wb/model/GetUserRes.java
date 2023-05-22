package umc.umc7wb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private Long userID;
    private String pwd;
    private String phoneNum;
    private String email;
}

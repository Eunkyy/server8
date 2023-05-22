package umc.umc7wb.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private Long userID;
    private String pwd;
    private String phoneNum;
    private String email;

    public String toString() {
        return "ID: " + userID + ", pwd: " + pwd + ", phone: " + phoneNum + ", email: " + email;
    }

    public void update(String pwd, String phoneNum, String email) {
        this.pwd = pwd;
        this.phoneNum = phoneNum;
        this.email = email;
    }
}

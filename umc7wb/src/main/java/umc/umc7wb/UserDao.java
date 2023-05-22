package umc.umc7wb;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.umc7wb.model.GetUserRes;
import umc.umc7wb.model.PatchUserReq;
import umc.umc7wb.model.PostUserReq;
import umc.umc7wb.model.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    public void setDatasource(DataSource datasource) {this.jdbcTemplate = new JdbcTemplate(datasource);}

    // 회원 가입
    public int createUser(PostUserReq postUserReq) {
        String createUserQuery = "insert into User (pwd, phoneNum, email) values (?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getPwd(), postUserReq.getPhoneNum(), postUserReq.getEmail()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);
        String lastInsertIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 전체 유저 정보 조회
    public List<GetUserRes> getUsers() {
        String getUsersQuery = "select * from User";

        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getLong("userIdx"),
                        rs.getString("pwd"),
                        rs.getString("phoneNum"),
                        rs.getString("email"))
        );
    }

    // 유저 아이디로 정보 조회
    public User getUser(Long userIdx) {
        String getUserQuery = "select * from User where userIdx = ?";
        Long getUserParams = userIdx;

        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new User(
                        rs.getLong("userIdx"),
                        rs.getString("pwd"),
                        rs.getString("phoneNum"),
                        rs.getString("email")),
                getUserParams
        );
    }

    // 회원 정보 수정
    public int modifyUser(PatchUserReq patchUserReq) {
        String modifyUserQuery = "update User set pwd = ? where userIdx = ?";
        Object[] modifyUserParams = new Object[]{patchUserReq.getPwd(), patchUserReq.getUserID()};

        return this.jdbcTemplate.update(modifyUserQuery, modifyUserParams);
    }

    // 이메일 중복 확인
    public int checkEmail(String email) {
        String checkEmailQuery = "select exist(select email from User where email = ?)";
        String checkEmailParams = email;

        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

//
//    public DeleteUserRes deleteByUserIdx(DeleteUserReq deleteUserReq){
//        String selectUserIdxQuery = "select  nickName, name, email, phone, password from User where userIdx=?";
//        String deleteUserIdxQuery = "delete from User where userIdx=?";
//        int idxParams = deleteUserReq.getUserIdx();
//
//        DeleteUserRes deleteUserRes = this.jdbcTemplate.queryForObject(selectUserIdxQuery,
//                (rs, rowNum) -> new DeleteUserRes(
//                        rs.getString("name"),
//                        rs.getString("nickName"),
//                        rs.getString("phone"),
//                        rs.getString("email"),
//                        rs.getString("password")),
//                idxParams);
//
//        this.jdbcTemplate.update(deleteUserIdxQuery,idxParams);
//
//        return deleteUserRes;
//    }

    //아이디가 존재하는지 체크
    public int checkId(int userIdx){
        String checkUserIdxQuery = "select exists(select userIdx from User where userIdx = ?)";
        int checkUserIdxParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserIdxQuery,
                int.class,
                checkUserIdxParams);

    }

}



//@Repository
//public class UserDao {
//
//    final Logger log = LoggerFactory.getLogger(this.getClass());
//   private JdbcTemplate jdbcTemplate;
//   public void setDatasource(DataSource datasource) {this.jdbcTemplate = new JdbcTemplate(datasource);}
//
//    public User create(User user){
//       // try {
//
//            String sql1 = "insert into USER(userID,pwd,phoneNum,email) VALUES(?,?,?,?)";
//            Object[] param = new Object[]{user.getUserID(),user.getPwd(),user.getPhoneNum(),user.getEmail()};
//        System.out.println(user.toString());
//        System.out.println(param[0] + ", " + param[1] + "," + param[2] + "," + param[3]);
//            this.jdbcTemplate.update("insert into USER(userID,pwd,phoneNum,email) VALUES(?,?,?,?)",user.getUserID(),user.getPwd(),user.getPhoneNum(),user.getEmail());	// JDBCUtil 에 insert문과 매개 변수 설정
//        System.out.println("create" + user.getEmail());
////        } catch (Exception ex) {
////            jdbcUtil.rollback();
////            ex.printStackTrace();
////        } finally {
////            jdbcUtil.commit();
////            jdbcUtil.close();	// resource 반환
////        }
//        return user;
//    }
//}

package cursorTeam5.demo.DTO;

import lombok.Getter;
import lombok.Setter;
// joinDTO 클래스에 데이터를 넣으면 Setter메소드
// joinDTO 클래서에서 데이터를 뽑아올 Getter메소드
@Setter
@Getter
public class JoinDTO {

    private String username;
    private String password;
}

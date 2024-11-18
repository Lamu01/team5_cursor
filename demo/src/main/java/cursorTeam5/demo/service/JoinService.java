package cursorTeam5.demo.service;

import cursorTeam5.demo.DTO.JoinDTO;
import cursorTeam5.demo.entity.UserEntity;
import cursorTeam5.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDTO joinDTO) {

        //DB에 이미 동일한 username을 가진 회원이 있는지 확인



        // DTO의 유저 데이터를 entity로 변경시키는 과정
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}

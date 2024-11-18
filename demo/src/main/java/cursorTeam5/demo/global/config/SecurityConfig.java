package cursorTeam5.demo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { //비밀번호 암호화를 진행할 메서드

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                );
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        //admin경로에 갔을때 오류 페이지가 발생하지 않고 자동으로 로그인 페이지로 이동
                        .loginProcessingUrl("/loginProc")
                        // html로그인 코드에서 로그인 데이터를 /loginProc라는 경로로 보내면, 이 경로를
                        // spring security가 받아서 로그인 처리를 진행
                        .permitAll()
                        // 해당 경로에 대해서 아무나 들어올 수 있도록 설정
                );

        http
                .csrf((auth) -> auth.disable());


        return http.build();
    }
}

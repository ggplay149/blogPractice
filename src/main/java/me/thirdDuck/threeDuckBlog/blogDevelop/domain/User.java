package me.thirdDuck.threeDuckBlog.blogDevelop.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name ="users")
@NoArgsConstructor
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    //생성자에 nickname 추가
    @Builder
    public User(String email, String password, String auth,String nickname){
        this.email=email;
        this.password=password;
        this.nickname=nickname;
    }

    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }

    /* 시큐리티 관련*/

    //사용자가 가지고 있는 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자 식별가능한 id를 반환(고유의 값)
    @Override
    public String getUsername(){
        return email;
    }

    //사용자의 패스워드를 반환, 이후 암호화해서 저장
    @Override
    public String getPassword(){
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 사용가능 여부 반환
    @Override
    public boolean isEnabled() {
        //계정이 사용가능한지 확인하는 로직
        return true; //true -> 사용가능
    }
}

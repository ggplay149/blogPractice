package me.thirdDuck.threeDuckBlog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id; //DB "id"컬럼과 매칭

    @Column(name="name", nullable = false)
    private String name; //DB "name" 컬럼과 매칭
}

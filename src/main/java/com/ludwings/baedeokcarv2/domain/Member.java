package com.ludwings.baedeokcarv2.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "writer")
    private List<Post> postList = new ArrayList<>();

    public void updateMemberInfo(Member info) {
        this.password = info.getPassword();
        this.name = info.getName();
    }
}

package com.ludwings.baedeokcarv2.domain.model;

import com.ludwings.baedeokcarv2.domain.BaseEntity;
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
public class Member extends BaseEntity {
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

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Car> carList = new ArrayList<>();

    @OneToMany(mappedBy = "reservedMember", fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();

    public void updateMemberInfo(Member info) {
        this.password = info.getPassword();
        this.name = info.getName();
    }

    public void addCar(Car car) {
        carList.add(car);
    }
}

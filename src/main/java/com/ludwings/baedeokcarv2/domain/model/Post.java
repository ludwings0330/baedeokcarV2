package com.ludwings.baedeokcarv2.domain.model;

import com.ludwings.baedeokcarv2.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    int hitCount = 0;

    public void modifyInfo(String title, String content) {
        if (title != null) {
            this.title = title;
        }

        if (content != null) {
            this.content = content;
        }
    }

    public int hitPost() {
        this.hitCount ++;
        return hitCount;
    }
}

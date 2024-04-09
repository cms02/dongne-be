package com.dongne.dongnebe.domain.category.channel.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "channel")
@Getter
@NoArgsConstructor
public class Channel {

    @Id
    @Column(name = "channel_id")
    @GeneratedValue
    private Long channelId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "channel")
    private List<Board> boardList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_code")
    private Zone zone;

    @Builder
    public Channel(Long channelId, String name, SubCategory subCategory, Zone zone) {
        this.channelId = channelId;
        this.name = name;
        this.subCategory = subCategory;
        this.zone = zone;

    }
}

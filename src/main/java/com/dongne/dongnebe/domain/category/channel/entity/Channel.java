package com.dongne.dongnebe.domain.category.channel.entity;

import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Channel(Long channelId, String name, SubCategory subCategory) {
        this.channelId = channelId;
        this.name = name;
        this.subCategory = subCategory;
    }
}

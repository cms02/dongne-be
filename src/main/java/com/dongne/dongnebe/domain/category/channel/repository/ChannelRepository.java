package com.dongne.dongnebe.domain.category.channel.repository;

import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}

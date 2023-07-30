package com.ssafy.campfire.bootcamp.domain;

import javax.persistence.*;

import com.ssafy.campfire.bootcamp.repository.TrackRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.reactive.TransactionalOperatorExtensionsKt;

@Getter
@NoArgsConstructor
@Entity
@Table(name ="boot_track")
public class BootTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;

    @Builder
    public BootTrack(Bootcamp bootcamp, Track track){
        this.bootcamp = bootcamp;
        this.track = track;
    }
}

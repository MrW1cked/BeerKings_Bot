package com.back.beerkings.models.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PodiumPK.class)
@Table(name = "PODIUM")
public class PodiumMO {

    @Id
    @Column(name = "ID")
    private Long id;

    @Id
    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DRINKS")
    private Integer drinks;

}

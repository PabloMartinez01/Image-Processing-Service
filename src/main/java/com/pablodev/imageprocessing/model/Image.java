package com.pablodev.imageprocessing.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String format;

    @Column(nullable = false)
    private Long size;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

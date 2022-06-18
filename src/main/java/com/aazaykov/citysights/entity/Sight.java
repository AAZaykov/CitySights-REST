package com.aazaykov.citysights.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sights")
@NoArgsConstructor
@Getter
@Setter
public class Sight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SightType type;

    @Column(name = "city")
    private Long cityId;

    public Sight(String name, Date buildDate, String description, SightType type, Long city) {
        this.name = name;
        this.date = buildDate;
        this.description = description;
        this.type = type;
        this.cityId = city;
    }

    @Override
    public String toString() {
        return "Sight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", city='" + cityId + '\'' +
                '}';
    }
}

package com.aazaykov.citysights.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private Long population;

    @Column(name = "metro")
    private boolean metroAvailable;

    @Column(name = "country")
    private String country;

    public City(String name, Long population, boolean metro, String country) {
        this.name = name;
        this.population = population;
        this.metroAvailable = metro;
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", metro=" + metroAvailable +
                ", country='" + country + '\'' +
                '}';
    }
}

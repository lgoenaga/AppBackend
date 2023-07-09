package com.iudigital.appbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String successDate;
    private String description;

    private String created_at;
    private String updated_at;


    @NotBlank
    @Column(columnDefinition = "varchar(30) default 'ATRACO'")
    @Enumerated(value = EnumType.STRING)
    private Delito delito;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return  "\t"+"Case {"+ "\n"+
                "\t\t\t\t\t"+"id= " + id + "\n"+
                "\t\t\t\t\t"+"successDate= " + successDate + '\n' +
                "\t\t\t\t\t"+"description= " + description + '\n' +
                "\t\t\t\t\t"+"location= " + '\n' +
                "\t\t\t\t\t\t\t\t\t"+"latitude= " + location.getAltitude() + '\n' +
                "\t\t\t\t\t\t\t\t\t"+"length= " + location.getLength() + '\n' +
                "\t\t\t\t\t\t\t\t\t"+"altitude= " + location.getAltitude() + '\n' +
                "\t\t\t\t\t"+"created_at= " + created_at + '\n' +
                "\t\t\t\t\t"+"updated_at= " + updated_at + '\n' +
                "\t\t\t\t\t"+"delito= " + delito + '\n' +
                "\t\t\t\t\t"+"user=" + user.getFull_name() + "\n" +
                "\t\t\t\t"+"}"+ "\n";
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
}

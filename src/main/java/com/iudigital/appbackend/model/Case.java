package com.iudigital.appbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cases")
public class Case {

    private static final String TAB = "\t\t\t\t\t";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String successDate;
    private String description;

    private String created_at;
    private String updated_at;


    @NotBlank
    @Column(columnDefinition = "varchar(30) default 'ATTRACT'")
    @Enumerated(value = EnumType.STRING)
    private Crime crime;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return  "\t"+ "Case {"+ "\n"+
                TAB + "id= " + id + "\n"+
                TAB + "successDate= " + successDate + '\n' +
                TAB + "description= " + description + '\n' +
                TAB + "location= " + '\n' +
                TAB + TAB +"latitude= " + location.getAltitude() + '\n' +
                TAB + TAB +"length= " + location.getLength() + '\n' +
                TAB + TAB +"altitude= " + location.getAltitude() + '\n' +
                TAB + "created_at= " + created_at + '\n' +
                TAB + "updated_at= " + updated_at + '\n' +
                TAB + "crime= " + crime + '\n' +
                TAB + "user=" + user.getFull_name() + "\n" +
                TAB + "}" + "\n";
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
}

package main.model;

import javax.persistence.*;

@Entity
@Table(name = "tour_details")
public class TourDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;
    @Column(length = 2000)
    private String description;


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /*
    uncomment if you want to have a bidrectional mapping
    @OneToOne(mappedBy = "tourDetails")
    private Tour tour;
    */
    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

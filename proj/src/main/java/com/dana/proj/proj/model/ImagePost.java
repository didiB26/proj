package com.dana.proj.proj.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "image_post")
public class ImagePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "counting", nullable = false)
    private int counting = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "image_path", nullable = true)
    private String imagePath;

    @Lob
    @Column(name = "image_size", length = Integer.MAX_VALUE, nullable = true)
    private byte[] imageSize;

    @OneToMany(mappedBy = "imagePost", cascade = CascadeType.ALL)
    private List<ImageComment> comments = new ArrayList<>();

    public int incrementCounting(){
        this.counting++;
        return this.counting;
    }

//to be included for https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//The following 2 methods I do not know yet how to use and why are necesarry
    public void addComment(ImageComment comment) {
        comments.add(comment);
        comment.setImagePost(this);
    }

    public void removeComment(ImageComment comment) {
        comments.remove(comment);
        comment.setImagePost(null);
    }

}

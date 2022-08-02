package com.dana.proj.proj.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;


@Entity
@Table(name = "image_post")
public class ImagePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    /*
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    */

    @Lob
    @Column(name = "image_size", length = Integer.MAX_VALUE, nullable = true)
    private byte[] imageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Date getDate() {
        return createDate;
    }

    public void setDate(Date date) {
        this.createDate = date;
    }
*/
    public byte[] getImageSize() {
        return imageSize;
    }

    public void setImageSize(byte[] imageSize) {
        this.imageSize = imageSize;
    }

    @Override
    public String toString() {
        return "ImagePost{" +
                "id=" + id +
                ", name='" + name + '\'' +
        //        ", date=" + createDate +
                ", imageSize=" + Arrays.toString(imageSize) +
                '}';
    }
}

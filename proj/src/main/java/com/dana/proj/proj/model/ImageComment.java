package com.dana.proj.proj.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "image_comm")
public class ImageComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comm", nullable = false, unique = true)
    private Long idComm;

    @Column(name = "text_comm", nullable = false)
    private String textComm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    //annotation complet necesara
    private ImagePost imagePost;

    public Long getIdComm() {
        return idComm;
    }

    public void setIdComm(Long idComm) {
        this.idComm = idComm;
    }

    public String getTextComm() {
        return textComm;
    }

    public void setTextComm(String textComm) {
        this.textComm = textComm;
    }

    public ImagePost getImagePost() {
        return imagePost;
    }

    public void setImagePost(ImagePost imagePost) {
        this.imagePost = imagePost;
    }

    @Override
    public String toString() {
        return "ImageComment{" +
                "idComm=" + idComm +
                ", textComm='" + textComm + '\'' +
                ", imagePost=" + imagePost +
                '}';
    }
}

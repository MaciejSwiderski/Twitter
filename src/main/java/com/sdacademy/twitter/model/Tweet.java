package com.sdacademy.twitter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for handling twitt data.
 */
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet implements BaseEntity {

    @Column(nullable = false)
    private String message;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "reTweet_id")
    private Tweet idReTweets;

    @OneToMany(mappedBy = "idReTweets", fetch = FetchType.LAZY)
    private Set<Tweet> subordinates;

    private Long creationTs;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCreationTs() {
        return creationTs;
    }

    public void setCreationTs(Long creationTs) {
        this.creationTs = creationTs;
    }

    public Tweet getIdReTweets() {
        return idReTweets;
    }

    public void setIdReTweets(Tweet idReTweets) {
        this.idReTweets = idReTweets;
    }

    public Set<Tweet> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Tweet> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long updateId) {
        this.id = updateId;
    }
}

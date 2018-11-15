package com.chess254.archcomp.Models;

import java.io.Serializable;

/**
 * Created by Yougourta on 3/28/17.
 */

public class Comment implements Serializable {
    private User user;
    private String comment;

    public Comment(User user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

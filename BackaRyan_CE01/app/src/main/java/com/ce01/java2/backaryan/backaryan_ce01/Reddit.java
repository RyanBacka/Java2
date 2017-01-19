// Ryan Backa
// Jav2 - 1611
// Reddit

package com.ce01.java2.backaryan.backaryan_ce01;

import java.io.Serializable;

/**
 * Created by ryankbacka on 11/30/16.
 */

class Reddit implements Serializable {

    String title;
    String author;
    int comments;

    Reddit(String _title, String _author, int _comments){
        title = _title;
        author = _author;
        comments = _comments;
    }
}

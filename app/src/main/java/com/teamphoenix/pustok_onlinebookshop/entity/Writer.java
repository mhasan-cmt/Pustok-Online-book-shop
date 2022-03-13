package com.teamphoenix.pustok_onlinebookshop.entity;

public class Writer {
    String writer_id,
            writer_name,
            description,
            profile_pic,
            followers,
            quote;

    public Writer(String writer_id,
                  String writer_name,
                  String description,
                  String profile_pic,
                  String followers,
                  String quote) {
        this.writer_id = writer_id;
        this.writer_name = writer_name;
        this.description = description;
        this.profile_pic = profile_pic;
        this.followers = followers;
        this.quote = quote;
    }

    public Writer(String writer_name,
                  String description,
                  String profile_pic,
                  String followers,
                  String quote) {
        this.writer_name = writer_name;
        this.description = description;
        this.profile_pic = profile_pic;
        this.followers = followers;
        this.quote = quote;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public String getWriter_name() {
        return writer_name;
    }

    public String getDescription() {
        return description;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getFollowers() {
        return followers;
    }

    public String getQuote() {
        return quote;
    }
}

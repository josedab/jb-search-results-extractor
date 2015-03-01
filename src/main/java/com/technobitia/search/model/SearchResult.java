package com.technobitia.search.model;

import com.google.common.base.MoreObjects;

public class SearchResult {
    String term;
    String title;
    String url;

    public SearchResult(String term, String title, String url) {
        this.term = term;
        this.title = title;
        this.url = url;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                           .add("title", title)
                           .add("url", url)
                           .toString();
    }
}

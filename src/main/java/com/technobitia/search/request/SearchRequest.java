package com.technobitia.search.request;

import java.net.URLEncoder;

public class SearchRequest {
    
    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

    
    private String originalTerm;
    private String searchTerm;
    private String userAgent;

    private SearchRequest(Builder requestBuilder) {
        this.originalTerm = requestBuilder.term;
        this.searchTerm = URLEncoder.encode(this.originalTerm);
        this.userAgent = requestBuilder.userAgent;
    }

    public String getOriginalTerm() {
        return originalTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public static class Builder {
        private final String term;
        private String userAgent = DEFAULT_USER_AGENT;

        public Builder(String term) {
            this.term = term;
        }
        
        public Builder withUserAgent(String userAgent){
            this.userAgent = userAgent;
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(this);
        }
    }
}

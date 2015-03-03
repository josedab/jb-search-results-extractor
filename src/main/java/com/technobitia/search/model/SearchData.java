package com.technobitia.search.model;

import java.util.List;

/**
 * Class that represents all the data that can be inferred from results
 * extracted from google search
 * 
 */
public class SearchData {
    private List<SearchResult> searchResults;
    private SocialProfile socialProfile;

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
    }

}

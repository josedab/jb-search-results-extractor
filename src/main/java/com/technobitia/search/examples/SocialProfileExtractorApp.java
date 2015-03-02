package com.technobitia.search.examples;

import com.technobitia.search.client.SearchResultClient;
import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.search.request.SearchRequest;

public class SocialProfileExtractorApp {

    public static void main(String[] args) throws SearchResultException {
        
        String termToSearch = "san francisco 49ers";
        SearchRequest searchRequest = new SearchRequest.Builder(termToSearch).build();
        SearchResultClient searchResultClient = new SearchResultClient();
        SocialProfile socialProfile = searchResultClient.extractSocialProfile(searchRequest);
        
        System.out.println(socialProfile);

    }

}

package com.technobitia.search.examples;

import java.util.List;

import com.technobitia.search.client.SearchResultClient;
import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.request.SearchRequest;

public class SearchResultScraper {

    public static void main(String[] args) throws SearchResultException {
        
        String termToSearch = "surf";
        SearchRequest searchRequest = new SearchRequest.Builder(termToSearch).build();
        SearchResultClient searchResultClient = new SearchResultClient();
        List<SearchResult> searchResults = searchResultClient.getSearchResults(searchRequest);
        
        System.out.println(searchResults);

    }

}

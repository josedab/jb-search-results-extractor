package com.technobitia.search.service;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.request.SearchRequest;

public class DocumentService {
    
    private static final String GOOGLE_SEARCH_PREFIX = "https://www.google.com/search?q=";

    public Document getSearchResultDocument(SearchRequest searchRequest) throws SearchResultException {
        checkNotNull(searchRequest);
        
        Document doc = null;
        String searchTerm = searchRequest.getSearchTerm();
        String url = getUrlForSearchTerm(searchTerm);
            try {
                doc = Jsoup.connect(url)
                           .userAgent(searchRequest.getUserAgent())
                           .get();
            } catch (IOException e) {
                throw new SearchResultException("I/O issue when requesting information from url:" + url);
            }

        return doc;
    }

    private String getUrlForSearchTerm(String searchTerm) {
        String url = GOOGLE_SEARCH_PREFIX + searchTerm;
        return url;
    }
}

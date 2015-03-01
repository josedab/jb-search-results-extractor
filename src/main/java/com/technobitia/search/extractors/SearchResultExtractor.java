package com.technobitia.search.extractors;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.technobitia.search.exceptions.InvalidSearchResultException;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.request.SearchRequest;

public class SearchResultExtractor implements Extractor<List<SearchResult>>{
    
    private static final String RESULT_SELECTOR = "h3.r>a";
    
    public List<SearchResult> extract(SearchRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        List<SearchResult> searchResultList = Lists.newLinkedList();
        Elements results = doc.select(RESULT_SELECTOR);

        if (results != null && !results.isEmpty()) {
            searchResultList = getSearchResults(request.getOriginalTerm(),
                                                results);
        }
        return searchResultList;
    }
    
    private List<SearchResult> getSearchResults(String term, Elements searchResults) {
        List<SearchResult> searchResultList = Lists.newArrayList();

        SearchResult searchResult = null;
        for (Element resultElement : searchResults) {
            
            try {
                searchResult = getSearchResult(term, resultElement);
                searchResultList.add(searchResult);
            } catch (InvalidSearchResultException e) {
                // Use of logger to showcase the url
            }
        }
        return searchResultList;
    }
    
    private SearchResult getSearchResult(String term, Element result) throws InvalidSearchResultException {
        String title = result.text();
        String url = result.absUrl("href");
        
        SearchResult searchResult = null;
        if (title != null && url != null) {
            searchResult = new SearchResult(term, title, url);
        } else {
            throw new InvalidSearchResultException("Title and url cannot be null related to a search result");
        }
        return searchResult;
    }
}

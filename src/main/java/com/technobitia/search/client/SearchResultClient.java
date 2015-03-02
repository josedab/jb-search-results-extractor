package com.technobitia.search.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.jsoup.nodes.Document;

import com.google.common.collect.Lists;
import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.extractors.Extractor;
import com.technobitia.search.extractors.SearchResultExtractor;
import com.technobitia.search.extractors.SocialProfileExtractor;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.search.request.SearchRequest;
import com.technobitia.search.service.DocumentService;

public class SearchResultClient {
    
    private DocumentService documentService;
    private Extractor<List<SearchResult>> searchResultExtractor;
    private Extractor<SocialProfile> socialProfileExtractor;
    
    public SearchResultClient() {
        documentService = new DocumentService();
        searchResultExtractor = new SearchResultExtractor();
        socialProfileExtractor = new SocialProfileExtractor();
    }
    
    public List<SearchResult> getSearchResults(SearchRequest request) throws SearchResultException {
        checkNotNull(request);
        List<SearchResult> searchResults = Lists.newLinkedList();
        
        Document document = documentService.getSearchResultDocument(request);
        searchResults = searchResultExtractor.extract(request, document);
        
        return searchResults;
    }
    
    public SocialProfile extractSocialProfile(SearchRequest request) throws SearchResultException {
        checkNotNull(request);
        
        Document document = documentService.getSearchResultDocument(request);
        SocialProfile socialProfile = socialProfileExtractor.extract(request, document);
        return socialProfile;
    }
}

package com.technobitia.search.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.jsoup.nodes.Document;

import com.google.common.collect.Lists;
import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.extractors.Extractor;
import com.technobitia.search.extractors.SearchResultExtractor;
import com.technobitia.search.model.SearchData;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.search.request.SearchRequest;
import com.technobitia.search.service.DocumentService;
import com.technobitia.search.service.SocialProfileService;

public class SearchResultClient {
    
    private DocumentService documentService;
    private SocialProfileService socialProfileService;
    
    private Extractor<List<SearchResult>> searchResultExtractor;
    
    public SearchResultClient() {
        documentService = new DocumentService();
        searchResultExtractor = new SearchResultExtractor();
        socialProfileService = new SocialProfileService();
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
        
        return extractSearchData(request).getSocialProfile();
    }
    
    public SearchData extractSearchData(SearchRequest request) throws SearchResultException {
        checkNotNull(request);
        
        Document document = documentService.getSearchResultDocument(request);
        List<SearchResult> searchResults = searchResultExtractor.extract(request, document);
        SocialProfile socialProfile = socialProfileService.getProfileFromDocumentAndLinks(request,
                                                                                          document, 
                                                                                          searchResults);
        SearchData searchDataResult = new SearchData();
        searchDataResult.setSearchResults(searchResults);
        searchDataResult.setSocialProfile(socialProfile);
        return searchDataResult;
    }
}

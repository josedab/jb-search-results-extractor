package com.technobitia.search.service;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.technobitia.search.exceptions.SearchResultException;
import com.technobitia.search.extractors.SocialProfileExtractor;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.request.SearchRequest;

@RunWith(MockitoJUnitRunner.class)
public class SocialProfileServiceTest {
    
    private static final String TERM_TO_SEARCH = "Mikill Pane";
    private static final List<SearchResult> SEARCH_RESULTS_EMPTY = Lists.newArrayList();
    private static final SearchRequest SEARCH_REQUEST = new SearchRequest.Builder(TERM_TO_SEARCH).build();

    @Mock
    private SocialProfileExtractor profileExtractorMock;
    
    @Mock
    private Document documentMock;
    
    @InjectMocks
    private SocialProfileService socialProfileService;

    @Test(expected = NullPointerException.class)
    public void whenGettingProfile_givenNullRequest_thenThrowNPE() throws SearchResultException {
        socialProfileService.getProfileFromDocumentAndLinks(null, documentMock, SEARCH_RESULTS_EMPTY);
    }

    @Test(expected = NullPointerException.class)
    public void whenGettingProfile_givenNullDocument_thenThrowNPE() throws SearchResultException {
        socialProfileService.getProfileFromDocumentAndLinks(SEARCH_REQUEST, null, SEARCH_RESULTS_EMPTY);
    }

    @Test(expected = NullPointerException.class)
    public void whenGettingProfile_givenNullSearchResults_thenThrowNPE() throws SearchResultException {
        socialProfileService.getProfileFromDocumentAndLinks(SEARCH_REQUEST, documentMock, null);
    }
}

package com.technobitia.search.extractors;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.search.request.SearchRequest;

@RunWith(MockitoJUnitRunner.class)
public class SearchResultExtractorTest {

    @Mock
    private Document documentMock;
    
    @Mock
    private SearchRequest searchRequestMock;
    
    @InjectMocks
    private SearchResultExtractor searchResultExtractor;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() {
        searchResultExtractor.extract(null, documentMock);
    }

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullDocument_thenThrowNPE() {
        searchResultExtractor.extract(searchRequestMock, null);
    }
}

package com.technobitia.search.extractors;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.search.request.SearchRequest;

@RunWith(MockitoJUnitRunner.class)
public class SocialProfileExtractorTest {

    @Mock
    private Document documentMock;
    
    @Mock
    private SearchRequest searchRequestMock;
    
    @InjectMocks
    private SocialProfileExtractor socialProfileExtractor;

    @Test(expected = NullPointerException.class)
    public void whenExtractingProfile_givenNullRequest_thenThrowNPE() {
        socialProfileExtractor.extract(null, documentMock);
    }

    @Test(expected = NullPointerException.class)
    public void whenExtractingProfile_givenNullDocument_thenThrowNPE() {
        socialProfileExtractor.extract(searchRequestMock, null);
    }
}

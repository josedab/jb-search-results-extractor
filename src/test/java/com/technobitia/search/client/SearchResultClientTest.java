package com.technobitia.search.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.search.client.SearchResultClient;
import com.technobitia.search.exceptions.SearchResultException;

@RunWith(MockitoJUnitRunner.class)
public class SearchResultClientTest {

    @InjectMocks
    private SearchResultClient searchResultClient;

    @Test(expected = NullPointerException.class)
    public void whenGettingSearchResults_givenNullRequest_thenThrowNPE() throws SearchResultException {
        searchResultClient.getSearchResults(null);
    }
}

package com.technobitia.search.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.search.exceptions.SearchResultException;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Test(expected = NullPointerException.class)
    public void whenGettingDocument_givenNullRequest_thenThrowNPE() throws SearchResultException {
        documentService.getSearchResultDocument(null);
    }
}

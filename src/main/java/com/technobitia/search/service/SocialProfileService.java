package com.technobitia.search.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.jsoup.nodes.Document;

import com.technobitia.search.extractors.SocialProfileExtractor;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.search.request.SearchRequest;

public class SocialProfileService {

    private SocialProfileExtractor socialProfileExtractor;

    public SocialProfileService() {
        socialProfileExtractor = new SocialProfileExtractor();
    }

    public SocialProfile getProfileFromDocumentAndLinks(SearchRequest request,
                                                         Document doc, 
                                                         List<SearchResult> searchResultList) {
        checkNotNull(request);
        checkNotNull(doc);
        checkNotNull(searchResultList);
        
        SocialProfile socialProfile = socialProfileExtractor.extract(request, doc);
        socialProfile = socialProfileExtractor.populate(socialProfile, searchResultList);
        return socialProfile;
    }
}

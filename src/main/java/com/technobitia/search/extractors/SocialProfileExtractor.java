package com.technobitia.search.extractors;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.technobitia.search.model.SearchResult;
import com.technobitia.search.model.SocialProfile;
import com.technobitia.search.request.SearchRequest;

public class SocialProfileExtractor 
        implements Extractor<SocialProfile>, Populator<SocialProfile, List<SearchResult>> {
    
    private static final String TYPE_FACEBOOK = "facebook";
    private static final String TYPE_TWITTER = "twitter";
    private static final String TYPE_INSTAGRAM = "instagram";
    private static final String TYPE_GOOGLE_PLUS = "google+";
    private static final String TYPE_GOOGLE_PLUS_ALTERNATIVE = "googleplus";
    private static final String TYPE_YOUTUBE = "youtube";
    private static final String TYPE_LINKEDIN = "linkedin";
    private static final String TYPE_MYSPACE = "myspace";
    
    private static final String PROFILES_PATTERN = "Facebook|Twitter|Instagram|Google+|Youtube|LinkedIn|Myspace";
    private static final String LINK_SELECTOR = "a.fl";
    
    public SocialProfile extract(SearchRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        
        Elements preselectedLinks = doc.getElementsMatchingOwnText(PROFILES_PATTERN).select(LINK_SELECTOR);
        
        Map<String, String> socialProfileMap = Maps.newHashMap();
        
        // Google search profile view
        if (!preselectedLinks.isEmpty()) {
            populateSocialProfileMap(socialProfileMap, preselectedLinks);
        }
        
        SocialProfile socialProfile = getSocialProfileFromProfileMap(socialProfileMap);
        socialProfile.setName(request.getOriginalTerm());
        return socialProfile;
    }
    
    private void populateSocialProfileMap(Map<String, String> socialProfileMap, Elements preSelectedlinks) {
        for (Element preselectedLink : preSelectedlinks) {
            String profileKey = preselectedLink.text().toLowerCase().trim();
            String profileUrl = preselectedLink.absUrl("href");
            populateSocialProfileMap(socialProfileMap, profileKey, profileUrl);
        }
        
    }

    private void populateSocialProfileMap(Map<String, String> socialProfileMap, String profileKey, String profileValue) {
        if (!socialProfileMap.containsKey(profileKey)) {
            socialProfileMap.put(profileKey, profileValue);
        }
    }

    private SocialProfile getSocialProfileFromProfileMap(Map<String, String> socialProfileMap) {

        SocialProfile socialProfile = new SocialProfile();

        for (Entry<String, String> socialProfileEntry : socialProfileMap.entrySet()) {
            String profileKey = socialProfileEntry.getKey();
            String profileValue = socialProfileEntry.getValue();

            if (profileKey.equalsIgnoreCase(TYPE_FACEBOOK)) {
                socialProfile.setFacebook(profileValue);
            } 
            else if (profileKey.equalsIgnoreCase(TYPE_TWITTER)) {
                socialProfile.setTwitter(profileValue);
            } 
            else if (profileKey.equalsIgnoreCase(TYPE_INSTAGRAM)) {
                socialProfile.setInstagram(profileValue);
            } 
            else if (profileKey.equalsIgnoreCase(TYPE_GOOGLE_PLUS)
                            || profileKey.equalsIgnoreCase(TYPE_GOOGLE_PLUS_ALTERNATIVE)) {
                socialProfile.setGooglePlus(profileValue);
            } 
            else if (profileKey.equalsIgnoreCase(TYPE_YOUTUBE)) {
                socialProfile.setYoutube(profileValue);
            } 
            else if (profileKey.equalsIgnoreCase(TYPE_LINKEDIN)) {
                socialProfile.setLinkedIn(profileValue);
            } 
            else if (profileKey.equalsIgnoreCase(TYPE_MYSPACE)) {
                socialProfile.setMyspace(profileValue);
            }

        }
        return socialProfile;
    }

    public SocialProfile populate(SocialProfile socialProfileToPopulate,List<SearchResult> searchResults) {
        checkNotNull(socialProfileToPopulate);
        checkNotNull(searchResults);
        
        for(SearchResult searchResult : searchResults){
            updateSocialProfile(socialProfileToPopulate, searchResult.getTitle(), searchResult.getUrl());
        }
        
        return socialProfileToPopulate;
    }
    
    private void updateSocialProfile(SocialProfile socialProfile, String title, String url) {

        String titleLower = title.toLowerCase();

        if (titleLower.indexOf("twitter") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getTwitter())) {
                socialProfile.setTwitter(url);
            }
        } else if (titleLower.indexOf("last.fm") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getLastfm())) {
                socialProfile.setLastfm(url);
            }
        } else if (titleLower.indexOf("facebook") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getFacebook())) {
                socialProfile.setFacebook(url);
            }
        } else if (titleLower.indexOf("wikipedia") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getWikipedia())) {
                socialProfile.setWikipedia(url);
            }
        } else if (url.indexOf("myspace") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getMyspace())) {
                socialProfile.setMyspace(url);
            }
        } else if (titleLower.indexOf("youtube") > -1
                && url.indexOf("artist") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getYoutube())) {
                socialProfile.setYoutube(url);
            }
        } else if (url.indexOf("instagram") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getInstagram())) {
                socialProfile.setInstagram(url);
            }
        } else if (url.indexOf("tumblr") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getTumblr())) {
                socialProfile.setTumblr(url);
            }
        } else if (titleLower.indexOf("linkedin") > -1
                && url.indexOf("company") > -1) {
            if (Strings.isNullOrEmpty(socialProfile.getLinkedIn())) {
                socialProfile.setLinkedIn(url);
            }
        }
    }
}

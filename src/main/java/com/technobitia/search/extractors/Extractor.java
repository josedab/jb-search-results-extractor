package com.technobitia.search.extractors;

import org.jsoup.nodes.Document;

import com.technobitia.search.request.SearchRequest;

public interface Extractor<T> {
    public T extract(SearchRequest request, Document doc);
}

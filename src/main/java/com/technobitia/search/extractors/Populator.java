package com.technobitia.search.extractors;


public interface Populator<TYPE, DATAFROM> {
    public TYPE populate(TYPE objectToPopulate, DATAFROM dataFrom);
}

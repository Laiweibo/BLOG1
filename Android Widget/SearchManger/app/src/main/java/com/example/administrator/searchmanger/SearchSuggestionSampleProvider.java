package com.example.administrator.searchmanger;

import android.content.SearchRecentSuggestionsProvider;
public class SearchSuggestionSampleProvider extends SearchRecentSuggestionsProvider {
    final static String AUTHORITY="com.coding.searchmanagerdemo.SearchSuggestionSampleProvider";
    final static int MODE=DATABASE_MODE_QUERIES;

    public SearchSuggestionSampleProvider(){
        super();
        setupSuggestions(AUTHORITY, MODE);
    }
}
package com.example.yummibot.business.recipe;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class YoutubeResults {
    private List<YoutubeObject> items;

    public List<YoutubeSearch> getVideoById() {
        List<YoutubeSearch> youtubeSearches = new ArrayList<>();
        for (YoutubeObject youtubeObject : items) {
            youtubeSearches.add(youtubeObject.getId());
        }
        return youtubeSearches;
    }
}

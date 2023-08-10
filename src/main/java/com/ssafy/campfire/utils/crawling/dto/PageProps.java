package com.ssafy.campfire.utils.crawling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageProps {
    private List<Data> data;
    private Data camp;
}

package com.ssafy.campfire.algorithm.service;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmService {
    private static String URL = "https://www.acmicpc.net/status?option-status-pid=on&problem_id=1000&user_id=dksek3050&language_id=-1&result_id=-1&from_problem=1"; //크롤링할 url

    public List<Algorithm> getAlgoDatas() throws IOException{

        System.out.println("서비스 - getAlgoDatas");
        
        List<Algorithm> algoList = new ArrayList<>();

        Document document = Jsoup.connect(URL).get();


        Elements contents = document.select("#status-table > tbody");
        System.out.println("contents = " + contents);


        for(Element content : contents){
            Algorithm algorithm = Algorithm.builder()
                    .bojId(content.select("td:nth-child(2) > a").text())
                    .result(content.select("td.result > span.result-text.result-ac").text())
                    .solveDate(content.select("td:nth-child(9) > a").attr("title"))
                            .build();
            System.out.println("algorithm.toString() = " + algorithm.toString());
            algoList.add(algorithm);
        }

        return algoList;

    }
}

package com.ssafy.campfire.utils.crawling;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.utils.crawling.dto.Data;
import com.ssafy.campfire.utils.crawling.dto.Script;
import com.ssafy.campfire.utils.crawling.enums.ApplicationProcessType;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BootcampCrawling {

    private static String BoottentList_URl ="https://boottent.sayun.studio/camps";


    public static List<Data> crawlingBootcamp() throws IOException, ParseException {

        //부트캠프 목록에서 각 부트캠프 상세페이지 url을 알기위한 정보(brandName, batchId) 얻어옴
        //부트캠프 상세페이지 url = BoottentList_Url + "/"+brandName+"_"+batchId
        Document document= Jsoup.connect(BoottentList_URl).get();
        Elements elements = document.select("#__NEXT_DATA__");
        String json = elements.get(0).data();
        System.out.println("json = " + json);

        //json 을 객체와 매핑시키는 객체
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);   //선언한 필드만 매핑

        //json 형식이 script 안의 Props 안의 PageProps 안의 Data 에 부트캠프가 리스트로 존재.
        Script script = objectMapper.readValue(json, Script.class);
        List<Data> dataList = script.getProps().getPageProps().getData().stream().toList();



        //크롤링한 데이터 리스트들을 한개씩 뽑아서 상세페이지를 알아낸 후 부트캠프 홈페이지 주소를 알아냄
//        for (Data data :dataList) {
//            System.out.println("data = " + data);

//            //부트캠프 상세 페이지 주소 알아냄
//            String detail_URL = BoottentList_URl + data.detailUrl();
//
//            //상세페이지 크롤링
//            Document detailDocument = Jsoup.connect(detail_URL).get();
//            Elements detailElements = detailDocument.select("#__next > div > section > main > div.container.full-width > div:nth-child(1) > div.camp_layer1__y_wsY > div > div:nth-child(3) > div > a");
//
//            data.setCampUrl(detailElements.attr("abs:href"));
//        }
        return dataList;
//        return null;
    }



}

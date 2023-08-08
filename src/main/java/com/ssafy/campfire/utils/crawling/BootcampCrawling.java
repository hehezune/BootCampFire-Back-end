package com.ssafy.campfire.utils.crawling;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.utils.crawling.dto.Data;
import com.ssafy.campfire.utils.crawling.dto.Script;
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

    private static String BoottentList_Url ="https://boottent.sayun.studio/camps";


    public static List<Bootcamp> crawlingBootcamp() throws IOException, ParseException {

        //부트캠프 목록에서 각 부트캠프 상세페이지 url을 알기위한 정보(brandName, batchId) 얻어옴
        //부트캠프 상세페이지 url = BoottentList_Url + "/"+brandName+"_"+batchId
        Document document= Jsoup.connect(BoottentList_Url).get();
        Elements elements = document.select("#__NEXT_DATA__");
        String json = elements.get(0).data();

        //json 을 객체와 매핑시키는 객체
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);   //선언한 필드만 매핑

        //json 형식이 script 안의 Props 안의 PageProps 안의 Data 에 부트캠프가 리스트로 존재.
        Script script = objectMapper.readValue(json, Script.class);
        List<Data> dataList = script.getProps().getPageProps().getData().stream().toList();



        List<Bootcamp> bootcampList = new ArrayList<>();
        int cnt = 0;
        //크롤링한 데이터 리스트들을 한개씩 뽑아서 상세페이지를 알아냄
        //json 파일을 가지고 와서 원하는 데이터로 가공함.
        for (Data data :dataList) {
            if(cnt == 20) break;
            //부트캠프 상세 페이지 주소 알아냄
            String detail_Url = BoottentList_Url + data.detailUrl();

            //코딩유무는 부트캠프 목록페이지에 존재해서 받아왔던 데이터를 저장했다가 넘겨줌
            String passRequiredOption = data.getPassRequiredOption();
            //상세페이지 크롤링
            document = Jsoup.connect(detail_Url).get();
            elements = document.select("#__NEXT_DATA__");
            script = objectMapper.readValue(elements.get(0).data(), Script.class);
            //크롤링한 json을 data로 만든다.
            data = script.getProps().getPageProps().getCamp();

            data.setPassRequiredOption(passRequiredOption);

            System.out.println("data.detailUrl() = " + detail_Url);
            System.out.println("getApplicationProcess() = " + data.getApplicationProcess().toString());
            
            
            //data를 부트캠프 객체로 바꿈
            Bootcamp bootcamp = data.toBootcamp();

            bootcampList.add(bootcamp);
            System.out.println();
            cnt ++;
        }


        return null;

    }

}

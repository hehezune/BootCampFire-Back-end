package com.ssafy.campfire.bootcamp.dto.response;

import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.domain.Track;

import java.time.LocalDateTime;
import java.util.List;

public record BootcampListResponseDto(
         Long id,
         String name, //부캠 이름 
         Double cost, //수강료
         Boolean support, //지원금
         Boolean hasCodingtest, //코테 유무
         String onOff, //온/오프
         LocalDateTime startDate,
         LocalDateTime endDate,
         String imgUrl,
         Integer reviewCnt,
         Double totalScore,
         List<String> tracks,
         List<String> regions

) {
//    cost 는 있음 없음으로 보내기
    // 평점


}

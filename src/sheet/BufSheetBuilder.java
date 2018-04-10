package sheet;

import org.Town;
import utility.IndexHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Gyummy on 2018-04-09.
 *
 */
public class BufSheetBuilder {

    private static final String HEADER_COMMON = "타임스탬프\t키\t순장선택\t";
    private static final int MIN_ROW = 2;
    private static final int MAX_ROW = 300;
    private static final int REPORT_START_COL = 26 * 4; //104
    private static final int REPORT_COL_LENGTH = 5;

    private static String buildKeyFormula(int row) {
        return "=left(" + IndexHelper.convertColumn(REPORT_START_COL + REPORT_COL_LENGTH) + row + ",12)&C" + row;
    }

    private static String buildHeaderRow(Town town) {
        StringBuilder builder = new StringBuilder(HEADER_COMMON);
        AtomicInteger cnt = new AtomicInteger(3);
        town.getCellList().forEach(cell -> cell.getMemberList().forEach(member -> {
            builder.append("=mid(").append(town.getImportRange(1, member.getColumn())).append(",5,5)").append("\t");
            cnt.incrementAndGet();
        }));
        while(cnt.intValue() < REPORT_START_COL) {
            builder.append("\t");
            cnt.incrementAndGet();
        }
        int townReportStartCol = IndexHelper.convertColumn(town.getReportStartColumn());
        while(cnt.intValue() < REPORT_START_COL + REPORT_COL_LENGTH) {
            builder.append("=").append(town.getImportRange(1, IndexHelper.convertColumn( townReportStartCol + cnt.intValue() - REPORT_START_COL))).append("\t");
            cnt.incrementAndGet();
        }
        return builder.toString();
    }

    private static String buildBodyRow(Town town, int row){
        StringBuilder builder = new StringBuilder();
        builder.append("=").append(town.getImportRange(row, IndexHelper.convertColumn(1))).append("\t")
                .append(buildKeyFormula(row)).append("\t")
        .append("=").append(town.getImportRange(row, IndexHelper.convertColumn(2))).append("\t");
        AtomicInteger cnt = new AtomicInteger(3);
        town.getCellList().forEach(cell -> cell.getMemberList().forEach(member -> {
            builder.append("=").append(town.getImportRange(row, member.getColumn())).append("\t");
            cnt.incrementAndGet();
        }));
        while(cnt.intValue() < REPORT_START_COL) {
            builder.append("\t");
            cnt.incrementAndGet();
        }
        int townReportStartCol = IndexHelper.convertColumn(town.getReportStartColumn());
        while(cnt.intValue() < REPORT_START_COL + REPORT_COL_LENGTH) {
            builder.append("=").append(town.getImportRange(row, IndexHelper.convertColumn( townReportStartCol + cnt.intValue() - REPORT_START_COL))).append("\t");
            cnt.incrementAndGet();
        }
        return builder.toString();
    }

    public static String build(Town town) {
        StringBuilder builder = new StringBuilder();
        builder.append(buildHeaderRow(town)).append("\n");
        for(int row = MIN_ROW; row <= MAX_ROW; row++) {
            builder.append(buildBodyRow(town, row));
            if(row < MAX_ROW)
                builder.append("\n");
        }
        return builder.toString();
    }

//    /*
//    * TODO -> 순장별로 만들 수 있을지?
//    * 1. 마을 별 순장 리스트
//    * 2. 순장 별 순원 리스트 (순장 포함)
//    * 3. 순원 별 열 번호 맵
//    * 4. 마을 별 IMPORT_RANGE 함수 템플릿 맵
//    * 5. 1+2 -> 헤더 생성
//    * 6. 2+3+4 -> 헤더 이외의 row 생성
//    */
//    private static final Map<String, String> TOWN_HEADER_MAP = new HashMap<>();
//    static {
//        TOWN_HEADER_MAP.put("동아리", "타임스탬프\t\t순장선택\t김창균08\t김현종11\t박예슬08\t유소리08\t이수임10\t이정선11\t인웅진07\t정명재08\t최지효11\t김진우10\t김충만11\t노하나09\t선우영진09\t신한나10\t양경모09\t이태석11\t전미정11\t조지훈10\t김은진09\t박예지10\t설재경08\t송지혜07\t안도원09\t안지혜07\t이민지06\t이은애09\t이철11\t이효진08\t임지혜10\t정찬희06\t배인성09\t강슬기10\t강유정06\t김은애10\t윤현주08\t이세호09\t이종혁11\t이지현10\t임현택09\t장영수11\t장원종08\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//        TOWN_HEADER_MAP.put("봉봉", "타임스탬프\t\t순장선택\t김보민12\t김현우11\t김혜진07\t문암미09\t송다혜11\t이태영11\t이효람10\t정철수10\t최성문10\t고광은10\t이재국10\t정지원10\t김태균11\t김난경06\t김혜진11\t노성현07\t박성희07\t박신혜07\t오선정08\t이은지06\t이재성07\t이재훈06\t이주혁10\t정효원06\t진혜진07\t황윤식06\t강은석10\t고주찬09\t김은정08\t김지윤09\t박은별08\t박주형07\t백영은08\t유원상06\t이미진08\t이은미07\t이지윤08\t임정민09\t조은지09\t한다희10\t김윤희10\t박수연10\t박유빈07\t위신영10\t이문수11\t이성훈10\t전소연10\t조수아11\t최내경09\t최민석10\t한희성08\t김영훈08\t김은지11\t남궁승조0\t유태영06\t이빛나라0\t정다연10\t조수연08\t한반석06\t한상은07\t한소희06\t허유정07\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//        TOWN_HEADER_MAP.put("지안", "타임스탬프\t\t순장선택\t김창균08\t김현종11\t박예슬08\t유소리08\t이수임10\t이정선11\t인웅진07\t정명재08\t최지효11\t김진우10\t김충만11\t노하나09\t선우영진09\t신한나10\t양경모09\t이태석11\t전미정11\t조지훈10\t김은진09\t박예지10\t설재경08\t송지혜07\t안도원09\t안지혜07\t이민지06\t이은애09\t이철11\t이효진08\t임지혜10\t정찬희06\t배인성09\t강슬기10\t강유정06\t김은애10\t윤현주08\t이세호09\t이종혁11\t이지현10\t임현택09\t장영수11\t장원종08\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//
//        TOWN_HEADER_MAP.put("세", "타임스탬프\t\t순장선택\t강선기12\t김기혁14\t김동석12\t심성보15\t이다경13\t임태훈16\t정지인12\t조혜지13\t지소진14\t홍진웅13\t문규환15\t김진선16\t김하림15\t김하영11\t남성희17\t박보은12\t박예찬17\t서은지12\t염기석12\t이연제14\t전소영13\t전현우14\t황준영12\t김은주14\t마보경15\t유자연12\t유지애16\t김동규16\t이현정16\t임정태14\t전혁인12\t조상현14\t진하랑13\t최정은15\t황애란13\t권지은17\t김동혁18\t김주연18\t김하영17\t나창대18\t손가은16\t이수빈18\t이재중16\t정현수17\t최은서16\t김도진13\t김하연14\t송지웅13\t양경주16\t오수빈15\t이재나17\t이지원13\t이지희14\t정인원13\t조지수13\t최형근12\t한정호12\t홍미선13\t\t\t\t\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//        TOWN_HEADER_MAP.put("사랑", "타임스탬프\t\t순장선택\t권미영12\t김다솔15\t김의영14\t박지운15\t안도현16\t이재훈15\t정예원12\t정유나14\t최수지14\t최재호15\t최진혁12\t김예은16\t김주아16\t김주희17\t김현진15\t류경준15\t문지현18\t박윤재18\t윤상필17\t윤슬기17\t이의정18\t이태준16\t최광철17\t강승미13\t김태양13\t김태훈17\t오혜인14\t유주헌14\t이근원17\t이한결13\t전우복14\t정성권14\t홍혜리13\t구창회16\t김효진13\t문수정17\t박성민17\t박수현17\t박지원13\t안용규13\t오경현13\t전혜진13\t차효정12\t황지혜14\t강지원13\t김단비18\t김예닮18\t김혜웅16\t서다빈16\t유원호18\t이영민16\t이정미18\t정귀철17\t조혜린16\t차정웅18\t최예진17\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//        TOWN_HEADER_MAP.put("삼", "타임스탬프\t\t순장선택\t권민선14\t김동건16\t김소현17\t김예인14\t백산16]\t신수영16\t이상목16\t이예솔13\t이찬휘17\t정윤진18\t김동호15\t김진일12\t박주성12\t오찬양14\t이도영17\t이형섭13\t임예린12\t조은영12\t조희수13\t최서희12\t최윤영15\t홍혜란12\t권나연18\t김남효17\t김예니18\t김윤수17\t박수림17\t박현수17\t여진호16\t오예은18\t정지은18\t최민혁18\t최현민17\t강영규16\t박명규17\t백시영14\t유도윤12\t윤지숙16\t이다은13\t이샛별14\t이선미14\t전현주16\t정현아15\t탁동민16\t박강영13\t김태은17\t박범재18\t송이슬15\t윤주형16\t이성욱15\t정혜진17\t조혜성16\t최수영15\t최예지16\t최종탁18\t김영우15\t김태은17\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//        TOWN_HEADER_MAP.put("빛진", "타임스탬프\t\t순장선택\t고주명13\t권지영13\t김요셉13\t박다혜13\t성소정15\t손태현12\t송의정14\t윤균호13\t한신성13\t황주영14\t강우림16\t권준한17\t김원우16\t김은혜16\t박민영16\t박범서18\t오혜영16\t이헌제17\t장한나18\t전현민17\t차서현18\t최효주18\t김명철13\t박정민14\t유수진11\t이서경15\t이성희16\t이주헌14\t이하은13\t임지호12\t강영수18\t장혜원15\t최완호12\t황예찬15\t이유정15\t김동현15\t김미지18\t김민주18\t김상혁16\t김상훈13\t김종현13\t봉한결14\t유주연14\t이수빈13\t조나연18\t최윤지17\t최주희14\t강영수18\t구건형14\t김범수12\t김소현13\t박소영12\t박신영13\t서현호16\t이다은12\t정다운12\t최낙현15\t최재호14\t하은혜12\t한승희12\t\t\t\t\t\t\t\t\t\t\t\t목사님/대표순장님께\t순모임 장소, 시간, 분위기\t순 특별 기도제목\t[선택] 특이사항 (경조사 / 출타자 등)\t[필수] 순모임 날짜");
//    }
//    private static final Map<String, String> TOWN_SEED_MAP = new HashMap<>();
//    static {
//        TOWN_SEED_MAP.put("동아리", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1CFg22k-huGZaJK4O3rUkBbHJj1VyatFfAQw1qXPfoEM/edit\", \"'1_박동은_대표순장'!${CELL}\")");
//        TOWN_SEED_MAP.put("봉봉", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1CFg22k-huGZaJK4O3rUkBbHJj1VyatFfAQw1qXPfoEM/edit\", \"'2_봉한울_대표순장'!${CELL}\")");
//        TOWN_SEED_MAP.put("지안", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1CFg22k-huGZaJK4O3rUkBbHJj1VyatFfAQw1qXPfoEM/edit\", \"'3_최지아_대표순장'!${CELL}\")");
//
//        TOWN_SEED_MAP.put("세", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1IZig0EFSA4iDzt_9Cac1Gv2atePZ81bfM8A4hwOcIDM/edit\", \"'1_김세한대표순장'!${CELL}\")");
//        TOWN_SEED_MAP.put("사랑", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1IZig0EFSA4iDzt_9Cac1Gv2atePZ81bfM8A4hwOcIDM/edit\", \"'2_이사랑대표순장'!${CELL}\")");
//        TOWN_SEED_MAP.put("삼", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1IZig0EFSA4iDzt_9Cac1Gv2atePZ81bfM8A4hwOcIDM/edit\", \"'3_전세진대표순장'!${CELL}\")");
//        TOWN_SEED_MAP.put("빛진", "IMPORTRANGE(\"https://docs.google.com/spreadsheets/d/1IZig0EFSA4iDzt_9Cac1Gv2atePZ81bfM8A4hwOcIDM/edit\", \"'4_조인겸대표순장'!${CELL}\")");
//    }
//


}

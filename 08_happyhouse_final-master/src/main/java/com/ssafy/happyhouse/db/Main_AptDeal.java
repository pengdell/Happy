package com.ssafy.happyhouse.db;

import com.ssafy.happyhouse.dto.AptDealDto;
import com.ssafy.happyhouse.dao.AptDeal;

import java.net.URLEncoder;
import java.sql.SQLException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Component
public class Main_AptDeal {
	
	@Autowired
	static AptDeal dao;

	static String[][] codes = { { "종로구", "11110" }, { "중구", "11140" }, { "용산구", "11170" }, { "성동구", "11200" },
			{ "광진구", "11215" }, { "동대문구", "11230" }, { "중랑구", "11260" }, { "성북구", "11290" }, { "강북구", "11305" },
			{ "도봉구", "11320" }, { "노원구", "11350" }, { "은평구", "11380" }, { "서대문구", "11410" }, { "마포구", "11440" },
			{ "양천구", "11470" }, { "강서구", "11500" }, { "구로구", "11530" }, { "금천구", "11545" }, { "영등포구", "11560" },
			{ "동작구", "11590" }, { "관악구", "11620" }, { "서초구", "11650" }, { "강남구", "11680" }, { "송파구", "11710" },
			{ "강동구", "11740" }, { "서구", "26140" }, { "동구", "26170" }, { "영도구", "26200" }, { "부산진구", "26230" },
			{ "동래구", "26260" }, { "남구", "26290" }, { "북구", "26320" }, { "해운대구", "26350" }, { "사하구", "26380" },
			{ "금정구", "26410" }, { "연제구", "26470" }, { "수영구", "26500" }, { "사상구", "26530" }, { "기장군", "26710" },
			{ "수성구", "27260" }, { "달서구", "27290" }, { "달성군", "27710" }, { "중구영종출장", "28115" }, { "중구용유출장", "28116" },
			{ "미추홀구", "28177" }, { "연수구", "28185" }, { "남동구", "28200" }, { "부평구", "28237" }, { "계양구", "28245" },
			{ "서구검단출장", "28265" }, { "강화군", "28710" }, { "옹진군", "28720" }, { "광산구", "29200" }, { "유성구", "30200" },
			{ "대덕구", "30230" }, { "울주군", "31710" }, { "수원시", "41110" }, { "수원시 장안구", "41111" }, { "수원시 권선구", "41113" },
			{ "수원시 팔달구", "41115" }, { "수원시 영통구", "41117" }, { "성남시", "41130" }, { "성남시 수정구", "41131" },
			{ "성남시 중원구", "41133" }, { "성남시 분당구", "41135" }, { "의정부시", "41150" }, { "안양시", "41170" },
			{ "안양시 만안구", " 41171" }, { "안양시 동안구", "41173" }, { "부천시", "41190" }, { "광명시", "41210" }, { "평택시", "41220" },
			{ "동두천시", "41250" }, { "안산시", "41270" }, { "안산시 상록구", "41271" }, { "안산시 단원구", "41273" }, { "고양시", "41280" },
			{ "고양시 덕양구", "41281" }, { "고양시 일산동구", "41285" }, { "고양시 일산서구", "41287" }, { "과천시", "41290" },
			{ "구리시", "41310" }, { "남양주시", "41360" }, { "오산시", "41370" }, { "시흥시", "41390" }, { "군포시", "41410" },
			{ "의왕시", "41430" }, { "하남시", "41450" }, { "용인시", "41460" }, { "용인시 처인구", "41461" }, { "용인시 기흥구", "41463" },
			{ "용인시 수지구", "41465" }, { "파주시", "41480" }, { "이천시", "41500" }, { "안성시", "41550" }, { "김포시", "41570" },
			{ "화성시", "41590" }, { "광주시", "41610" }, { "양주시", "41630" }, { "포천시", "41650" }, { "여주시", "41670" },
			{ "연천군", "41800" }, { "가평군", "41820" }, { "양평군", "41830" }, { "춘천시", "42110" }, { "원주시", "42130" },
			{ "강릉시", "42150" }, { "동해시", "42170" }, { "태백시", "42190" }, { "속초시", "42210" }, { "삼척시", "42230" },
			{ "홍천군", "42720" }, { "횡성군", "42730" }, { "영월군", "42750" }, { "평창군", "42760" }, { "정선군", "42770" },
			{ "철원군", "42780" }, { "화천군", "42790" }, { "양구군", "42800" }, { "인제군", "42810" }, { "고성군", "42820" },
			{ "양양군", "42830" }, { "청주시", "43110" }, { "청주시 상당구", "43111" }, { "청주시 서원구", "43112" },
			{ "청주시 흥덕구", "43113" }, { "청주시 청원구", "43114" }, { "충주시", "43130" }, { "제천시", "43150" }, { "보은군", "43720" },
			{ "옥천군", "43730" }, { "영동군", "43740" }, { "증평군", "43745" }, { "진천군", "43750" }, { "괴산군", "43760" },
			{ "음성군", "43770" }, { "단양군", "43800" }, { "천안시", "44130" }, { "천안시 동남구", "44131" }, { "천안시 서북구", "44133" },
			{ "공주시", "44150" }, { "보령시", "44180" }, { "아산시", "44200" }, { "서산시", "44210" }, { "논산시", "44230" },
			{ "계룡시", "44250" }, { "당진시", "44270" }, { "금산군", "44710" }, { "부여군", "44760" }, { "서천군", "44770" },
			{ "청양군", "44790" }, { "홍성군", "44800" }, { "예산군", "44810" }, { "태안군", "44825" }, { "전주시", "45110" },
			{ "전주시 완산구", "45111" }, { "전주시 덕진구", "45113" }, { "전주시효자출", "45118" }, { "군산시", "45130" },
			{ "익산시", "45140" }, { "익산시함열출", "45145" }, { "정읍시", "45180" }, { "남원시", "45190" }, { "김제시", "45210" },
			{ "완주군", "45710" }, { "진안군", "45720" }, { "무주군", "45730" }, { "장수군", "45740" }, { "임실군", "45750" },
			{ "순창군", "45770" }, { "고창군", "45790" }, { "부안군", "45800" }, { "목포시", "46110" }, { "여수시", "46130" },
			{ "순천시", "46150" }, { "나주시", "46170" }, { "광양시", "46230" }, { "담양군", "46710" }, { "곡성군", "46720" },
			{ "구례군", "46730" }, { "고흥군", "46770" }, { "보성군", "46780" }, { "화순군", "46790" }, { "장흥군", "46800" },
			{ "강진군", "46810" }, { "해남군", "46820" }, { "영암군", "46830" }, { "무안군", "46840" }, { "함평군", "46860" },
			{ "영광군", "46870" }, { "장성군", "46880" }, { "완도군", "46890" }, { "진도군", "46900" }, { "신안군", "46910" },
			{ "포항시", "47110" }, { "포항시 남구", " 47111" }, { "포항시 북구", "47113" }, { "경주시", "47130" }, { "김천시", "47150" },
			{ "안동시", "47170" }, { "구미시", "47190" }, { "영주시", "47210" }, { "영천시", "47230" }, { "상주시", "47250" },
			{ "문경시", "47280" }, { "경산시", "47290" }, { "군위군", "47720" }, { "의성군", "47730" }, { "청송군", "47750" },
			{ "영양군", "47760" }, { "영덕군", "47770" }, { "청도군", "47820" }, { "고령군", "47830" }, { "성주군", "47840" },
			{ "칠곡군", "47850" }, { "예천군", "47900" }, { "봉화군", "47920" }, { "울진군", "47930" }, { "울릉군", "47940" },
			{ "창원시", "48120" }, { "창원시 의창구", "48121" }, { "창원시 성산구", "48123" }, { "창원시 마산합포구", "48125" },
			{ "창원시 마산회원구", "48127" }, { "창원시 진해구", "48129" }, { "진주시", "48170" }, { "통영시", "48220" },
			{ "사천시", "48240" }, { "사천남양출장", "48245" }, { "김해시", "48250" }, { "밀양시", "48270" }, { "거제시", "48310" },
			{ "양산시", "48330" }, { "의령군", "48720" }, { "함안군", "48730" }, { "창녕군", "48740" }, { "남해군", "48840" },
			{ "하동군", "48850" }, { "산청군", "48860" }, { "함양군", "48870" }, { "거창군", "48880" }, { "합천군", "48890" },
			{ "제주시", "50110" }, { "서귀포시", "50130" } };

	public static String getTagValue(String tag, Element eElement) {
		// 결과를 저장할 result 변수 선언
		String result = "";

		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		
		result = nlList.item(0).getTextContent();

		return result;
	}

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, SQLException {
		
		for (int i = 0; i < codes.length; i++) {
			String key = "XjvtWcdVPCK83W0FrKNeJhYCKBqvkqIvikqKT3dcrB3bQoupKdgyMav1j9%2FoK%2FUwGNN8jeiQimO4M%2BgJ4OBYDQ%3D%3D";
			// String code = "11140";
			String code = codes[i][1]; //모든 지역 저장
			String day = "202203";
			//
			System.out.println("code " + code);

			StringBuilder urlBuilder = new StringBuilder(
					"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); // 서비스키
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append(
					"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /* 지역코드 */
			urlBuilder.append(
					"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(day, "UTF-8")); /* 계약월 */

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlBuilder.toString());

			// 제일 첫번째 태그
			doc.getDocumentElement().normalize();

			// 파싱할 tag
			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				Element eElement = (Element) nNode;

				int price_deal = Integer.parseInt(getTagValue("거래금액", eElement).replace(",", "").trim());
				int buildYear;
				try {
					buildYear = Integer.parseInt(getTagValue("건축년도", eElement).trim());
				} catch (Exception e) {
					e.printStackTrace();
					buildYear = 0;
				}
				//code=42210 day=202202 NPE
				String dong = getTagValue("법정동", eElement).trim();
				String aptName = getTagValue("아파트", eElement).trim();
				int deal_year = Integer.parseInt(getTagValue("년", eElement).trim());
				int deal_month = Integer.parseInt(getTagValue("월", eElement).trim());
				int deal_day = Integer.parseInt(getTagValue("일", eElement).trim());
				String exclusivePrivateArea = getTagValue("전용면적", eElement).trim();
				String jiNum;
				try {
					jiNum = getTagValue("지번", eElement).trim();
				} catch (Exception e) {
					e.printStackTrace();
					jiNum = "";
				}
				String areaCode = getTagValue("지역코드", eElement).trim();
				int floor = Integer.parseInt(getTagValue("층", eElement).trim());
				
				dao.insert(new AptDealDto(price_deal, buildYear, deal_year, dong, aptName, deal_month, deal_day,
						exclusivePrivateArea, jiNum, areaCode, floor));

			}//for temp
		}// for i

	}// main

}

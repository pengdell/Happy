package com.ssafy.happyhouse.naver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ssafy.happyhouse.dto.NewsDto;


public class NewsApi {

	public static void main(String[] args) {
		String clientId = "xUj2R4XaHGoeB7cBkH62"; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = "tV2JKyNu7t"; // 애플리케이션 클라이언트 시크릿값"

		String keyword = "아파트";
		String text;
		try {
			text = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text
				 + "&display=10&start=1&sort=date"; // json 결과
		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
		// // xml 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders); //json 출력
		//System.out.println(responseBody);
			
		//responseBody json 배열 파싱
		JSONObject jObject = new JSONObject(responseBody);
		JSONArray jArray = jObject.getJSONArray("items");
		
		List<NewsDto> list = new ArrayList<NewsDto>();
		for(int i=0; i<jArray.length(); i++) {
			JSONObject obj = jArray.getJSONObject(i);
			String title = obj.getString("title");
			String link = obj.getString("link");
			String description = obj.getString("description");
			String pubDate = obj.getString("pubDate");

			
			NewsDto news = new NewsDto(title, description, pubDate, link);
			list.add(news);
		}//for i
		
		System.out.println(list);
	}// main

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}// get

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}// HttpURLConnection

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line+"\n");
			}
			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}//readBody

}// END

package kr.ac.hansung.cse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.model.Content;

@Service
public class YoutubeService {
	private WebDriver driver;
	
	public YoutubeService(){
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
	}
	
	
	public List<Content> searchItems(String searchText) {
		driver.get("https://www.youtube.com/results?search_query="+searchText);
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		
		Elements contents = doc.select("ytd-video-renderer");
		String titlestring;
		String desstring;
		String thumbstring;
		String hrefstring;
		List<Content> yList = new ArrayList<Content>();

		for(Element e : contents){
			titlestring = e.getElementById("video-title").text(); //title 가져오기
			desstring = e.getElementById("description-text").text(); //설명 가져오기
			hrefstring = e.getElementById("thumbnail").attr("href"); //실 동영상 주소 가져오기
			thumbstring = e.getElementsByTag("img").attr("src").toString(); // 이미지 주소 가져오기
			yList.add(new Content(titlestring, desstring,thumbstring,hrefstring));
			System.out.println(titlestring +"\n"+ desstring + "\n" + thumbstring + "\n" + hrefstring);
		}
		
		return yList;
	}
	
	
}

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
public class NaverService {
private WebDriver driver;
	
	public NaverService(){
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
	}

	public List<Content> searchItmes(String searchItem) {
		driver.get("https://search.naver.com/search.naver?where=news&sm=tab_jum&query="+searchItem);
		String html = driver.getPageSource();
		Document doc = Jsoup.parse(html);
		
		Elements contents = doc.select("li[id^=sp_nw]");
		String titlestring;
		String desstring;
		String thumbstring;
		String hrefstring;
		List<Content> nList = new ArrayList<Content>();

		for(Element e : contents){
			titlestring = e.getElementsByClass("_sp_each_title").attr("title"); //title 가져오기
			desstring = e.getElementsByTag("dd").get(1).text(); //설명 가져오기
			hrefstring = e.getElementsByClass("sp_thmb thmb80").attr("href"); //실 동영상 주소 가져오기
			thumbstring = e.getElementsByTag("img").attr("src"); // 이미지 주소 가져오기
			nList.add(new Content(titlestring, desstring,thumbstring,hrefstring));
			System.out.println(titlestring +"\n"+ desstring + "\n" + thumbstring + "\n" + hrefstring);
		}
		
		return nList;
	}

	
}

package model;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//【１時間半→９０分に変換するメソッド ＜h時間＊６０＋ｍ分＝分】＞
//【９０分を→１時間半に変換するメソッド ＜m分/60=h時間  h時間＋%（m分）＝時間分】＞


public class TimeConverter {
int h,m,hans,mans;

	public static void timeConverter(String[] args){
	Scanner scn = new Scanner(System.in);
		  //h時間をm分に変換
		  System.out.println(TimeUnit.HOURS.toMinutes(h));

		//m分をh時間 ＋ m分に変換
		  System.out.println(TimeUnit.MINUTES.toHours(m));

	  }

}
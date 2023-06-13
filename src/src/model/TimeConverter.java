package model;
import java.util.concurrent.TimeUnit;


//【１時間半→９０分に変換するメソッド ＜h時間＊６０＋ｍ分＝分】＞
public class TimeConverter {
	public static void timeConverter(String[] args){
int hours;
long minutes;
		  //h時間をm分に変換
	    hours = 2; // 変換する時間（単位：時間
	    minutes = TimeUnit.HOURS.toMinutes(hours); // 時間を分に変換
		System.out.println( minutes + " 分");
	}


	//【９０分を→１時間半に変換するメソッド ＜m分/60=h時間  h時間＋%（m分）＝時間分】＞
    public static void main(String[] args) {
 long minutes,hours,remainingMinutes;
         minutes = 135; // 変換する分（単位：分）
         hours = TimeUnit.MINUTES.toHours(minutes); // 分を時間に変換
         remainingMinutes = minutes % 60; // 分の残りを計算

        System.out.println(hours + " 時間 " + remainingMinutes + " 分");
    }
}





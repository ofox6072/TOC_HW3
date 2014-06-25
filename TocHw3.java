/***
TocHW3
林衡
F74006218
***/
import java.io.*;//File ,FileReader
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.*;



public class TocHw3 {
	
		 public static void main(String[] argv) throws Exception,IOException,FileNotFoundException{  //throws JSONException
			 
			 try{
				 if(argv.length == 4)
				 {
					 String block,road ;//區,路
					 int year,i = 0,count = 0,sum = 0;//year 年，i第幾個JSONArray arr,count有幾個條件符合的個數,sum總價元
					 
					 
					 URL pUrl = new URL(argv[0]);//取得argv URL的網址
					 BufferedReader iread = new BufferedReader(new InputStreamReader(pUrl.openStream(), "UTF-8"));//iread 讀URL
					 JSONArray arr = new JSONArray(new JSONTokener(iread));//  把url資料放入JSONArray arr
					
					 
					 boolean flag = true ;
					 while (flag == true)
					 {
						 try{
							 JSONObject ob = arr.getJSONObject(i);
							 i++;
							 block = ob.getString("鄉鎮市區");
						
							 
							 Pattern pattern = Pattern.compile(argv[1]);
							 Matcher matcher = pattern.matcher(block);
							 if(matcher.find())//判斷"鄉鎮市區"是否一樣
							 {
								 road= ob.getString("土地區段位置或建物區門牌");
								 pattern = Pattern.compile(argv[2]);
								 matcher = pattern.matcher(".*"+road+".*");
								 if(matcher.find())//判斷路是否一樣
								 {
									 year = ob.getInt("交易年月");
									 int check = year-(Integer.valueOf(argv[3])*100);//現在obj年分-argv上的年份*100
									 int price = ob.getInt("總價元");
									 if(check>= 0)//判斷是否是在argv上的年份之後
									 {
										 
										 System.out.print(argv[1]);
										 System.out.print("\t" +  road);
										 System.out.printf("\t %d" ,year);
										 System.out.printf("\t %d\n" , price);
										 sum += price;//總價元+=總價元
										 count++;//條件符合的個數++
									 }
								 }
							}	
						 }
						 catch(JSONException e)
						 	{
							 flag = false;
						 	}
					}//end while
					 
					 int avg_price = sum / count;//平均總價元
					 System.out.println("Output:");
					 System.out.printf("%d \n" , avg_price);
					 
					 
				}//end if(argv.length == 4)
				 else//argv 輸入錯誤
				 {
					 System.out.println("Unexpected arguments !");
				 }
			}//end try
			 
			catch(IOException e)
			{
				System.out.println("File Not Found!");
			}
			
		 }
		 
}
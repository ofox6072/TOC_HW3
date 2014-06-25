/***
TocHW3
�L��
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
					 String block,road ;//��,��
					 int year,i = 0,count = 0,sum = 0;//year �~�Ai�ĴX��JSONArray arr,count���X�ӱ���ŦX���Ӽ�,sum�`����
					 
					 
					 URL pUrl = new URL(argv[0]);//���oargv URL�����}
					 BufferedReader iread = new BufferedReader(new InputStreamReader(pUrl.openStream(), "UTF-8"));//iread ŪURL
					 JSONArray arr = new JSONArray(new JSONTokener(iread));//  ��url��Ʃ�JJSONArray arr
					
					 
					 boolean flag = true ;
					 while (flag == true)
					 {
						 try{
							 JSONObject ob = arr.getJSONObject(i);
							 i++;
							 block = ob.getString("�m����");
						
							 
							 Pattern pattern = Pattern.compile(argv[1]);
							 Matcher matcher = pattern.matcher(block);
							 if(matcher.find())//�P�_"�m����"�O�_�@��
							 {
								 road= ob.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
								 pattern = Pattern.compile(argv[2]);
								 matcher = pattern.matcher(".*"+road+".*");
								 if(matcher.find())//�P�_���O�_�@��
								 {
									 year = ob.getInt("����~��");
									 int check = year-(Integer.valueOf(argv[3])*100);//�{�bobj�~��-argv�W���~��*100
									 int price = ob.getInt("�`����");
									 if(check>= 0)//�P�_�O�_�O�bargv�W���~������
									 {
										 
										 System.out.print(argv[1]);
										 System.out.print("\t" +  road);
										 System.out.printf("\t %d" ,year);
										 System.out.printf("\t %d\n" , price);
										 sum += price;//�`����+=�`����
										 count++;//����ŦX���Ӽ�++
									 }
								 }
							}	
						 }
						 catch(JSONException e)
						 	{
							 flag = false;
						 	}
					}//end while
					 
					 int avg_price = sum / count;//�����`����
					 System.out.println("Output:");
					 System.out.printf("%d \n" , avg_price);
					 
					 
				}//end if(argv.length == 4)
				 else//argv ��J���~
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
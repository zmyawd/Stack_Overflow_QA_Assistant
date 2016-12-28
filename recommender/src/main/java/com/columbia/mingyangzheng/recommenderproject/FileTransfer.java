package com.columbia.mingyangzheng.recommenderproject;


import java.io.*;
 class FileTransfer 
{

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader bufr = new BufferedReader(new FileReader("data/Tags2.csv"));
		BufferedWriter bufw = new BufferedWriter(new FileWriter("data/Tags3.csv"));
		BufferedWriter bufw2 = new BufferedWriter(new FileWriter("data/Tags4.csv"));
		
		String line = null;
		String[] strarr = null;
		String tmp = null;
		
		while((line=bufr.readLine())!=null)
		{
			strarr = line.split(",");
			tmp = ""+strarr[0].hashCode();
			
			bufw.write(tmp+",");
			bufw.write(strarr[1]+",");
			bufw.write(strarr[2]);
			
			bufw.newLine();
			bufw.flush();

			bufw2.write(strarr[0]+",");
			bufw2.write(strarr[1]+",");
			bufw2.write(strarr[2]+",");
			bufw2.write(tmp);
			bufw2.newLine();
			bufw2.flush();
			
			
		}
		
		bufr.close();
		bufw.close();
		bufw2.close();
	}

}

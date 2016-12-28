
import java.io.*;
public class puretext 
{
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader bufr = new BufferedReader(new FileReader("/tmp/puretext-in"));
		BufferedWriter bufw = null;
		String line = null;

		bufw = new BufferedWriter(new FileWriter("/tmp/puretext-out"));
		
		while((line=bufr.readLine())!=null)
		{
			line = line.replaceAll( "[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×  ]" , " ");
			line = line.trim().replaceAll("\\s+", " ");
			line = line.toLowerCase();
			bufw.write(line);
			bufw.newLine();
			bufw.flush();
		}
		
		bufw.close();
		bufr.close();
	}

}

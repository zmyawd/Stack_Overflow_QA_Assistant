import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class seqcsv
{

	public static void main(String[] args) throws IOException
	{
		BufferedReader bufr = new BufferedReader(new FileReader("/tmp/seqcsv-in"));
		String line;

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		Path outputPath = new Path("/tmp/seqcsv-out");

		Text key = new Text();
		Text value = new Text();

		SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf, outputPath, key.getClass(), value.getClass());

		while((line = bufr.readLine()) != null)
		{
			String[] c = line.split(",");
			if (c.length != 2) { continue; }

			key.set(c[0]);
			value.set(c[1]);

			writer.append(key, value);
		}
		writer.close();
	}
}


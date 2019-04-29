import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class VcfReader{

	public String getName(String s) {
		String sUtf8 = null;
		byte b1[] = new byte[s.length()/2];
		int j=0;

		if (!s.contains("="))
			return s;

		s = s.replaceAll("=", "");
		s = s.replaceAll(";", "");
		for (int i=0; i<=s.length()-2; i+=2) {
			b1[j] = (byte) java.lang.Integer.parseInt(s.substring(i,i+2), 16);
			j++;
		}
		try {
			sUtf8 = new String(b1, "utf8");
			//System.out.println(sUtf8);
			return sUtf8;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); 
		}
		return s;
	}

	public String getTel(String s) {
		//System.out.println(s);
		return s;
	}

	public String getEmail(String s) {
		//System.out.println(s);
		return s;
	}

	@SuppressWarnings("resource")
    public void read(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String data = null;
		String line = null;
		String info = "";
		while(true){
			line = br.readLine();//一次读入一行，直到读入null为文件结束)
			if (line == null)
				break;

			if (line.startsWith("BEGIN:VCARD")) {
				if (info.length() != 0) {
					System.out.println(info);
					info = "";
				}
			}
			else if (line.startsWith("FN;") || line.startsWith("FN:")) {
				data = line.substring(line.indexOf(':')+1);
				while (line.charAt(line.length()-1) == '=') //multi-lines
				{
					line = br.readLine();
					data += line;
				}
				info = getName(data);
			}
			else if (line.startsWith("TEL;")) {
				data = line.substring(line.indexOf(':')+1);
				info += " " + getTel(data);
			}
			else if (line.startsWith("EMAIL;")) {
				data = line.substring(line.indexOf(':')+1);
				info += " " + getEmail(data);
			}
			else {
			}
		}
		if (info.length() != 0)
			System.out.println(info);
	}

}

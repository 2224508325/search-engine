package tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexingTokenizer implements Tokenizer{
	//ArrayList<String> words;
	//public IndexingTokenizer(String paper) {
	//	words = tokenize(paper);
		
	//}
	@Override
	public ArrayList<String> tokenize(String s) {
		// TODO Auto-generated method stub
		ArrayList<String> ret = new ArrayList<String>();
		Pattern p = Pattern.compile("\\w+[\\w-]*");
		Matcher m = p.matcher(s);
		while (m.find()) {
			ret.add(m.group().toLowerCase());
		}
		return ret;

}
}

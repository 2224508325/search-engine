package score;

import index.Index;

public class TFIDFScoringFun implements TermScoringFun{
	Index _i;
	//int DF=1;
	@Override
	public void init(Index s) {
		// TODO Auto-generated method stub
		_i=s;
		//DF=1;
		
		
	}

	@Override
	public double scoreToken(String term, int freq) {
		// TODO Auto-generated method stub

		
		double result = (1+Math.log10((double)freq))*Math.log10((double)_i.getDocSource().getNumDocs()/(double)_i.getDocumentFreq(term));
		//double result = (double)_i.getDocumentFreq(term);
		return result;
	}

}

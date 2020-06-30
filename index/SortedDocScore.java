package index;

import java.util.TreeSet;

public class SortedDocScore extends DocScore implements Comparable{
	TreeSet<DocScore> _sort;
	public SortedDocScore(double score, int docId,String content) {
		super(score, docId,content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		double var = 0.0;
		int compare=0;
    	if (o instanceof DocScore) {
    		DocScore d = (DocScore)o;
    		var=this.getScore() - d.getScore();
    		if(var>0) {
        		compare = -1;
        	}
        	else if(var<0) {
        		compare = 1;
        	}
        	else if(var==0){
        		return this.getContent().compareTo(d.getContent());
        	}
    	}
    	return compare;
    	
	}

}

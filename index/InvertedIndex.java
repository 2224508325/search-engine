package index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

import io.DocSource;
import score.TermScoringFun;
import tokenizer.Tokenizer;

public class InvertedIndex extends Index{
	//HashMap<Integer,Integer> id_freq;
	HashMap<String,HashMap<Integer,Integer>> _index = new HashMap<String,HashMap<Integer,Integer>>();
	HashMap<String,Integer> _docFreq = new HashMap<String,Integer>();
	public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
		super(doc_source, tokenizer, scoring);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildIndex() {
		// TODO Auto-generated method stub
		 for(int i = 0; i < _docSource.getNumDocs(); i++){
	            ArrayList<String> terms = _tokenizer.tokenize(_docSource.getDoc(i));
	            for(String term: terms){
	                if(!_index.containsKey(term)){ 
	                	_index.put(term, new HashMap< Integer, Integer>());
	                    _index.get(term).put(i, 1); 
	                    _docFreq.put(term, 0); 
	                    
	                }
	                else if(_index.containsKey(term)){
	                	if (_index.get(term).containsKey(i)){ 
	                        _index.get(term).put(i, 1 + _index.get(term).get(i));
	                    }
	                    else{
	                        _index.get(term).put(i, 1); 
	                    }
	                }
	            }
	        }
	        for(String t: _docFreq.keySet()){
	            _docFreq.put(t, _index.get(t).size());
	        }
	     
	    }
		

	@Override
	public Integer getDocumentFreq(String term) {
		// TODO Auto-generated method stub
		return _docFreq.get(term);
	}

	@Override
	public ArrayList<DocScore> getSortedSearchResults(String query) {
		// TODO Auto-generated method stub
		ArrayList<String> term = new ArrayList<String>();
		term = new ArrayList<String> (_tokenizer.tokenize(query));
		HashMap<Integer,Double> docScore = new HashMap<Integer,Double>();
		TreeSet<SortedDocScore> sortedDoc = new TreeSet<SortedDocScore>();
		for(String i: term) {
			if(_index.containsKey(i)) {
				for(int j : _index.get(i).keySet()) {
					if(!docScore.containsKey(j)) {
						docScore.put(j, _scoring.scoreToken(i, _index.get(i).get(j)));
					}
					else {
						docScore.replace(j, docScore.get(j)+_scoring.scoreToken(i, _index.get(i).get(j)));
					}
				}
			}
		}
		for(int k: docScore.keySet()) {
			sortedDoc.add(new SortedDocScore(docScore.get(k),k,_docSource.getDoc(k)));
		}
		//System.out.println(term);
		//System.out.println(term);
		ArrayList<DocScore> fin = new ArrayList<DocScore>(sortedDoc);
		return fin;
	}

}

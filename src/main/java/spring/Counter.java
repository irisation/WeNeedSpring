package spring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;


public class Counter
{

	enum Prepositions
	{
		on, in, at, since, ago, before, to, past, till, until, by, under, below, over, above, across, through, into, towards, onto, from, off, about, of
	}


	String filePath;

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}



	public WordsContainer parse() throws IOException
	{
		WordsContainer wc = new WordsContainer(filePath);
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String line = in.readLine();
		String[] words;
        while (line != null) {
            if (!line.isEmpty()) {
                words = line.split("[\\d\\W]+");
                for (int i = 0; i < words.length; i++) {
                    String currentWord = words[i].toLowerCase();
                    if (wc.allWords.containsKey(currentWord)) {
                        wc.allWords.put(currentWord, wc.allWords.get(currentWord) + 1);
                    } else {
                        wc.allWords.put(currentWord, 1);
                    }
                }
			}
			line = in.readLine();
		}
		wc.setTopTen((HashMap<String, Integer>) sortByValue(wc.allWords));
		wc.setWordsCount(wc.allWords.size());
		return wc;


	}

	static Map sortByValue(Map map)
	{
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator()
		{
			public int compare(Object o1, Object o2)
			{
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});
		Map result = new LinkedHashMap();
		int i = 0;
		int j = 0;
		while (j < 10 && i < list.size())
		{
			Map.Entry entry = (Map.Entry) list.get(i);
			if (!EnumUtils.isValidEnum(Prepositions.class, (String) entry.getKey()))
			{
				result.put(entry.getKey(), entry.getValue());
				j++;
			}
			i++;
		}
		return result;
	}
}

package spring;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


@Service
public class CountService
{

	private String folderPath;
	private String storageType;
	private CountersFactory countersFactory;

	public String getStorageType()
	{
		return storageType;
	}

	public void setStorageType(String storageType)
	{
		this.storageType = storageType;
	}



	public String getFolderPath()
	{
		return folderPath;
	}

	public void setFolderPath(String folderPath)
	{
		this.folderPath = folderPath;
	}

	ArrayList<WordsContainer> start() throws IOException
	{
		File folder = new File(this.folderPath);
		ArrayList<WordsContainer> wordsContainers = new ArrayList<>();
		for (final File fileEntry : folder.listFiles())
		{
			if (!fileEntry.isDirectory())
			{
				Counter counter = countersFactory.getCounter(getFolderPath() + fileEntry.getName());
				wordsContainers.add(counter.parse());
			}
		}
		return wordsContainers;
	}

	public void setCountersFactory(final CountersFactory countersFactory)
	{
		this.countersFactory = countersFactory;
	}
}

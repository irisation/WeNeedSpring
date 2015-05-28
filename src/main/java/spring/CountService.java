package spring;

import org.codehaus.jackson.map.SerializationConfig;
import org.json.*;
//import org.springframework.oxm.Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;//org.springframework.oxm.Marshaller;

import org.springframework.oxm.Unmarshaller;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.*;

public class CountService
{

	private String folderPath;
	private String storageType;

	private Jaxb2Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public void setMarshaller(Jaxb2Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

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

	public void saveInfoIntoXML(Containers arrayList) throws IOException {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream("D:/outcome.xml");
			this.marshaller.marshal(arrayList, new StreamResult(os));

		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public void saveInfoIntoJSON(Containers arrayList) {

		File file = new File("D:/outcome.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			mapper.writeValue(file, arrayList);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	ArrayList<WordsContainer> start() throws IOException, JSONException {
		File folder = new File(this.folderPath);
		ArrayList<WordsContainer> wordsContainers = new ArrayList<>();
		for (final File fileEntry : folder.listFiles())
		{
			if (fileEntry.isFile())
			{
				Counter counter = countersFactory.getCounter();
				counter.setFilePath(getFolderPath() + fileEntry.getName());
				wordsContainers.add(counter.parse());
			}
		}
		writeFile(wordsContainers);
		return wordsContainers;
	}

	public void writeFile(ArrayList<WordsContainer> arrayList) throws IOException, JSONException {
		switch (storageType) {
			case "xml": {
				saveInfoIntoXML(new Containers(arrayList));
				break;
			}
			case "json": {
				saveInfoIntoJSON(new Containers(arrayList));
				break;
			}
			default:
				System.out.println("File format is wrong!");

		}

	}

	public void setCountersFactory(final CountersFactory countersFactory)
	{
		this.countersFactory = countersFactory;
	}
}

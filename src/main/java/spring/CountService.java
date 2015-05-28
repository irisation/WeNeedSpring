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

	public void saveSettings(WordsContainer arrayList) throws IOException {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream("D:/customer.xml");

			this.marshaller.marshal(arrayList, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
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

				saveSettings(arrayList.get(0));
//					DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
//					DocumentBuilder build = dFact.newDocumentBuilder();
//					Document doc = build.newDocument();
//
//					Element root = doc.createElement("FilesInfo");
//					doc.appendChild(root);
//
//					Element details = doc.createElement("Details");
//					root.appendChild(details);
//
//
//					for (int i = 0; i < arrayList.size(); i++) {
//
//						Element file = doc.createElement("file");
//						details.appendChild(file);
//
//						Element fileName = doc.createElement("fileName");
//						fileName.appendChild(doc.createTextNode(arrayList.get(i).getFileName()));
//						file.appendChild(fileName);
//
//						Element count = doc.createElement("countOfWords");
//						count.appendChild(doc.createTextNode(String.valueOf(arrayList.get(i).getWordsCount())));
//						file.appendChild(count);
//
//
//						Element topTen = doc.createElement("topTen");
//
//
//						List list = new LinkedList(arrayList.get(i).topTen.entrySet());
//						for (Object entry : list) {
//							Element word = doc.createElement("word");
//							word.setAttribute("quantity", String.valueOf(((Map.Entry) entry).getValue()));
//							word.appendChild(doc.createTextNode((String) ((Map.Entry) entry).getKey()));
//
//							topTen.appendChild(word);
//							file.appendChild(topTen);
//						}
//
//					}
//
//					// Save the document to the disk file
//					TransformerFactory tranFactory = TransformerFactory.newInstance();
//					Transformer aTransformer = tranFactory.newTransformer();
//
//					// format the XML nicely
//					aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
//
//					aTransformer.setOutputProperty(
//							"{http://xml.apache.org/xslt}indent-amount", "4");
//					aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//
//					DOMSource source = new DOMSource(doc);
//					try {
//						FileWriter fos = new FileWriter("D:/temp.xml");
//						StreamResult result = new StreamResult(fos);
//						aTransformer.transform(source, result);


				break;
			}
			case "json": {
				File file = new File("D:/temp.json");
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

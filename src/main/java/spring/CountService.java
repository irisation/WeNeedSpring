package spring;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
				Counter counter = countersFactory.getCounter();
				counter.setFilePath(getFolderPath() + fileEntry.getName());
				wordsContainers.add(counter.parse());
			}
		}
		writeFile(wordsContainers);
		return wordsContainers;
	}

	public void writeFile(ArrayList<WordsContainer> arrayList) {
		switch (storageType) {
			case "xml": {
				try {

					DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
					DocumentBuilder build = dFact.newDocumentBuilder();
					Document doc = build.newDocument();

					Element root = doc.createElement("FilesInfo");
					doc.appendChild(root);

					Element details = doc.createElement("Details");
					root.appendChild(details);


					for (int i = 0; i < arrayList.size(); i++) {

						Element file = doc.createElement("file");
						details.appendChild(file);

						Element fileName = doc.createElement("fileName");
						fileName.appendChild(doc.createTextNode(arrayList.get(i).getFileName()));
						file.appendChild(fileName);

						Element count = doc.createElement("countOfWords");
						count.appendChild(doc.createTextNode(String.valueOf(arrayList.get(i).getWordsCount())));
						file.appendChild(count);


						Element topTen = doc.createElement("topTen");


						List list = new LinkedList(arrayList.get(i).topTen.entrySet());
						for (Object entry : list) {
							Element word = doc.createElement("word");

							Element spelling = doc.createElement("spelling");
							spelling.appendChild(doc.createTextNode((String) ((Map.Entry) entry).getKey()));
							word.appendChild(spelling);

							Element quantity = doc.createElement("quantity");
							quantity.appendChild(doc.createTextNode(String.valueOf(((Map.Entry) entry).getValue())));
							word.appendChild(quantity);

							topTen.appendChild(word);
							file.appendChild(topTen);
						}

					}


					// Save the document to the disk file
					TransformerFactory tranFactory = TransformerFactory.newInstance();
					Transformer aTransformer = tranFactory.newTransformer();

					// format the XML nicely
					aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

					aTransformer.setOutputProperty(
							"{http://xml.apache.org/xslt}indent-amount", "4");
					aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");


					DOMSource source = new DOMSource(doc);
					try {
						FileWriter fos = new FileWriter("D:\\temp.xml");
						StreamResult result = new StreamResult(fos);
						aTransformer.transform(source, result);

					} catch (IOException e) {

						e.printStackTrace();
					}


				} catch (TransformerException ex) {
					System.out.println("Error outputting document");

				} catch (ParserConfigurationException ex) {
					System.out.println("Error building document");
				}
				break;
			}
			case "json": {
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

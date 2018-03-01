package support;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xml.MathVisualization;

/**
 * Class LoadConfigXML
 * 
 * @author Mahesh Kumar
 *
 * Purpose of this class is to load the configuration xml file.
 */

public class LoadConfigXML {
	boolean isValidArguments = false;
	MathVisualization mathVisualization;

	public MathVisualization validateArguments(String[] args) {
		if(args.length==0 || args == null){
			System.out.println("Input argument MISSING.");
			System.out.println("Usage   : java -jar <jarFile> <inputXmlFile>");
			System.out.println("Example : java -jar mathematicalVisualization.jar etc/mathematicalVisualization.xml");
			return null;
		}else{
			if(args[0]!=null){
				if(readConfigurationFile(args[0])){
					System.out.println("Configutation file read successfully.");
					return mathVisualization;
				}else{
					System.out.println("Error while reading the confiration file.");
					return null;
				}					
			}else{
				System.out.println("Argument cannot be null");
				return null;
			}
		}	
	}

	private boolean readConfigurationFile(String fileName) {
		try {
			System.out.println("Loading the configuation XML file: "+fileName);
			JAXBContext jc = JAXBContext.newInstance(MathVisualization.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			File xmlFileName = new File(fileName.trim());
			if(xmlFileName.exists()){
				mathVisualization = (MathVisualization)unmarshaller.unmarshal(xmlFileName);
				if(mathVisualization == null){
					System.out.println("Error while parsing the input XML file");
					return false;
				}
				System.out.println("Configuration XML loaded successfully.");
			}else{
				System.out.println("Input Configuration file not found at location: "+fileName);
				return false;
			}
		} catch (JAXBException|NullPointerException e) {
			System.out.println("Unable to read parse input XML file.");
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
}

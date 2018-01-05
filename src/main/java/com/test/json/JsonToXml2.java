package com.test.json;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.FileUtils;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonToXml2 {


    public static void main(String[] args) throws IOException {
        String path =System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test2.json";
        String json = FileUtils.readFileToString(new File(path),"utf-8");
        String xml = json2xml(json);
        String xmlpath =System.getProperty("user.dir")+ File.separator+"testdata"+File.separator+"test.xml";
        FileUtils.writeStringToFile(new File(xmlpath),xml,"utf-8");
    }
    public static String json2xml(String json){
        StringReader input = new StringReader(json);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch( Exception e){
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(output.toString().length()>=38){//remove <?xml version="1.0" encoding="UTF-8"?>
            return output.toString().substring(39);
        }
        return output.toString();
    }
}

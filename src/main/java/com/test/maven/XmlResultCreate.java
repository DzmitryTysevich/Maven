package com.test.maven;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class XmlResultCreate {
    public static void writeXML() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        assert docBuilder != null;
        Document document = docBuilder.newDocument();

        Element rootElement = document.createElement("Maven");
        document.appendChild(rootElement);

        Element configFileName = document.createElement("ConfigFileName");
        rootElement.appendChild(configFileName);
        configFileName.setTextContent(new File("config.xml").getName());

        Element executionTime = document.createElement("ExecutionTime");
        rootElement.appendChild(executionTime);
        executionTime.setTextContent(new Date().toString());

        Element files = document.createElement("OldFiles");
        rootElement.appendChild(files);
        files.setTextContent(Main.getOldFiles() + Arrays.toString(new File("src/main/resources/configs").list()));

        //Create TransformerFactory for print to console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        //Output to console
        assert transformer != null;
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);

        //Print to console
        StreamResult console = new StreamResult(System.out);
        try {
            transformer.transform(source, console);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
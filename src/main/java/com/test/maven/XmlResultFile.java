package com.test.maven;

import com.test.maven.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static com.test.maven.service.FileService.SINGLETON;

public class XmlResultFile {
    private static final Logger LOGGER = LogManager.getLogger(XmlResultFile.class);

    public void writeXML(long executionTime) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Exception happen!", e);
        }
        assert docBuilder != null;
        Document document = docBuilder.newDocument();

        Element rootElement = document.createElement("Maven");
        document.appendChild(rootElement);

        Element configFileName = document.createElement("ConfigFileName");
        rootElement.appendChild(configFileName);
        configFileName.setTextContent(new File("config.xml").getName());

        Element time = document.createElement("ExecutionTime");
        rootElement.appendChild(time);
        time.setTextContent(String.valueOf(executionTime));

        Element files = document.createElement("Files");
        rootElement.appendChild(files);
        files.setTextContent(SINGLETON.getOLD_FILES() + Arrays.toString(new File(Constants.CONFIGS_PATH).list()));

        //Create TransformerFactory for print to console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            LOGGER.error("Exception happen!", e);
        }

        //Output to console
        assert transformer != null;
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);

        //Print to console
        StreamResult console = new StreamResult(System.out);
        StreamResult resultToFile = new StreamResult(new File(Constants.RESULT_XML));
        try {
            transformer.transform(source, console);
            transformer.transform(source, resultToFile);
        } catch (TransformerException e) {
            LOGGER.error("Exception happen!", e);
        }
    }
}
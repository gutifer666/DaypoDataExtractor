package trade.javiergutierrez.DaypoDatasExtractor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import trade.javiergutierrez.DaypoDatasExtractor.models.Question;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import trade.javiergutierrez.DaypoDatasExtractor.repositories.QuestionRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class SendXMLController {

    @Autowired
    private QuestionRepository questionRepository;
    private Document formatXML(MultipartFile file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file.getInputStream());
            document.getDocumentElement().normalize();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0 && nodeList.item(0) != null) {
            Node node = nodeList.item(0).getChildNodes().item(0);
            if (node != null) {
                return node.getNodeValue();
            }
        }
        return null;
    }

    public List<Question> createQuestionsFromXML(Document document) {
        NodeList nodeList = document.getElementsByTagName("c");
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String typeStr = getTagValue("t", element);
                String question = getTagValue("p", element);
                String election = getTagValue("c", element);
                NodeList respuestasNodeList = element.getElementsByTagName("o");
                List<String> solution = new ArrayList<>();
                for (int j = 0; j < respuestasNodeList.getLength(); j++) {
                    solution.add(respuestasNodeList.item(j).getTextContent());
                }

                if(typeStr != null && typeStr.equals("0")
                        && election != null
                        && election.equals("2111")
                        || Objects.equals(election, "1211")
                        || Objects.equals(election, "1121")
                        || Objects.equals(election, "1112"))
                {
                    questionRepository.save(new Question(question, election, solution));
                }
            }
        }
        return questions;
    }

    @PostMapping (value = "/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {;
        Document doc = formatXML(file);
        List<Question> questions = null;

        if (doc != null) {
            questions = createQuestionsFromXML(doc);
        }
        if (questions != null) {
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        }
        return "File uploaded unsuccessfully! -> filename = " + file.getOriginalFilename();
    }
}

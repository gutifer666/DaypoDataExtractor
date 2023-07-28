package trade.javiergutierrez.DaypoDatasExtractor.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import trade.javiergutierrez.DaypoDatasExtractor.models.Employee;
import trade.javiergutierrez.DaypoDatasExtractor.models.Question;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

// Leer un fichero XML enviado desde Postman
@RestController
public class SendXMLController {

    public Document formatXML(MultipartFile file) {
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

    public List<String> getQuestions(Document doc) {
        List<String> questions = new ArrayList<>();
        for (int i = 0; i < doc.getElementsByTagName("p").getLength(); i++) {
            questions.add(doc.getElementsByTagName("p").item(i).getTextContent());
        }
        return questions;
    }
    @PostMapping ("/sendxml")
    public String sendXMLFile(@RequestBody String xmlFile) {
        System.out.println(xmlFile);
        return xmlFile;
    }

    @PostMapping ("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {;
        Document doc = formatXML(file);
        List<String> questions = new ArrayList<>();
        questions = getQuestions(doc);

        return questions.toString();
    }

    @PostMapping ("/uploadfile2")
    public Question uploadFile2(@RequestParam("file") MultipartFile file){
        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Question.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Question question = (Question) jaxbUnmarshaller.unmarshal(file.getInputStream());
            System.out.println(question.toString());
            return question;


        }
        catch (JAXBException | IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping ("/hardcodedquestion")
    public Question hardcode(){
        String xmlString = "<c>" +
                                "<t>0</t>" +
                                "<p>En JPA una instancia tiene el estado de gestionada si...</p>" +
                                "<c>2111</c>" +
                                "<r>" +
                                    "<o>el motor de persistencia mantiene una referencia interna a la instancia de entidad y cualquier cambio en la misma será sincronizado con el almacén de datos en el siguiente commit.</o>" +
                                    "<o>el motor de persistencia mantiene una referencia interna a la instancia de entidad justo después de haberla desacoplado o desligado.</o>" +
                                    "<o>el motor de persistencia no mantiene una referencia interna a la instancia de entidad después de hacer el commit.</o>" +
                                    "<o>es una instancia de entidad recién creada.</o>" +
                                "</r>" +
                                "<h>En JPA, una entidad está en el estado de gestionada (o managed) cuando el contexto de persistencia está rastreando cualquier cambio en esa entidad, y esos cambios se sincronizarán con la base de datos cuando ocurra el próximo commit. Esta es una de las características principales de JPA y ORM (Object-Relational Mapping) en general, ya que permite tratar las entidades como objetos regulares en el código, con la garantía de que cualquier cambio que hagas se reflejará en la base de datos.</h>" +
                            "</c>";

        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Question.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            if(xmlString.contains("<t>0</t>"))
            {
                Question question = (Question) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
                System.out.println(question.toString());
                return question;
            }

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}

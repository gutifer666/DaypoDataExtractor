package trade.javiergutierrez.DaypoDatasExtractor.controllers;

import org.springframework.web.bind.annotation.*;

// Leer un fichero XML enviado desde Postman
@RestController
public class SendXMLController {
    String xmlFile = "<c><t>0</t><p>El lenguaje SQL:</p><c>2111</c><r><o>Es estándar.</o><o>Es propietario, pertenece a Oracle.</o><o>Procede del lenguaje Pascal.</o><o>Ninguna de las respuestas anteriores es correcta.</o></r></c>";
    @PostMapping ("/sendxml")
    public String sendXMLFile(@RequestBody String xmlFile) {
        System.out.println(xmlFile);
        return xmlFile;
    }

}

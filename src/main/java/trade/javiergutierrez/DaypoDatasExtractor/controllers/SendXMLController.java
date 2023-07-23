package trade.javiergutierrez.DaypoDatasExtractor.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Leer un fichero XML enviado desde Postman
@RestController
public class SendXMLController {
    @PostMapping ("/sendxml")
    public String sendXMLFile(@RequestBody String xmlFile) {
        System.out.println(xmlFile);
        return xmlFile;
    }

    @PostMapping ("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        return file.getOriginalFilename();
    }




}

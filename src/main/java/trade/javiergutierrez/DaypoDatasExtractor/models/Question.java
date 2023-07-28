package trade.javiergutierrez.DaypoDatasExtractor.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class Question implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "t")
    private Integer tipo;
    @XmlElement(name = "p")
    private String pregunta;
    @XmlElement(name = "c")
    private String eleccion;
    @XmlElementWrapper(name = "r")
    @XmlElement(name = "o")
    private List<String> respuestas;
    @XmlElement(name = "h")
    private String aclaracion;

    public Question() {
        super();
    }

    public Question(Integer tipo, String pregunta, String eleccion, List<String> respuestas, String aclaracion) {
        super();
        this.tipo = tipo;
        this.pregunta = pregunta;
        this.eleccion = eleccion;
        this.respuestas = respuestas;
        this.aclaracion = aclaracion;

    }

    //Setters and Getters

    @Override
    public String toString() {
        return String.format("Question [tipo=%s, pregunta=%s, eleccion=%s, respuestas=%s]", tipo, pregunta, eleccion, Arrays.toString(respuestas.toArray()));
    }
}

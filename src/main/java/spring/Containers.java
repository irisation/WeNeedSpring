package spring;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "files")
@XmlAccessorType(XmlAccessType.FIELD)
public class Containers {

    @XmlElement(name = "file")
    List<WordsContainer> list;

    public Containers() {

    }

    public Containers(List<WordsContainer> list) {
        this.list = list;
    }

    public List<WordsContainer> getList() {
        return list;
    }

    public void setList(List<WordsContainer> list) {
        this.list = list;
    }
}

package domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Verzoek<T extends Serializable> implements Serializable {

    @Id
    private final String parameter;
    private final String query;

    private List<T> data;

    public Verzoek(String query, String text) {
        this.query = query;
        parameter = text;
    }

    public String getParameter() {
        return parameter;
    }

    public String getQuery() {
        return query;
    }
    //TODO
    // methoden getResultaat en setResult

    public List<T> getResultaat() {
        return data;
    }

    public void setResult(List<T> data) {
        this.data = data;
    }

}

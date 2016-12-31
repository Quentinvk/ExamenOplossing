package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Gebouw.findAll", query = "SELECT g FROM Gebouw g")
public class Gebouw implements Serializable {

    @Id
    private String naam;
    private int capaciteit;

    @OneToMany(cascade = CascadeType.PERSIST)
    private final List<Dier> dieren = new ArrayList<>();

    public Gebouw(String naam, int capaciteit) {
        this.naam = naam;
        this.capaciteit = capaciteit;
    }

    protected Gebouw() {
    }

    public String getNaam() {
        return naam;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public void setCapaciteit(int capaciteit) {
        this.capaciteit = capaciteit;
    }

    public List<Dier> getDieren() {
        return dieren;
    }

    public void addDier(Dier dier) {
        this.dieren.add(dier);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.naam);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gebouw other = (Gebouw) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naam;
    }
}

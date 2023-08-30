package problemaRicorsivo.problemaRicorsivo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "ATTIVITA")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "ALIAS", nullable = false)
    private String alias;
    @Column(name = "LAVORATA", nullable = false)
    private Boolean lavorata;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTIVITA_PADRE")
    @JsonBackReference
    private Activity attivitaPadre;
    @OneToMany(mappedBy = "attivitaPadre")
    @JsonManagedReference
    private List<Activity> attivitaFiglie;

    public Activity(String alias, Boolean lavorata, Activity attivitaPadre) {
        this.alias = alias;
        this.lavorata = lavorata;
        this.attivitaPadre = attivitaPadre;
    }

    public Activity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getLavorata() {
        return lavorata;
    }

    public void setLavorata(Boolean lavorata) {
        this.lavorata = lavorata;
    }

    public Activity getAttivitaPadre() {
        return attivitaPadre;
    }

    public void setAttivitaPadre(Activity attivitaPadre) {
        this.attivitaPadre = attivitaPadre;
    }

    public List<Activity> getAttivitaFiglie() {
        return attivitaFiglie;
    }

    public void setAttivitaFiglie(List<Activity> attivitaFiglie) {
        this.attivitaFiglie = attivitaFiglie;
    }
}

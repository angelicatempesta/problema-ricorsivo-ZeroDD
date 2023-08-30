package problemaRicorsivo.problemaRicorsivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import problemaRicorsivo.problemaRicorsivo.model.Activity;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Activity findByalias(String aliasName);

    List<Activity> findBylavorata(Boolean isLavorata);

    Activity findByattivitaPadre(Activity attivitaPadre);
}

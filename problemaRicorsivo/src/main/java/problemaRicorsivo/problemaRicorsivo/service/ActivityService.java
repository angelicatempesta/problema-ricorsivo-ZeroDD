package problemaRicorsivo.problemaRicorsivo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import problemaRicorsivo.problemaRicorsivo.repository.ActivityRepository;
import problemaRicorsivo.problemaRicorsivo.model.Activity;

import java.util.Collections;
import java.util.List;


@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void createActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        if (activity.getAlias() == null || activity.getAlias().isEmpty()) {
            throw new IllegalArgumentException("Activity alias cannot be null or empty!");
        }
        if (activity.getId() == null) {
            throw new IllegalArgumentException("Activity id cannot be null!");
        }
        activityRepository.save(activity);
    }

    public Activity getActivityById(int activityId) {
        return activityRepository.findById(activityId).orElse(null);
    }

    public Activity getActivityByAlias(String aliasName) {
        return activityRepository.findByalias(aliasName);
    }

    public List<Activity> getActivityByLavorata(Boolean isLavorata) {
        return activityRepository.findBylavorata(isLavorata);
    }

    public Activity getActivityByAttivitaPadre(Activity attivitaPadre) {
        return activityRepository.findByattivitaPadre(attivitaPadre);
    }

    public List<Activity> retrieveActivities() {
        List<Activity> temporaryList = activityRepository.findAll();
        if (temporaryList.isEmpty()) {
            return Collections.emptyList();
        }
        return temporaryList;
    }

    public void updateActivity(Activity activity, Integer activityId) {
        if (activity.getId().equals(activityId)) {
            activityRepository.deleteById(activityId);
            activityRepository.save(activity);
        }
    }

    public void deleteActivityById(int activityId) {
        activityRepository.deleteById(activityId);
    }

    public boolean checkAttivita(Activity activity) {
        if (activity.getAttivitaPadre() == null) {
            return true;
        }
        if (!activity.getAttivitaPadre().getLavorata()) {
            return false;
        }
        if (activity.getAttivitaFiglie().size() > 1) {
            for (Activity a : activity.getAttivitaFiglie()) {
                if (a.getLavorata()) {
                    return false;
                }
            }
        }
        return checkAttivita(activity.getAttivitaPadre());
    }

    public void lavoraAttivita(Activity activity) {
        if (!activity.getLavorata()) {
            activity.setLavorata(true);
            activityRepository.save(activity);
        }
    }
}






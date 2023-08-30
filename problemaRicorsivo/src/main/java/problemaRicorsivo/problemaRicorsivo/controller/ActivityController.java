package problemaRicorsivo.problemaRicorsivo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import problemaRicorsivo.problemaRicorsivo.model.Activity;
import problemaRicorsivo.problemaRicorsivo.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addActivity(@RequestBody Activity activity) {
        if (activity == null) {
            return ResponseEntity.badRequest().body("Invalid activity's fields!");
        } else activityService.createActivity(activity);
        return ResponseEntity.ok("Activity added!");
    }

    @GetMapping("/retrieve")
    public ResponseEntity<List<Activity>> activityList() {
        try {
            return ResponseEntity.ok(activityService.retrieveActivities());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") int activityId) {
        if (activityService.getActivityById(activityId) != null) {
            return ResponseEntity.ok(activityService.getActivityById(activityId));
        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/alias/{alias}")
    public ResponseEntity<Activity> getActivityByAlias(@PathVariable("alias") String activityAlias) {
        if (activityService.getActivityByAlias(activityAlias) != null) {
            return ResponseEntity.ok(activityService.getActivityByAlias(activityAlias));
        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/lavorata/{lavorata}")
    public ResponseEntity<List<Activity>> getActivityByLavorata(@PathVariable("lavorata") boolean lavorata) {
        if (activityService.getActivityByLavorata(lavorata) != null) {
            return ResponseEntity.ok(activityService.getActivityByLavorata(lavorata));
        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/attivitaPadre/")
    public ResponseEntity<Activity> getActivityByAttivitaPadre(@RequestBody Activity attivitaPadre) {
        if (activityService.getActivityByAttivitaPadre(attivitaPadre) != null) {
            return ResponseEntity.ok(activityService.getActivityByAttivitaPadre(attivitaPadre));
        } else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateActivity(@RequestBody Activity activity, @PathVariable int activityId) {
        try {
            activityService.updateActivity(activity, activityId);
            return ResponseEntity.ok("Activity updated!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Activity cannot be updated! Fields are wrong or missing!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") int activityId) {
        activityService.deleteActivityById(activityId);
        return ResponseEntity.ok("Activity successfully deleted!");
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<String> checkActivity(@PathVariable("id") int activityId) {
        try {
            boolean risultato = activityService.checkAttivita(activityService.getActivityById(activityId));
            String risultatoStringa = risultato ? "true" : "false";
            return ResponseEntity.ok(risultatoStringa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Activity not found!");
        }
    }

    @PutMapping("/perform/{id}")
    public ResponseEntity<String> performActivity(@PathVariable("id") int activityId) {
        if (activityService.checkAttivita(activityService.getActivityById(activityId))) {
            activityService.lavoraAttivita(activityService.getActivityById(activityId));
            return ResponseEntity.ok("Activity performed!");
        } else return ResponseEntity.badRequest().body("Activity cannot be performed!");
    }
}

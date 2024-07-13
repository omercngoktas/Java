package com.omercngoktas.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private final List<Run> runs = new ArrayList<>();

    public List<Run> findAll() {
        return runs;
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(
                1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                3,
                Location.INDOOR));

        runs.add(new Run(
                2,
                "Tuesday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                7,
                Location.OUTDOOR));
    }

    public Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    public void create(Run run) {
        runs.add(run);
    }

    public void update(Integer id, Run run) {
        Optional<Run> existingRun = findById(id);

        if(existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }

    }

    public void delete(Integer id) {
        Optional<Run> existingRun = findById(id);

        if(existingRun.isPresent()) {
            runs.remove(existingRun.get());
        }
    }


}

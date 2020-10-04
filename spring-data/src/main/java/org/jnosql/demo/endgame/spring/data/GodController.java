package org.jnosql.demo.endgame.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GodController {

    @Autowired GodRepository godRepository;

    @GetMapping("/gods") public ResponseEntity<List<God>> getAllGods(@RequestParam(required = false) String name) {
        try {
            List<God> gods = new ArrayList<God>();

            if (name == null)
                godRepository.findAll().forEach(gods::add);
            else
                godRepository.findByNameContaining(name).forEach(gods::add);

            if (gods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(gods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gods/{id}") public ResponseEntity<God> getGodById(@PathVariable("id") String id) {
        Optional<God> godData = godRepository.findById(id);

        if (godData.isPresent()) {
            return new ResponseEntity<>(godData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/gods") public ResponseEntity<God> addGod(@RequestBody God god) {
        try {
            God _tutorial = godRepository.save(new God(god.getName(), god.getPower()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/gods/{id}") public ResponseEntity<God> updateGod(@PathVariable("id") String id, @RequestBody God god) {
        Optional<God> godData = godRepository.findById(id);

        if (godData.isPresent()) {
            God _god = godData.get();
            if ( null != god.getName()) _god.setName(god.getName());
            if ( null != god.getPower()) _god.setPower(god.getPower());
            return new ResponseEntity<>(godRepository.save(_god), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/gods/{id}") public ResponseEntity<HttpStatus> deleteGod(@PathVariable("id") String id) {
        try {
            godRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/gods") public ResponseEntity<HttpStatus> deleteAllGods() {
        try {
            godRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

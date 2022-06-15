package main.restcontroller;

import main.error.TourErrorResponse;
import main.error.TourNotFoundException;
import main.model.Tour;
import main.service.TourService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TourRestController {

    private final TourService tourService;

    public TourRestController(TourService tourService) {
        this.tourService = tourService;
    }


    @GetMapping("/tours")
    public ResponseEntity<List<Tour>> getTours(){
        List<Tour> tours = tourService.getAll();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "getTours");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tours);
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<Tour> getTour(@PathVariable int id){
        Tour tour = tourService.getById(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "getTour");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(tour);
    }

    @PostMapping(value = "/tours", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Tour> saveTour(@RequestBody Tour tour){
        // setting the Id to 0 will ensure the save method is envoked
        // not the update method
        tour.setId(0);
        tourService.saveOrUpdate(tour);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "saveTour");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(tour);
    }


    @PutMapping("/tours")
    public ResponseEntity<Void> editTour(@RequestBody Tour tour)
    {
        Tour t = tourService.getById(tour.getId());
        tourService.saveOrUpdate(t);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "editTour");

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .headers(httpHeaders)
                // after we create an object we don't want to return it
                // so we use the build method
                .build();
    }

    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable int id )
    {
        Tour tour = tourService.getById(id);
        tourService.delete(tour.getId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Method", "deleteTour");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(null);
    }

    @ExceptionHandler
    public ResponseEntity<TourErrorResponse> handleException(TourNotFoundException exception){
        TourErrorResponse error = new TourErrorResponse();
        error.setIdStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<TourErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

}

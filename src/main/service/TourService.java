package main.service;

import main.model.Tour;

import java.util.List;

public interface TourService {

    public List<Tour> getAll();
    public Tour getbyId(int id);
    public void saveOrUpdate(Tour tour);
    public void delete(int id);
//    public void addTourDetailsIfNotExist(Tour tour);
    public Tour getByIdWithComments(int id);
    public void addUserToTour(int id, int userId);


}

package main.dao;

import main.model.Tour;

import java.util.List;

public interface TourDAO {

    public List<Tour> getAll();
    public Tour getbyId(int id);
    public void saveOrUpate(Tour tour);
    public void delete(int id);
    public Tour getByIdWithComments(int id);

}

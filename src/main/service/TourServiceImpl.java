package main.service;

import java.util.ArrayList;
import java.util.List;

import main.dao.UserDAO;
import main.dao.UserDAOImpl;
import main.model.TourDetails;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.TourDAO;
import main.model.Tour;

import javax.transaction.Transactional;

// ALl methods from this class are transactional
@Service
@Transactional
public class TourServiceImpl implements TourService{

    @Autowired
    private TourDAO tourDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Tour> getAll() {
        return tourDAO.getAll();
    }

    @Override
    public Tour getbyId(int id) {
        return tourDAO.getbyId(id);
//        return tourDAO.getbyId(id);
//        Tour tour = tourDAO.getbyId(id);
//        tour.getComments().size();
//        return tour;

    }


    @Override
    public void saveOrUpdate(Tour tour) {
        tourDAO.saveOrUpate(tour);
    }

    @Override
    public void delete(int id) {
        tourDAO.delete(id);
    }

//    @Override
//    public void addTourDetailsIfNotExist(Tour tour) {
//        if(tour.getTourDetails() == null){
//            tour.setTourDetails(new TourDetails());
//            saveOrUpdate(tour);
//        }
//    }

    @Override
    public Tour getByIdWithComments(int id) {
        return tourDAO.getByIdWithComments(id);
    }

    @Override
    public void addUserToTour(int id, int userId) {
        Tour tour = getbyId(id);
        if(tour.getUsers() == null) {
            tour.setUsers(new ArrayList<>());
        }
        User user = userDAO.getById(userId);
        if(user != null) {
            tour.getUsers().add(user);
            saveOrUpdate(tour);
        }
    }
}

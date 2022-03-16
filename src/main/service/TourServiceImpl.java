package main.service;

import java.util.ArrayList;
import java.util.List;

import main.model.User;
import main.repository.TourRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Tour;

import javax.transaction.Transactional;

// ALl methods from this class are transactional
@Service
@Transactional
public class TourServiceImpl implements TourService{

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour getbyId(int id) {
        return tourRepository.findById(id).get();
//        return tourDAO.getbyId(id);
//        Tour tour = tourDAO.getbyId(id);
//        tour.getComments().size();
//        return tour;

    }


    @Override
    public void saveOrUpdate(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void delete(int id) {
        tourRepository.deleteById(id);
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
        return tourRepository.getByIdWithComments(id);
    }

    @Override
    public void addUserToTour(int id, int userId) {
        Tour tour = getbyId(id);
        if(tour.getUsers() == null) {
            tour.setUsers(new ArrayList<>());
        }
        User user = userRepository.getById(userId);
        if(user != null) {
            tour.getUsers().add(user);
            saveOrUpdate(tour);
        }
    }
}

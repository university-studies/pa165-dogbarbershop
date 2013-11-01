/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.utils.DogConvertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author martin
 */

@Service
public class DogService {
    
    @Autowired
    private DogDao dogDao;
    
    public DogDao getDogDao(){
        return this.dogDao;
    }
    
    public void setDogDao(DogDao dogDao){
        this.dogDao = dogDao;
    }
    
    @Transactional
    public Dog addDog(DogDto dogDto){
        if (dogDto == null) {
            throw new DataAccessException("Argument dogDto je null") {};
        }
        if (dogDto.getId() != null){
            throw new DataAccessException("Cannot add dog, it is "
                    + "already persisted") {};
        }
        return dogDao.addDog(DogConvertor.dogDtoToDog(dogDto));
    }
    
    @Transactional
    public Dog updateDog(DogDto dogDto){
        if (dogDto == null) {
            throw new DataAccessException("Argument dogDto je null") {};
        }
        if (dogDto.getId() == null){
            throw new DataAccessException("Cannot update dog, it is not persisted") {};
        }
        return dogDao.updateDog(DogConvertor.dogDtoToDog(dogDto));
    }
    
    @Transactional
    public void deleteDog(DogDto dogDto){
        if (dogDto == null) {
            throw new DataAccessException("Argument dogDto je null") {};
        }
        if (dogDto.getId() == null){
            throw new DataAccessException("Cannot delete dog, it is not persisted") {};
        }
        dogDao.removeDog(DogConvertor.dogDtoToDog(dogDto));
    }
    
    @Transactional
    public DogDto getDogById(Long id){
        if (id == null) {
            throw new DataAccessException("Argument is null."){};
        }
        return DogConvertor.dogToDogDto(dogDao.getDog(id));
    }
    
    @Transactional
    public List<DogDto> getAllDogs(){
        List<DogDto> listAllDogs = new ArrayList<DogDto>();
        for (Dog dog : dogDao.getAllDogs()){
            listAllDogs.add(DogConvertor.dogToDogDto(dog));
        }
        return listAllDogs;
    }
    
    @Transactional
    public List<DogDto> getDogsByOwner(Customer owner){
        List<DogDto> dogsByOwner = new ArrayList<DogDto>();
        for (Dog dog : dogDao.getDogsByOwner(owner)){
            dogsByOwner.add(DogConvertor.dogToDogDto(dog));
        }
        return dogsByOwner;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.utils.DogConverter;
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
public class DogServiceImpl {
    
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
        
        Dog dogResult;
        try{
            dogResult = dogDao.addDog
                (DogConverter.dogDtoToDog(dogDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return dogResult;
    }
    
    @Transactional
    public Dog updateDog(DogDto dogDto){
        if (dogDto == null) {
            throw new DataAccessException("Argument dogDto je null") {};
        }
        if (dogDto.getId() == null){
            throw new DataAccessException("Cannot update dog, it is not persisted") {};
        }
        
        Dog dogResult;
        try{
            dogResult = dogDao.updateDog(DogConverter.dogDtoToDog(dogDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return dogResult;
    }
    
    @Transactional
    public void deleteDog(DogDto dogDto){
        if (dogDto == null) {
            throw new DataAccessException("Argument dogDto je null") {};
        }
        if (dogDto.getId() == null){
            throw new DataAccessException("Cannot delete dog, it is not persisted") {};
        }
        
        try{
            dogDao.removeDog(DogConverter.dogDtoToDog(dogDto));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
    }
    
    @Transactional
    public DogDto getDogById(Long id){
        if (id == null) {
            throw new DataAccessException("Argument is null."){};
        }
        
        DogDto dogResult;
        try{
            dogResult = DogConverter.dogToDogDto(dogDao.getDog(id));
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return dogResult;
    }
    
    @Transactional
    public List<DogDto> getAllDogs(){
        List<DogDto> listAllDogs = new ArrayList<DogDto>();
        try{
            for (Dog dog : dogDao.getAllDogs()){
                listAllDogs.add(DogConverter.dogToDogDto(dog));
            }
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return listAllDogs;
    }
    
    @Transactional
    public List<DogDto> getDogsByOwner(Customer owner){
        List<DogDto> dogsByOwner = new ArrayList<DogDto>();
        try {
            for (Dog dog : dogDao.getDogsByOwner(owner)){
                dogsByOwner.add(DogConverter.dogToDogDto(dog));
            }
        }
        catch (Exception ex){
            throw new DataAccessException("Error during accessing persistence layer") {};
        }
        return dogsByOwner;
    }
}

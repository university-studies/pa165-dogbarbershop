/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service.impl;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.utils.CustomerConverter;
import fi.muni.pa165.utils.DogConverter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author martin
 */

@Service
public class DogServiceImpl implements DogService{
    
    @Autowired
    private DogDao dogDao;
    
    public DogDao getDogDao(){
        return this.dogDao;
    }
    
    public void setDogDao(DogDao dogDao){
        this.dogDao = dogDao;
    }
    
    @Transactional
    public void addDog(DogDto dogDto){
        Validate.isTrue(dogDto != null, "DogDto is null!");
        Validate.isTrue(dogDto.getId() == null, "DogDto ID is not null!");
        try{
            dogDao.addDog(DogConverter.dogDtoToDog(dogDto));
        }
        catch(Exception ex){
            throw new DataAccessException("Error during accessing persistence layer", ex) {};
        }
    }
    
    @Transactional
    public void updateDog(DogDto dogDto){
        Validate.isTrue(dogDto != null, "DogDto is null!");
        Validate.isTrue(dogDto.getId() != null, "DogDto ID is null!");
        try{
            dogDao.updateDog(DogConverter.dogDtoToDog(dogDto));
        }
        catch(Exception ex){
            throw new DataAccessException("Error during accessing persistence layer", ex) {};
        }
    }
    
    @Transactional
    public void deleteDog(DogDto dogDto){
        Validate.isTrue(dogDto != null, "DogDto is null!");
        Validate.isTrue(dogDto.getId() != null, "DogDto ID is null!");
        try{
            dogDao.removeDog(DogConverter.dogDtoToDog(dogDto));
        }
        catch(Exception ex){
            throw new DataAccessException("Error during accessing persistence layer", ex) {};
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
    public List<DogDto> getDogsByOwner(CustomerDto owner){
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

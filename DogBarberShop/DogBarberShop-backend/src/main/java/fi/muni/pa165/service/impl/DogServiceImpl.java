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
    
    @Override
    @Transactional
    public void addDog(DogDto dogDto){
        Validate.isTrue(dogDto != null, "DogDto is null!");
        Validate.isTrue(dogDto.getId() == null, "DogDto ID is not null!");
        Dog dog = DogConverter.dogDtoToDog(dogDto);
        dogDao.addDog(dog);
        dogDto.setId(dog.getId());
    }
    
    @Override
    @Transactional
    public void updateDog(DogDto dogDto){
        Validate.isTrue(dogDto != null, "DogDto is null!");
        Validate.isTrue(dogDto.getId() != null, "DogDto ID is null!");
        dogDao.updateDog(DogConverter.dogDtoToDog(dogDto));
    }
    
    @Override
    @Transactional
    public void deleteDog(DogDto dogDto){
        Validate.isTrue(dogDto != null, "DogDto is null!");
        Validate.isTrue(dogDto.getId() != null, "DogDto ID is null!");
        dogDao.removeDog(DogConverter.dogDtoToDog(dogDto));
    }
    
    @Override
    @Transactional
    public DogDto getDogById(Long id){
        Validate.isTrue(id != null, "ID cannot be null!");
        DogDto dogResult;
        dogResult = DogConverter.dogToDogDto(dogDao.getDog(id));
        return dogResult;
    }
    
    @Override
    @Transactional
    public List<DogDto> getAllDogs(){
        List<DogDto> listAllDogs = new ArrayList<>();
        for (Dog dog : dogDao.getAllDogs()){
            listAllDogs.add(DogConverter.dogToDogDto(dog));
        }
        return listAllDogs;
    }
    
    @Override
    @Transactional
    public List<DogDto> getDogsByOwner(CustomerDto owner){
        List<DogDto> dogsByOwner = new ArrayList<>();
        for (Dog dog : dogDao.getDogsByOwner(owner)){
            dogsByOwner.add(DogConverter.dogToDogDto(dog));
        }
        return dogsByOwner;
    }
}

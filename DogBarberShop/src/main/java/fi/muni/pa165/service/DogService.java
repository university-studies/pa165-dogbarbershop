/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.service;

import fi.muni.pa165.dto.DogDto;
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
    public List<DogDto> getAllDogs(){
        List<DogDto> listAllDogs = new ArrayList<DogDto>();
        //for (Dog )
        return listAllDogs;
    }
}

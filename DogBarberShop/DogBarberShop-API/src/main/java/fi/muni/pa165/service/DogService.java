package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface DogService {
    void addDog(DogDto dogDto);
    void updateDog(DogDto dogDto);
    void deleteDog(DogDto dogDto);
    DogDto getDogById(Long id);
    List<DogDto> getAllDogs();
    List<DogDto> getDogsByOwner(CustomerDto owner);
    
}

package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface DogService {
    DogDto addDog(DogDto dogDto);
    DogDto updateDog(DogDto dogDto);
    void deleteDog(DogDto dogDto);
    DogDto getDog(DogDto dogDto);
    List<DogDto> getAllDogs();
    List<DogDto> getDogsByOwner(CustomerDto owner);
    
}

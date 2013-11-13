package fi.muni.pa165.service;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface DogService {
    public void addDog(DogDto dogDto);
    public void updateDog(DogDto dogDto);
    public void deleteDog(DogDto dogDto);
    public DogDto getDogById(Long id);
    public List<DogDto> getAllDogs();
    public List<DogDto> getDogsByOwner(CustomerDto owner);
    
}

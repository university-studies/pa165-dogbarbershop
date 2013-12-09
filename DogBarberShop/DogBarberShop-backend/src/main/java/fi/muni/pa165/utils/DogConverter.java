package fi.muni.pa165.utils;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.entity.Dog;

/**
 *
 * @author martin
 */
public class DogConverter {
    
    public static Dog dogDtoToDog(DogDto dogDto){
        Dog dog = new Dog();
        if (dogDto.getId() != null) {
            dog.setId(dogDto.getId());
        }
        dog.setName(dogDto.getName());
        dog.setBreed(dogDto.getBreed());
        dog.setBirth(dogDto.getBirthDate());
        if (dogDto.getOwner() != null){
            dog.setOwner(CustomerConverter.CustomerDtoToCustomer(dogDto.getOwner()));
        }
        return dog;
    }
    
    public static DogDto dogToDogDto(Dog dog){
        DogDto dogDto = new DogDto();
        dogDto.setId(dog.getId());
        dogDto.setName(dog.getName());
        dogDto.setBreed(dog.getBreed());
        dogDto.setBirthDate(dog.getBirth());
        if (dog.getOwner() != null){
            dogDto.setOwner(CustomerConverter.CustomerToCustomerDto(dog.getOwner()));
        }
        return dogDto;
    }
}

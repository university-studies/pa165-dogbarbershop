package fi.muni.pa165.utils;

import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.entity.Dog;

/**
 *
 * @author martin
 */
public class DogConverter {
    
    public static Dog dogDtoToDog(DogDto dogDto){
        Dog dog = new Dog(dogDto.getName(), dogDto.getBreed(), dogDto.getBirthDate(),
                CustomerConverter.CustomerDtoToCustomer(dogDto.getOwner()));
        dog.setId(dogDto.getId());
        return dog;
    }
    
    public static DogDto dogToDogDto(Dog dog){
        DogDto dogDto = new DogDto(dog.getId(), dog.getName(), 
                dog.getBreed(), dog.getBirth(), CustomerConverter.CustomerToCustomerDto(dog.getOwner()));
        return dogDto;
    }
}

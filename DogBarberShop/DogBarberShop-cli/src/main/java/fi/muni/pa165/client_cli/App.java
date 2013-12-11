package fi.muni.pa165.client_cli;

import static fi.muni.pa165.client_cli.CLIArgumentsParser.Methods.ADD;
import static fi.muni.pa165.client_cli.CLIArgumentsParser.Methods.DELETE;
import static fi.muni.pa165.client_cli.CLIArgumentsParser.Methods.GETALL;
import static fi.muni.pa165.client_cli.CLIArgumentsParser.Methods.GETBYID;
import static fi.muni.pa165.client_cli.CLIArgumentsParser.Methods.UPDATE;
import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import java.net.ConnectException;
import java.util.List;
import javax.ws.rs.ProcessingException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * Author: Jan Pacner, Pavol Loffay
 */
public class App {

    public static void main(String[] args) {
        try {
            CLIArgumentsParser arguments = new CLIArgumentsParser(args);

            switch (arguments.parse()) {
                case HELP:
                    System.out.println(CLIArgumentsParser.helpMessage);
                    return;
                case OK:
                    if (arguments.getEntity() == CLIArgumentsParser.Entities.CUSTOMER) {
                        customerRestProces(arguments);
                      
                    }
                    if (arguments.getEntity() == CLIArgumentsParser.Entities.DOG) {
                        dogRestProcess(arguments);
                    }
            }
        } catch (Exception e) {
            if (e instanceof ConnectException || e instanceof ProcessingException) {
                System.err.println("Connection error: check option -s\n");
            } else {
                System.err.println(e.getMessage() + "\n");
            }

            System.out.println(CLIArgumentsParser.helpMessage);
            System.exit(1);
        }
    }

    /**
     * 
     * @param argParsed
     * @return 
     */
    private static Boolean customerRestProces(CLIArgumentsParser argParsed) {
        CustomerClient customerRest = new CustomerClient(argParsed.getServerAddress());
        List<String> arg = argParsed.getMethodArgs();
        
        switch (argParsed.getMethod()) {
            case GETALL:
                for (CustomerDto c : customerRest.getAll()) {
                    System.out.println(c.toStringData());
                }
                break;
            case GETBYID:
                CustomerDto cust = customerRest.getById(arg.get(0));
                if (cust != null) {
                    System.out.println(cust.toStringData());
                }
                break;
            case ADD:
                Long newId = customerRest.add(new CustomerDto(arg.get(0),
                                                              arg.get(1),
                                                              arg.get(2),
                                                              arg.get(3)));
                System.out.println(newId);
                break;
            case UPDATE:
                customerRest.update(new CustomerDto(new Long(arg.get(0)),
                                                             arg.get(1),
                                                             arg.get(2),
                                                             arg.get(3),
                                                             arg.get(4)));
                break;
            case DELETE:
                customerRest.delete(arg.get(0));
                break;
        }
        return true;
    }
    
    private static Boolean dogRestProcess(CLIArgumentsParser argParsed) {
        DogClient dogRest = new DogClient(argParsed.getServerAddress());
        final List<String> arg = argParsed.getMethodArgs();

        switch (argParsed.getMethod()) {
            case GETBYID:
                DogDto dog = dogRest.getById(arg.get(0));
                if (dog != null) {
                    System.out.println(dog.toStringData());
                }
                break;
            case GETALL:
                for (DogDto d : dogRest.getAll()) {
                    System.out.println(d.toStringData());
                }
                break;
            case ADD: {
                final DateTimeFormatter format = DateTimeFormat.forPattern("dd.MM.yyyy");
                final LocalDate date = format.parseLocalDate(arg.get(2));
                
                Long newId = null;
                if (arg.size() == 4) {
                    newId = dogRest.add(new DogDto(arg.get(0), arg.get(1), 
                                        date, null), arg.get(3));
                } else {
                    newId = dogRest.add(new DogDto(arg.get(0), arg.get(1), 
                                        date, null), "no-ID");
                }
                
                if (newId != null) {
                    System.out.println(newId);
                }
                break;
            }
            case UPDATE: {
                final DateTimeFormatter format = DateTimeFormat.forPattern("dd.MM.yyyy");
                final LocalDate date = format.parseLocalDate(arg.get(3));
                
                dogRest.update(new DogDto(new Long(arg.get(0)), arg.get(1), 
                                          arg.get(2), date, null), arg.get(4));
                break;
            }
            case DELETE:
                dogRest.delete(arg.get(0));
                break;
        }

        return true;
    }
}
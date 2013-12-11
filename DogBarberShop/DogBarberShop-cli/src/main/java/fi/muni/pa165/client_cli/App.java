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
            case ADD:
                Long newId = dogRest.add(new DogDto(arg.get(0), arg.get(1),
                                                    null, null));
                System.out.println(newId);
                break;
            case UPDATE:
                dogRest.update(new DogDto(new Long(arg.get(0)), arg.get(1), 
                                          arg.get(2), null, null));
                break;
            case DELETE:
                dogRest.delete(arg.get(0));
                break;
        }

        return true;
    }
}

/*
             System.out.print(
             "SYNOPSIS\n" +
             "  DogBarberShop-cli [-s <server_url>] -e <entity> <method> [<meth_arg>...]\n" +
             "OPTIONS\n" +
             "  -s <server_URI>\n" +
             "    server to which to connect to\n" +
             "    default: localhost\n" +
             "  -e <entity>\n" +
             "    entity to work with (see LIST OF ENTITIES)\n" +
             "  <method> [<meth_arg>...]\n" +
             "    method with arguments to invoke on the given entity\n" +
             "    some (e.g. add) do return ID (write to stdout) of record\n" +
             "      they operated on\n" +
             "    see LIST OF ENTITIES for available methods\n" +
             // see fi.muni.pa165.service
             "LIST OF ENTITIES\n" +
             "  customer\n" +
             // this one should print the resulting id
             "    add <name> <surname> <address> <phone>\n" +
             "    update <id> <name> <surname> <address> <phone>\n" +
             "    delete <id>\n" +
             // this one should print output already quoted in
             // single quotes to allow simple shell usage without
             // additional parsing, e.g.:
             //   rename_customer() {
             //     # $1 $2   $3      $4      $5    $6       $7
             //     # id name surname address phone new_name new_surname
             //     mvn ... -e customer update "$1" "$2" "$6" "$7" "$4" "$5"
             //   }
             //   rename_customer $(mvn ... -e customer getbyid 4321) 'Franta' 'Vomacka'
             "    getbyid <id>\n" +
             // FIXME print IDs as well
             "    getall\n" +
             "  dog\n" +
             "    NO IDEA WHAT IS THE SECOND ENTITY Oliver HAS CHOSEN TO IMPLEMENT REST API ON\n" +
             "  dogservice\n" +
             "    NO IDEA WHAT IS THE SECOND ENTITY Oliver HAS CHOSEN TO IMPLEMENT REST API ON\n" +
             "  employee\n" +
             "    NO IDEA WHAT IS THE SECOND ENTITY Oliver HAS CHOSEN TO IMPLEMENT REST API ON\n" +
             "  service\n" +
             "    NO IDEA WHAT IS THE SECOND ENTITY Oliver HAS CHOSEN TO IMPLEMENT REST API ON\n"
             );
             //FIXME -s argument could be also DNS hostname, not only IP addr
             //FIXME exit status 0
      
             */
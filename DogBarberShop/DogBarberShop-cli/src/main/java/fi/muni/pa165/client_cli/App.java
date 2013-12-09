package fi.muni.pa165.client_cli;

import fi.muni.pa165.dto.CustomerDto;
import java.util.List;
import org.apache.log4j.Logger;

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
                        
                        CustomerClient customerRest = new CustomerClient(arguments.getServerAddress());
                        final List<String> arg = arguments.getMethodArgs();
                        
                        switch (arguments.getMethod()) {
                            case GETALL:
                                System.err.println("GETALL");
                                for (CustomerDto c: customerRest.getAll()) {
                                    System.out.println(c.toStringData());
                                }
                               break;
                            case GETBYID:
                                System.err.println("GETBYID");
                                CustomerDto cust = customerRest.getById(arguments.getMethodArgs().get(0));
                                if (cust == null) {
                                } else {
                                    System.out.println(cust.toStringData());
                                }                                
                                break;
                            case ADD:
                                System.err.println("ADD");
                                Long newId = customerRest.add(new CustomerDto(arg.get(0), arg.get(1), arg.get(2), arg.get(3)));
                                System.out.println(newId);
                                break;
                            case UPDATE:
                                customerRest.update(new CustomerDto(new Long(arg.get(0)), arg.get(1), arg.get(2), arg.get(3), arg.get(4)));
                                System.err.println("UPDATE");
                                break;
                            case DELETE:
                                System.err.println("DELETE " + arg.get(0));
                                customerRest.delete(arg.get(0));
                                break;
                        }                     
                    }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
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
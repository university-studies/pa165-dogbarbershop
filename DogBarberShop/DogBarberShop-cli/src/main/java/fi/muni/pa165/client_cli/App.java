package fi.muni.pa165.client_cli;

/**
 *
 * Author: Jan Pacner
 */
public class App {
  public static void main( String[] args ) {
    // avoid stack trace if anything unexpected happens
    try {
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
    }
    catch (Exception e) {
      System.err.println("ERR Unknown error, exiting.");
      // FIXME exit status 1
    }
  }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.client_cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class CLIArgumentsParser {

    private String serverAddress;
    private Entities entity;
    private Methods method;
    private List<String> methodArgs;
    private Options options;
    private final String[] args;
    private static final String OPT_ENTITY = "e";
    private static final String OPT_SERVER = "s";
    private static final Integer ENTITY_MIN_ARGUMENTS = 2;
    public static final String helpMessage =
            "SYNOPSIS\n"
            + "    DogBarberShop-cli [-s <server_url>] -e <entity> <method> [<meth_arg>...]\n"
            + "OPTIONS\n"
            + "    -e <entity> <method> [<meth_arg>...]  entity to work with (see LIST OF ENTITIES)\n"
            + "    -s <server_URI>        server to which to connect to\n"
            + "                           default: http://localhost:8080/pa165/webresources\n"
            + "LIST OF ENTITIES WITH METHODS\n"
            + "  CUSTOMER\n"
            + "    customer getall\n"
            + "    customer getbyid <id>\n"
            + "    customer add <name> <surname> <address> <phone>\n"
            + "    customer update <id> <name> <surname> <address> <phone>\n"
            + "             if do not exists create new, with new id\n"
            + "    customer delete <id>\n"
            + "  DOG\n" 
            + "    dog getall\n"
            + "    dog getbyid <id>\n"
            + "    dog add <name> <breed> \n"
            + "    dog update <id> <name> <breed>\n"
            + "             if do not exists create new, with new id\n"
            + "    dog delete <id>";

    /*
     * Enum for Methods
     */
    public enum Methods {

        GETALL,
        GETBYID,
        ADD,
        UPDATE,
        DELETE;

        public static Methods stringToEnum(String str) {
            if (GETALL.name().equalsIgnoreCase(str)) {
                return GETALL;
            }
            if (GETBYID.name().equalsIgnoreCase(str)) {
                return GETBYID;
            }
            if (ADD.name().equalsIgnoreCase(str)) {
                return ADD;
            }
            if (UPDATE.name().equalsIgnoreCase(str)) {
                return UPDATE;
            }
            if (DELETE.name().equalsIgnoreCase(str)) {
                return DELETE;
            }
            return null;
        }
    };

    /*
     * Enum for Entities
     */
    public enum Entities {

        DOG,
        CUSTOMER;

        public static Entities stingtoEnum(String str) {
            if (DOG.name().equalsIgnoreCase(str)) {
                return DOG;
            }
            if (CUSTOMER.name().equalsIgnoreCase(str)) {
                return CUSTOMER;
            }
            return null;
        }
    };

    public CLIArgumentsParser(String[] args) {
        this.args = args;

        /*
         * Create options
         */
        Option server = OptionBuilder.withArgName("server_URI")
                .hasArg()
                .withDescription(
                "server to which to connect to \n default: localhost")
                .create(OPT_SERVER);

        Option entity = OptionBuilder.withArgName("entity> <method")
                .hasArgs()
                .withValueSeparator(' ')
                .isRequired()
                .withDescription(
                "entity to work with (see LIST OF ENTITIES)")
                .create(OPT_ENTITY);

        /*
         * Add options
         */
        options = new Options();
        options.addOption(server);
        options.addOption(entity);
    }

    /*
     * Return values for parse method
     */
    public enum ParseStatus {

        OK,
        HELP,
    };

    /*
     * Parse program arguments
     */
    public ParseStatus parse() throws Exception {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        /*
         * Starting parsing 
         */
        if (argsList.isEmpty()) {
            return ParseStatus.HELP;
        }

        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, this.args);

        if (cmd.hasOption(OPT_SERVER)) {
            /*
             * chech if option is used multiple times -> not allowed
             */
            argsList.remove("-" + OPT_SERVER.toLowerCase());
            if (argsList.contains("-" + OPT_SERVER.toLowerCase())) {
                throw new AlreadySelectedException(
                        "Option occurs multiple times: -"
                        + OPT_SERVER.toLowerCase());
            }

            this.serverAddress = cmd.getOptionValue("s", "localhost");
        } else {
            this.serverAddress = "http://localhost:8080/pa165/webresources";
        }

        if (cmd.hasOption(OPT_ENTITY)) {
            /*
             * chech if option is used multiple times -> not allowed
             */
            argsList.remove("-" + OPT_ENTITY.toLowerCase());
            if (argsList.contains("-" + OPT_ENTITY.toLowerCase())) {
                throw new AlreadySelectedException(
                        "Option occurs multiple times: -"
                        + OPT_ENTITY.toLowerCase());
            }

            List<String> entityArguments = Arrays.asList(cmd.getOptionValues("e"));
            
            /*
             * -e <entity_name> <method> => min 2 arguments
             */
            if (!entityArgumentsCheck(entityArguments)) {
                throw new Exception("Option -e arguments error");
            }

            this.methodArgs = entityArguments.subList(2, entityArguments.size());
        }

        return ParseStatus.OK;
    }

    /*
     * Metoda bude pocitat ze v args je aj <entity> <method>
     * @param argumety prepinaca -e
     */
    private Boolean entityArgumentsCheck(List<String> eArgs) throws Exception {
        if (eArgs.size() < ENTITY_MIN_ARGUMENTS) {
            throw new Exception("-e requires min <entity> <method>");
        } else {
            this.entity = Entities.stingtoEnum(eArgs.get(0));
            this.method = Methods.stringToEnum(eArgs.get(1));
        }

        if (entity == Entities.CUSTOMER) {
            switch (method) {
                case GETALL:
                    if (eArgs.size() != 2) {
                        return false;
                    }
                    break;
                case GETBYID:
                    if (eArgs.size() != 3) {
                        return false;
                    }
                    break;
                case DELETE:
                    if (eArgs.size() != 3) {
                        return false;
                    }
                    break;
                case UPDATE:
                    if (eArgs.size() != 7) {
                        return false;
                    }
                    break;
                case ADD:
                    if (eArgs.size() != 6) {
                        return false;
                    }
            }
        }
        if (entity == Entities.DOG) {
            switch (method) {
                case GETALL:
                    if (eArgs.size() != 2) {
                        return false;
                    }
                    break;
                case GETBYID: 
                    if (eArgs.size() != 3) {
                        return false;
                    }
                    break;
                case DELETE:
                    if (eArgs.size() != 3) {
                        return false;
                    }
                    break;
                case ADD: 
                    if (eArgs.size() != 4) {
                        return false;
                    }
                    break;
                case UPDATE:
                    if (eArgs.size() != 5) { 
                        return false;
                    }
                    break;
            }   
        }
        
        return true;
    }

    public Entities getEntity() {
        return entity;
    }

    public Methods getMethod() {
        return method;
    }

    public List<String> getMethodArgs() {
        return methodArgs;
    }

    public String getServerAddress() {
        return serverAddress;
    }
}

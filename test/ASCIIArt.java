package test;

/**
 * This class contains methods that print project introduction messages and 
 * stylized ASCII art headers to the console with color formatting
 */
public class ASCIIArt{
    /**
 * Displays an ASCII art introduction for the project with various colored sections.
 * The output includes the project name and group members' names in different colors 
 * using ANSI escape codes. The result is printed to the console.
 */
    public static void display_Proj_intro() {
        image3();
        System.out.println("\033[1;34m");
        System.err.println(" ██████   ██████  ██████      ██████  ██████   ██████       ██ ███████  ██████ ████████     ██████  \r\n" + //
                           "██    ██ ██    ██ ██   ██     ██   ██ ██   ██ ██    ██      ██ ██      ██         ██             ██ \r\n" + //
                           "██    ██ ██    ██ ██████      ██████  ██████  ██    ██      ██ █████   ██         ██         █████  \r\n" + //
                           "██    ██ ██    ██ ██          ██      ██   ██ ██    ██ ██   ██ ██      ██         ██        ██      \r\n" + //
                           " ██████   ██████  ██          ██      ██   ██  ██████   █████  ███████  ██████    ██        ███████");

        System.out.println("\033[1;34m");
        System.out.println(" ██████  ██████   ██████  ██    ██ ██████       ██  ██ \r\n" + //
                           "██       ██   ██ ██    ██ ██    ██ ██   ██     ███ ███ \r\n" + //
                           "██   ███ ██████  ██    ██ ██    ██ ██████       ██  ██ \r\n" + //
                           "██    ██ ██   ██ ██    ██ ██    ██ ██           ██  ██ \r\n" + //
                           " ██████  ██   ██  ██████   ██████  ██           ██  ██");

        
        System.out.println("\033[1;35m");
        System.out.println("██████  ██ ██       ██████  ███████      █████   ██████  █████  ██████  ████████ ██    ██ ██████  ██   ██ \r\n" + //
                           "██   ██ ██ ██      ██       ██          ██   ██ ██      ██   ██ ██   ██    ██    ██    ██ ██   ██ ██  ██  \r\n" + //
                           "██████  ██ ██      ██   ███ █████       ███████ ██      ███████ ██████     ██    ██    ██ ██████  █████   \r\n" + //
                           "██   ██ ██ ██      ██    ██ ██          ██   ██ ██      ██   ██ ██   ██    ██    ██    ██ ██   ██ ██  ██  \r\n" + //
                           "██████  ██ ███████  ██████  ███████     ██   ██  ██████ ██   ██ ██   ██    ██     ██████  ██   ██ ██   ██");
        System.out.println("\033[1;36m");
        System.out.println(" ██████  █████  ███    ██     ███████ ███████ ██████  ██    ██ ███████ ████████     ███████ ██       ██████ ███████ \r\n" + //
                           "██      ██   ██ ████   ██     ██      ██      ██   ██ ██    ██ ██         ██        ██      ██      ██      ██ \r\n" + //
                           "██      ███████ ██ ██  ██     ███████ █████   ██████  ██    ██ █████      ██        █████   ██      ██      █████ \r\n" + //
                           "██      ██   ██ ██  ██ ██          ██ ██      ██   ██  ██  ██  ██         ██        ██      ██      ██      ██ \r\n" + //
                           " ██████ ██   ██ ██   ████     ███████ ███████ ██   ██   ████   ███████    ██        ███████ ███████  ██████ ███████ ");
        System.out.println("\033[0;35m");
        System.out.println("███    ██  █████  ██████   ██████  ██ ███████     ██   ██ ██    ██ ███████ ███████ ██    ██ ███    ██  ██████  ██    ██  █████  \r\n" + //
                          "████   ██ ██   ██ ██   ██ ██       ██    ███      ██   ██ ██    ██ ██      ██       ██  ██  ████   ██ ██    ██ ██    ██ ██   ██ \r\n" + //
                          "██ ██  ██ ███████ ██████  ██   ███ ██   ███       ███████ ██    ██ ███████ █████     ████   ██ ██  ██ ██    ██ ██    ██ ███████ \r\n" + //
                          "██  ██ ██ ██   ██ ██   ██ ██    ██ ██  ███        ██   ██ ██    ██      ██ ██         ██    ██  ██ ██ ██    ██  ██  ██  ██   ██ \r\n" + //
                          "██   ████ ██   ██ ██   ██  ██████  ██ ███████     ██   ██  ██████  ███████ ███████    ██    ██   ████  ██████    ████   ██   ██");
        System.out.println("\033[0;36m");
        System.out.println(" ██████  ██   ██  █████  ███    ██      █████  ██   ██ ███    ███ ███████ ████████     ███████  ██████  ██       █████  ██   ██ \r\n" + //
                          "██    ██ ██  ██  ██   ██ ████   ██     ██   ██ ██   ██ ████  ████ ██         ██        ██      ██    ██ ██      ██   ██ ██  ██  \r\n" + //
                          "██    ██ █████   ███████ ██ ██  ██     ███████ ███████ ██ ████ ██ █████      ██        ███████ ██    ██ ██      ███████ █████   \r\n" + //
                          "██    ██ ██  ██  ██   ██ ██  ██ ██     ██   ██ ██   ██ ██  ██  ██ ██         ██             ██ ██    ██ ██      ██   ██ ██  ██  \r\n" + //
                          " ██████  ██   ██ ██   ██ ██   ████     ██   ██ ██   ██ ██      ██ ███████    ██        ███████  ██████  ███████ ██   ██ ██   ██");
        System.out.println("\033[1;36m"); 
        System.out.println("███████  █████  ███    ███  ██  ██████      ███████ ██████  ████████ ██    ██ ██████  ██   ██ \r\n" + //
                           "██      ██   ██ ████  ████  ██  ██   ██     ██      ██   ██    ██    ██    ██ ██   ██ ██  ██  \r\n" + //
                           "███████ ███████ ██ ████ ██  ██  ██████      █████   ██████     ██    ██    ██ ██████  █████   \r\n" + //
                           "     ██ ██   ██ ██  ██  ██  ██  ██   ██     ██      ██   ██    ██    ██    ██ ██   ██ ██  ██  \r\n" + //
                           "███████ ██   ██ ██      ██  ██  ██   ██     ███████ ██   ██    ██     ██████  ██   ██ ██   ██");
        System.out.println("\033[1;35m"); 
        System.out.println("███████ ███████ ██    ██ ███    ██ ███████ ██████      ██████   █████  ███████  █████  ██   ██      █████  ██   ██ ████████  █████  ███████ \r\n" + //
                           "   ███  ██       ██  ██  ████   ██ ██      ██   ██     ██   ██ ██   ██ ██      ██   ██ ██  ██      ██   ██ ██  ██     ██    ██   ██ ██      \r\n" + //
                           "  ███   █████     ████   ██ ██  ██ █████   ██████      ██████  ███████ ███████ ███████ █████       ███████ █████      ██    ███████ ███████ \r\n" + //
                           " ███    ██         ██    ██  ██ ██ ██      ██          ██   ██ ██   ██      ██ ██   ██ ██  ██      ██   ██ ██  ██     ██    ██   ██      ██ \r\n" + //
                           "███████ ███████    ██    ██   ████ ███████ ██          ██████  ██   ██ ███████ ██   ██ ██   ██     ██   ██ ██   ██    ██    ██   ██ ███████");
        System.out.println("\033[0m"); 
        System.out.println("\n------------------------------------------------------------------------------------------------------------");
        
    }

    /**
 * Displays a welcome message with ASCII art in blue using ANSI escape codes.
 * The output consists of two sections of stylized text that introduce the project.
 * The related message is printed to the console.
 */  
    public static void display_welcome_msg() {
        System.out.println("\033[1;34m");
        System.err.println("██     ██ ███████ ██       ██████  ██████  ███    ███ ███████ \r\n" + //
                           "██     ██ ██      ██      ██      ██    ██ ████  ████ ██      \r\n" + //
                           "██  █  ██ █████   ██      ██      ██    ██ ██ ████ ██ █████   \r\n" + //
                           "██ ███ ██ ██      ██      ██      ██    ██ ██  ██  ██ ██      \r\n" + //
                           " ███ ███  ███████ ███████  ██████  ██████  ██      ██ ███████ ");

        System.out.println("\033[1;34m");
        System.err.println("██       ██████   ██████  ██ ███    ██     ███████  ██████ ██████  ███████ ███████ ███    ██ \r\n" + //
                           "██      ██    ██ ██       ██ ████   ██     ██      ██      ██   ██ ██      ██      ████   ██ \r\n" + //
                           "██      ██    ██ ██   ███ ██ ██ ██  ██     ███████ ██      ██████  █████   █████   ██ ██  ██ \r\n" + //
                           "██      ██    ██ ██    ██ ██ ██  ██ ██          ██ ██      ██   ██ ██      ██      ██  ██ ██ \r\n" + //
                           "███████  ██████   ██████  ██ ██   ████     ███████  ██████ ██   ██ ███████ ███████ ██   ████");


    }

    /**
 * Displays a success message with ASCII art in green using ANSI escape codes.
 * Related message is printed to the console.
 */
    public static void display_success_msg()
    {
        System.out.println("\033[1;32m");
        System.out.println("██       ██████   ██████  ██ ███    ██     ███████ ██    ██  ██████  ██████ ███████ ███████ ███████ ███████ ██    ██ ██      ██      \r\n" + //
                           "██      ██    ██ ██       ██ ████   ██     ██      ██    ██ ██      ██      ██      ██      ██      ██      ██    ██ ██      ██      \r\n" + //
                           "██      ██    ██ ██   ███ ██ ██ ██  ██     ███████ ██    ██ ██      ██      █████   ███████ ███████ █████   ██    ██ ██      ██      \r\n" + //
                           "██      ██    ██ ██    ██ ██ ██  ██ ██          ██ ██    ██ ██      ██      ██           ██      ██ ██      ██    ██ ██      ██      \r\n" + //
                           "███████  ██████   ██████  ██ ██   ████     ███████  ██████   ██████  ██████ ███████ ███████ ███████ ██       ██████  ███████ ███████");

    }

    /**
 * Displays an error message with ASCII art in green using ANSI escape codes.
 * Related message is printed to the console.
 */
    public static void display_error_msg()
    {
        System.out.println("\033[1;31m");
        System.out.println("███████ ██████  ██████   ██████  ██████  ██ \r\n" + //
                           "██      ██   ██ ██   ██ ██    ██ ██   ██ ██ \r\n" + //
                           "█████   ██████  ██████  ██    ██ ██████  ██ \r\n" + //
                           "██      ██   ██ ██   ██ ██    ██ ██   ██    \r\n" + //
                           "███████ ██   ██ ██   ██  ██████  ██   ██ ██");

    }

     /**
 * Displays the failure message with ASCII art in green using ANSI escape codes.
 * Related message is printed to the console.
 */
    public static void display_Fail_msg()
    {
        System.out.println("\033[1;31m");
        System.out.println("██       ██████   ██████  ██ ███    ██     ███████  █████  ██ ██      ███████ ██████  \r\n" + //
                           "██      ██    ██ ██       ██ ████   ██     ██      ██   ██ ██ ██      ██      ██   ██ \r\n" + //
                           "██      ██    ██ ██   ███ ██ ██ ██  ██     █████   ███████ ██ ██      █████   ██   ██ \r\n" + //
                           "██      ██    ██ ██    ██ ██ ██  ██ ██     ██      ██   ██ ██ ██      ██      ██   ██ \r\n" + //
                           "███████  ██████   ██████  ██ ██   ████     ██      ██   ██ ██ ███████ ███████ ██████ ");

    }

    /**
 * Displays a logout message with ASCII art in green using ANSI escape codes.
 * The related message is printed to the console.
 */
    public static void logging_out()
    {
        System.out.println("\033[1;32m"); 
        System.out.println("██       ██████   ██████   ██████  ██ ███    ██  ██████       ██████  ██    ██ ████████\r\n" + //
                           "██      ██    ██ ██       ██       ██ ████   ██ ██           ██    ██ ██    ██    ██   \r\n" + //
                           "██      ██    ██ ██   ███ ██   ███ ██ ██ ██  ██ ██   ███     ██    ██ ██    ██    ██   \r\n" + //
                           "██      ██    ██ ██    ██ ██    ██ ██ ██  ██ ██ ██    ██     ██    ██ ██    ██    ██   \r\n" + //
                           "███████  ██████   ██████   ██████  ██ ██   ████  ██████       ██████   ██████     ██ ");
        System.out.println("\033[0m"); 

    }

    /**
 * Displays an ASCII art image with a red color using ANSI escape codes.
 * The image is printed to the console and consists of a pattern or graphic made up of various characters.
 */
    public static void image3() {
                        System.out.println("\033[1;31m");
                            System.err.println(
                            
                                    "                         :*:            :-.                                                         \n" +
                                    "                          =*.           -*.                                                         \n" +
                                    "                           .+.           =.                                                         \n" +
                                    "                            :=           =.                                                         \n" +
                                    "                            .*:          +.                                                         \n" +
                                    "                             -=         +-                                                           \n" +
                                    "                             -=       .==                                                            \n" +
                                    "                        ...-=*+-...  .=-                                                 \n" +
                                    "                       :*#*******#*+:+.                                                              \n" +
                                    "                     .+%=::-*#*******+.  .:-=+******+=--:.                                           \n" +
                                    "                     -%.+*+::****+=+*##********###**********+-:.                                    \n" +
                                    "                    .#%:.-  :*#+:**=.*%****########************##+..                                \n" +
                                    "                    .%%%#**#%%%+.+...+%*+++++++++**########*****####+.                               \n" +
                                    "                    .#%%%*%%%%%%#=:=#%#++++++++++++++++++++*####******=.                            \n" +
                                    "                     :%%%%*#%%##%%%%%#++++++++++******+++++++++*###*****=.                          \n" +
                                    "                      -%%%%%%%%%%%%%#++++++++++********+++++++++++*##****+.                         \n" +
                                    "                        =%%%%%%%%%*++++++++++++*#******++++++++++++++##****-                        \n" +
                                    "                          .:++++======+++++++++++*##**+++++++++++++++++##***:                       \n" +
                                    "                           -=========+++=+++++++++++++++++++++++++++++++##***:                      \n" +
                                    "                           -+======+######====++++++++++++++*###+++++++++*##*=.                     \n" +
                                    "                           :*+=====*######+==========++++++#######+++=====+#*+.                     \n" +
                                    "                            -**++==+%#####================+#######=========+#+.                     \n" +
                                    "                            +=:=**++=+++===================+####*+==========.                       \n" +
                                    "                            -*. ..-+***++=============================+++*+:                        \n" +
                                    "                           ..++.    .+*++****++++============++++****++*=.                          \n" +
                                    "                           .:..     .+#.   .-=****************+=-:.   .=*-                          \n" +
                                    "                                   .:-#-       :#+ ......   -%.         :**.                        \n" +
                                    "                                   ..::.        -#:         .**.       .-:                          \n" +
                                    "                                               ***+         =*+.                                     "
                            );
                
                    }
                    

    /**
 * Prints a complex pattern of Unicode characters to the console, 
 * including colored blocks and symbols, to display a stylized image.
 * The image is made up of various emojis and Unicode characters to create an artistic display.
 * The method uses a mix of colors and patterns to generate a visual effect in the console.
 */
    public static void image()
    {
        System.out.println("⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛⬛️⬜️⬜️⬜️⬜️⬜⬜️⬜️️");
        System.out.println("🟥🟥⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️🟥🟥🟥🟥🟥🟥🟥🟥⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧⬛️⬜️⬜️⬜️⬜️⬜⬜️️️");
        System.out.println("🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥⬛️🥧🥧🥧💟💟💟💟💟💟💟💟💟💟💟💟💟🥧🥧🥧⬛️⬜️⬜️⬜️⬜⬜️️️");
        System.out.println("🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥⬛️🥧🥧💟💟💟💟💟💟🍓💟💟🍓💟💟💟💟💟🥧🥧⬛️⬜️⬜️⬜️⬜️⬜️️️");
        System.out.println("🟧🟧🟥🟥🟥🟥🟥🟥🟥🟥🟧🟧🟧🟧🟧🟧🟧🟧🟥🟥🟥🟥🟥🟥🟥⬛🥧💟💟🍓💟💟💟💟💟💟💟💟💟💟💟💟💟💟🥧⬛️⬜️⬜️⬜️⬜⬜️️️");
        System.out.println("🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧⬛️🥧💟💟💟💟💟💟💟💟💟💟⬛️⬛️💟💟🍓💟💟🥧⬛️⬛️️⬛️️⬜⬜️️️");
        System.out.println("🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧🟧⬛️🥧💟💟💟💟💟💟💟💟💟⬛️🌫🌫⬛💟💟💟💟🥧⬛️⬛️🌫🌫⬛⬜️️️");
        System.out.println("🟨🟨🟧🟧🟧🟧🟧🟧🟧🟧🟨🟨🟨🟨🟨🟨🟨🟨🟧⬛️⬛️⬛️⬛️🟧🟧⬛️🥧💟💟💟💟💟💟🍓💟💟⬛️🌫🌫🌫⬛💟💟💟🥧⬛️🌫🌫🌫⬛⬜️️️");
        System.out.println("🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛️🌫🌫⬛️⬛️🟧⬛️🥧💟💟💟💟💟💟💟💟💟⬛️🌫🌫🌫🌫⬛️⬛️⬛️⬛️⬛️🌫🌫🌫🌫🌫⬛⬜️️️");
        System.out.println("🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛️⬛️🌫🌫⬛️⬛️⬛️🥧💟💟💟🍓💟💟💟💟💟⬛️🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫⬛⬜️️️");
        System.out.println("🟩🟩🟨🟨🟨🟨🟨🟨🟨🟨🟩🟩🟩🟩🟩🟩🟩🟩🟨🟨⬛⬛️🌫🌫⬛️⬛️🥧💟💟💟💟💟💟💟🍓⬛️🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫⬛️️");
        System.out.println("🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩⬛️⬛️🌫🌫⬛️🥧💟🍓💟💟💟💟💟💟⬛️🌫🌫🌫🌫⬜️⬛️🌫🌫🌫🌫⬜️⬛️🌫🌫🌫⬛️️");
        System.out.println("🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩⬛️⬛️⬛️⬛️🥧💟💟💟💟💟💟💟💟⬛️🌫🌫🌫🌫⬛️⬛️🌫🌫🌫🌫⬛️⬛️🌫🌫⬛️️");
        System.out.println("🟦🟦🟩🟩🟩🟩🟩🟩🟩🟩🟦🟦🟦🟦🟦🟦🟦🟦🟩🟩🟩🟩🟩🟩⬛️⬛️🥧💟💟💟💟💟🍓💟💟⬛🌫🌫🟥🟥🌫🌫🌫🌫🌫🌫🌫🌫🟥🟥🌫⬛️️");
        System.out.println("🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦⬛️🥧🥧💟🍓💟💟💟💟💟⬛️🌫🌫🟥🟥🌫🌫🌫⬛️🌫🌫🌫🌫🟥🟥🌫⬛️️");
        System.out.println("🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦⬛️🥧🥧🥧💟💟💟💟💟💟💟⬛️🌫🌫🌫⬛⬛️⬛️⬛️⬛️⬛️🌫🌫🌫⬛️⬜️️");
        System.out.println("🟪🟪🟦🟦🟦🟦🟦🟦🟦🟦🟪🟪🟪🟪🟪🟪🟪🟪🟦🟦🟦🟦🟦🟦⬛️⬛️⬛️🥧🥧🥧🥧🥧🥧🥧🥧🥧🥧⬛️🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫🌫⬛️⬜️⬜️️");
        System.out.println("🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪⬛️🌫🌫🌫⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬛️⬜️⬜️⬜️️");
        System.out.println("🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪⬛️🌫🌫⬛️⬛️⬜️⬛️🌫🌫⬛️⬜️⬜️⬜️⬜️⬜️⬛️🌫🌫⬛️⬜️⬛️🌫🌫⬛️⬜️⬜️⬜️⬜️️");
        System.out.println("⬜️⬜️🟪🟪🟪🟪🟪🟪🟪🟪⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬜️🟪🟪🟪🟪🟪⬛️⬛️⬛️⬛⬜️⬜️⬛️⬛️⬛️⬜️⬜️⬜️⬜️⬜️⬜️⬜️⬛️⬛️⬛️⬜️⬜️⬛️⬛️⬜️⬜️⬜️⬜️⬜️️️");
            
    }

  /**
* Prints a special acknowledgment message thanking a list of individuals.
* The method displays the list of people in a formatted way and includes
* a visual acknowledgment image, followed by a thank you message in blue text.
*/
    public static void acknowledment()
    {
        String[] thanksList = 
        {
            "Asst. Prof. Dr. İlktan Ar",
            "Edin Koç",
            "Göktuğ Ateş",
            "Mehmet Kuzucu",
            "Miray Köksal",
            "Ozan Kutlar",
            "Tuna Zeyneloğlu"
        };

        System.out.println("*********************************************");
        System.out.println("*               Special Thanks To           *");
        System.out.println("*********************************************");

        for (String name : thanksList) {
            System.out.println("*   " + name);
        }

        System.out.println("*********************************************");
        System.out.println("*       We appreciate your support!         *");
        System.out.println("*********************************************");

        image2();
        System.out.println("\u001B[34m%-15s\u001B[0m\t"); 
    }


/**
* Prints a special acknowledgment image of the instructor
*/
    public static void image2(){
        System.out.println("                                                     .:jEgDRQRbEMMgMgDZQZRBMgBBK:.");
System.out.println("                                                       :vbRBBBQZDBQBRZgQMRRBdPgMqEDgEdQQQBgZv:");
System.out.println("                                                  i5BBBBBBBBBBBdqZBQBQBQQMBQZEgBQdZZDbggQRBBBBBBBXr");
System.out.println("                                              :KBBBBBBQBBRDQBBBBDgXUPgEEgDbbPMDgPQDdPgdZZgdgQBBBBBBBBPi");
System.out.println("                                           rMBBQBBBQBBBBBBBdqPgQBRMPKDQdKXSuI2KqbgQQQQQBBMRdPqddRQBBBBBBu.");
System.out.println("                                        iMQBQBZPKXSSXqSjvvss7LjSqEPbSu7::i7v77ir7v1ZgQEgDgggPX2USgBBQQBBgX..");
System.out.println("                                     .KBBBQDPUu77r77vYsri:::irYss7r:::i:::iiiiiii::isj1vvsUuujI1XPRQBRRdZZ: .:.");
System.out.println("                                   iQBBBZqI2Ls77riiiiririrr7irrr:....:iiii:::::i:i:ii77ri77L7LsjUPgBQZ2XdB5  ..:..");
System.out.println("                                 7BBBBBQE5UjJv7r7r7rr::::::.........:.:::.:::.::::i:irvr7r7r77sJIXMgPPKPqBB: .....:.");
System.out.println("                               rBBBBQBQEUujY7vr7r777i:::::::::::.:::.:.:.:.:.::::::::rrrr7r77vvJ2KDQPbSQQBB7 ......:..");
System.out.println("                             :BBBBMMQgb2jsu7rr7rrri:i:i.:.:::::.:.:.......:....:i:::iirrrrrr77LjIUqddEZEQMBs  ........: ");
System.out.println("                            qBQQBBZZdqujJsLvr7riiri::i:::i::::.:.......:.:::::::::iiiririrrr7v7YsKqqKdPEDMRJ ............ ");
System.out.println("                          .YBBgZQQQZdXuLLvvrrrririririiii:i:i::::.:.::::::::i:iiiiriiiriii7rvvv7JXDggEEdDBB7  ............. ");
System.out.println("                         ..rZQDMQQQQbqUjvLv7irrrrririiii:::::i::.::::::i::::iiii:irri77rrrrrrvvLYSEMQBggDgB2 ............... ");
System.out.println("                        .. idQgDQMBQgXIYYvL77r777rrii::::::.:::::::::::::::::.::::irirrr7rri7777sJqgBQBMBdBK  ..............:.");
System.out.println("                      .... iPBMDgBQBRqusYv77rrr777ii::.:.:.:.:::::::::...:.:.:.::::i:iiiir7v7svL2gQQQBBBBJ ................:.");
System.out.println("                     ....  .uQQDRQQRM5JYY7777r7rr::::.:.....:::...:.:................:::iiii7vvYvLJPBBBQQBBi  ................:.");
System.out.println("                    ...... .rQRgQMQBD5vJsY77rri:..........................    .......::irLv7rvsvYJLIMQQQQBg: ....................");
System.out.println("                   .......  :dBggDQQRUsJuLvrriiiiii:i:......  ... ... .   ...:r7J1SX51PEDZZbdSUsJ7vvERBQBBI  .....................");
System.out.println("                  ........  .vQBZRMBEuJuuIIXXPPgRMbdbIsJ7:.:.............i77LPKPDZdMQgPgZZDMMBMqYL7YIMQBBD:  . ....................");
System.out.println("                 .... .....  .KQBZMBEL1XgQBBBQQgQMDZMgRdP2Lrr:........:ir72JXZPK2L7ir::::::rjKgDSUYvuDBQRr  .  ...................:.");
System.out.println("                 ..........   igQMMBKjSQQMKu7rii:i::rJI5SXsvri:......::r7jJsuUu2jSI1uJsL7rii:i711UY7vXMBP.:Ls1i. ....................");
System.out.println("                ........... . .PBRRQPJIPU7r:::rr7v2IX1JY1j21J7r::....:r7s7LLvi7iYBdIBBI5EXIL7ir7J77vLjgBqir7vL7........ ............:.");
System.out.println("               .............   2bYUQKvLULv7vuPPu1BBugB...vUssIj7::..::i77rrsri.  MgEBI .XDKUL7r7rvvv7sSQY:i7r::......................: ");
System.out.println("              ... ...........:IRqruRqrLjjvsIMR1  PBKgg. :vUL712r:::.iir77rr7vv7rii77j7L1SKSuJ77r7r77vL5gUi:iL.:.......................: ");
System.out.println("              .... ... . .. .JBBb:sQS7Lv7vYSPb5IrL1viriY7rivU5sr...::rrvvvrrir77r..:i77vvL777vriiir7rLuZqr.:ri::........................ ");
System.out.println("             ............... .PBX LdUvvr7rLJ1sYL7i::.:iirirL5sr::...::rrY77r77v77:::::iiiiriririrr7777YSdv::rr::........................ ");
System.out.println("             ............. . .qBu YguY77r777rrriii:i:::rrrrLJvi:...::::ii7riii:ii:.:.::i:iiiiiiri77v77LKdr..:rii ......................:. ");
System.out.println("            ... . .........  :gBb.UdUv777rvri:i::::.:irii:rvYr:.....:i:::ii::...:.:.:.::i:::i:iirrr7s7Y5Kr. .:7. .......................: ");
System.out.println("            ................ .KBQv2Dsu7v77rrii:::i:::i::.:rjv7i:...:::i:::ii::.:.:::.:::::::::::rrr77LsIJi.  :i. .........................");
System.out.println("            . ......... . ..  YBBrrP5vYvL7rii:i::::::::.:i7LY77i:.::::rrr:i:::....::.::.:.::::iiir7vLvujUi..:ri........................... ");
System.out.println("           ..... ......... .  vBBi.USsvs7rii:i::::.:::..:r7Jv7ri.:.::ir7rrr:::.:...:.....:::.:iiirrv7Ysu7:ivrv: .........................: ");
System.out.println("           ................   uBBr.2SsYvvrrii::::::::::.:i1jvrr::::.::rr77s7:.........:.:.::::iiir7rLsU5sr7r7i.  ..........................");
System.out.println("           ................. .sBBq:1SJsvrriii:::.::......7sr:i::.....::iiirYi....:...:.:...:.::iirr7YsjS7::Lr:.............................");
System.out.println("           ........ ...... .. rQBB5vXLu77rrrrii.:::......vvii::..   ..::i.:77............:::::::rr77JY2jr:rJ7..............................");
System.out.println("           ..... ...........   7BBP71SJvr7rrii::::.......777Lvr:.....rrrirr7i......:...:.::::iiiir7Lvu2sr7rv...............................");
System.out.println("          ............... ...  .dBP:YIUL777iri:::.:.....::rrr7Yv7r77r:::iir::...........::::ririrrvvY1Kv777:  ............................. ");
System.out.println("          .. ......... .....   :PBML7XUs77r7rri:.:.....:::i::rirrrii:.:ri:.:.:.:::...:.:::::irirr77sYU1v:. ................................ ");
System.out.println("           ................... .1BBBPbIvY7777ri::::::::iiiiiriii:::ii:iii:i:i:::::..::::::iiiirrr7vvuqL   ................................. ");
System.out.println("           ... ... ...........   rgBBB2LLvrririii::::i:riiirii::.::ii::::i:::i:::::::::::::iiii7r77L1P:  ................................:. ");
System.out.println("           .... ......... . ...   7QBZdYL7vr7riiriiii:iii:::::::........:.....::::::iiiiiiirrirrrvsLXU. ................................... ");
System.out.println("           ......... ...........  igQQgUYv7vrrrrirri::r7rrrv7Jss7vi:i7LY7Yvrii:i::::irii:iiririrr7v2S7 ...................................  ");
System.out.println("           .... ......... ......  .JBBQKJ7vv7rririri::rsISqIIS511552S52155S5PKqXSLi:iii:iiiirrrrvrJ2I. ...................................  ");
System.out.println("           ......................  7MBQQ1j7L77r7iiiriiii7JJUYvr7rrr77v777vr7u2svrrrii:::irirrrr77Jud7. ..................................:  ");
System.out.println("            ................. . .. .XBBQE1Yvvr777iiii::.::rvY77rr::::::.:r77ri::::::i:::rrrr7r77L1Ku. .................................... ");
System.out.println("            .. ...................  iQBBQPsvv77vrrrr:::::::::rrv77v7r7r7rri:..:i:..:irirrrrr777suKS: ....................................  ");
System.out.println("            ....................... .rBBQQKvLvLv7r77r:::::i::::..:SZE7i:::..::::::iirirr7r77vLv1Z5:  ....................................  ");
System.out.println("             ............. ........  .YBQBQPUJvLY7777rii:i::...:.iuEqv::...:::::irirrrrrr7vY7YIDU: ....................................:   ");
System.out.println("             .............. .....     .SQMBBguvvLvL77rrii::.:.::::i::i:::.:...::iiirrr7r7vv7IXKr  ....................................:.   ");
System.out.println("              ..................    iKDQggRBQgXUvsYY77rrri::..:ir:.....::::..::riiirr7rvYYJKqqi   .....................................    ");
System.out.println("              .................  .uMBBQMQQMBgSgZP2uYLvsJUvrrr:r7si:r7iii7i:.irr7sj1v7LsJUIbS51:  ......................................    ");
System.out.println("               ...............  .EBBBQgRQBQBPLudMgKI1II5u1uJJYrLIIj1U11LYjiiYsvLv1XXJ2SEPPujLXDs.  ..................................:      ");
System.out.println("                .............  .EBQgBRRgQQBQqYY7UPMZPZMPIUSuI1Y7uv7LSLvJYrJj52KuqPMMdPdK5usuu5BBBv   ...................... ........:       ");
System.out.println("                 .........    igBBMRRQMQQMEgSJv1YvubbDQBRgDZu5PIuuvUbsv2js2ZqPdgBBBQbXjJsjvssPQQBBD: ..............................:.       ");
System.out.println("                  :.        i2BBBZRRQMRgRgMdUYJvLJsvuKgBBBBBZgBgRDEPdSEgDgQQQBBQBQqYJLLLLvYsjPBMQMBQ:  ...........................:.        ");
System.out.println("                   .   .iJIgBBBBBgMMgBQRMQQQdJ777v7L7LJXbBBBBBBBBBQBBBBBBBBBBBQqsu77vLvJYYJj1DBBMQRBM.  .........................:.         ");
System.out.println("                    rjDBBBBBBQBQgDBQQQRMQRRRBMIrr77r77v77JS2PdgQBBBQBQQMRPqI1v7777vvJYsLYYsL2XDBMMQQBBr   ......................:.          ");
System.out.println("                    7BBBBBQQQQRRMBQBQQMQQQRQRBBQILrii7rLv7vv7vrY7vr7rrrriri7irrvvvvLvvvY7LYJ2EEMgMMRQBB1    ...................:.           ");
System.out.println("                     :BBBgQgQQQQQQQgQDQQRMBRggBBBQgur:ii77v7Y7rrirrii:rirr7r77vvYYv7srr7vvY1dRQgMQQRQQBBg7.     ..............:             ");
System.out.println("                       BBBBBQBRQgQQQgMMQgQBQgQQQDBBBBg2riri777rr7v777v7v7v7v7v7r7rrrrrsKRQQgMMQRQMQQBBBBMv.   ............:               ");
System.out.println("                        KBBQQQQMQQQMQQQQRRBMRQQgQBQQBBBQQZPY7vLrrrirvvv77rrriii::::::ivJdRBQRZMRQMRMQRBRQBBQBBRv   ..........                ");
System.out.println("                         :BBBBQQQQDQQBMBQQQMgBRRQQgQDQQBBBBBgBQP1K17rr7r:i7sJXKXsU5XqMQBBBRRRQMQQQMQQQgQQQgMQBQBQr     ..:.                  ");
System.out.println("                           XBQBDQRRQQMDRQRBgQBRQQQQMQRQQQQQQBMBBBBBQY:r:r5gQBBBBBBBBBBQMQQRQMgQgQQQgBRMRBMRMQMQQBBQui.  ..                   ");
System.out.println("                            .BBBQBQQMQQQRRQRMBRQQBMQgBQgQBMQgggBgRQBDv:rSBBBMQQQDBQMgBQQMQgBRMQRQBRQMQQQQRQRRMRQQBBBBBBX                     ");
System.out.println("                              iBBBBMBQQRRgQQBQQgQQRQQBQgQQMgRgRQgZBBSi.UQQQgRQQDMQQMRMRMQRRQQRQQQMgBQRQRRgQQgQBgQRBQBBq                      ");
System.out.println("                                7BBBBBRQQQDQQQgQQQgMQQRQRQMRQQRgQgBS  P.rBRgMQQRMQMRMBQQQBgRQBMQBgRQgBQBRRgRDRQBQBBB5                       ");
System.out.println("                                  rQBBBBBQQQQMRQBQQgQQBMRRBQQQBMRgBK  X .gBMQRQMMQBgQRQQQQQgQQQRQQQRMRQMQQQQBBBBBBU                        ");
System.out.println("                                    :gBBBBBQgQQBQQRQRQQQQQRQMQQQgMBBQYIQBBQBRQQRgBQQRQRQMQQQgQQQMQQQQQQQMQQBBBBBr                          ");
System.out.println("                                       sBBBQBQBQRgQQQQQgBQQDQQQBQQBQY.iIBBQRQRQQQMQQMQBgQMBRQQQQRgBgQRQQBBBBBX.                            ");
System.out.println("                                         .2BBBBBQBBBQQMQgBQQRQQQgBBg ...QBQBBQgQQRgRQQQRDMQBgQRBRQQBQBBBBBK:                               ");
System.out.println("                                            .JBBBBBQBRQQQRQMMQQRBZ: 7PPYiqBQQQQgQQQMQRQMMMBQQRBBQBBBBBB5:                                  ");
System.out.println("                                                :KBBBBBBBBQBQBQBB: jQBQBqrQQRQRQgQQQRQQBQBQBBBBBQBBdr                                      ");
System.out.println("                                                    :vEBBBBQBBBBB7 BBBBB7 JBQgRRQQQMBQBBBBBBBBMJi                                          ");
System.out.println("                                                           :rj2bQgrrKdEL .PBQQQQQQMgEE51r:.                                               ");

    }
}

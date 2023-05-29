import java.io.IOException;

public class ATM {
    public static void main(String[] args) throws IOException {
        OptionMenu option = new OptionMenu();
        introduction();
        OptionMenu.mainMenu();
    }
    public static void introduction()
    {
        System.out.println("WELCOME TO THE ATM PROJECT!");
    }
}

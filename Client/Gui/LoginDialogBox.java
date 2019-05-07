package Client.Gui;

import Communication.Messages.Message;
import Infomation.Machine;

import javax.swing.*;

/**
 * Created by mark on 15/05/15.
 */
public class LoginDialogBox {
    private static final String login = "Do you wish to login as an existing user\n or register a new one?";
    private static final String[] loginOptions = {"Existing User Login", "Register as a new User", "Quit"};

    private LoginDialogBox() {
    }


    public static void showLoginRequest(Machine instance) {
        int n = JOptionPane.showOptionDialog(new JFrame(), login , "login/Regiester",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, loginOptions, loginOptions[2] );

        switch (n) {
            case 0:
                new ExistingUserLogin(instance);
                break;
            case 1:
                new NewUserRegistration(instance);
                break;
            case 2:
                instance.exit();
        }
    }

}

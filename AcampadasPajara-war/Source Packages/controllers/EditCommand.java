/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author W7
 */
public class EditCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            forward("/FrontController?command=GetInitialDataCommand");
        } catch (ServletException ex) {
            Logger.getLogger(EditCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

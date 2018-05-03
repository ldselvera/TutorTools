/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author beths
 */
public class mailController {

    MailView mv = new MailView();
    // SMTP info
    String host = "smtp.gmail.com";
    String port = "465";
    String mailFrom = "tutor.tools.01@gmail.com";
    String password = "TutorToolsadmin";
    File[] attachFiles = null;

    public mailController(MailView mv) {
        this.mv = mv;
        attachHandlers();
    }

    public void attachHandlers() {

        mv.getSend().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // message info
                String mailTo = mv.getToTF().getText();
                String subject = mv.getSubject().getText();
                String message = mv.getMessage().getText();
                String ccemail = mv.getCcTF().getText();

                // CC emails
                ArrayList<String> ccEmails = new ArrayList<>();
                ccEmails.add(ccemail);

                try {
                    EmailAttachmentSender.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                            subject, message, attachFiles);
                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Could not send email.");
                    ex.printStackTrace();
                }
               try {
                    EmailAttachmentSender.sendEmailWithAttachmentsCC(host, port, mailFrom, password, mailTo,
                            subject, message, attachFiles, ccEmails);
                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Could not send email.");
                    ex.printStackTrace();
                }
            }
        });

        mv.getBrowseButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select file");
                fileChooser.setInitialDirectory(new File("C:\\Users\\beths\\Desktop"));
             
               List <File> files = fileChooser.showOpenMultipleDialog(stage);
         
                attachFiles = new File[files.size()];
                files.toArray(attachFiles);
                
         
        }             
        });
        }  
}
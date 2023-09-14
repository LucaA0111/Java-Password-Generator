package com.example.passwordgenerator.views;


import com.example.passwordgenerator.backend.Generator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("PasswordGenerator")
@Route(value = "passwordGenerator")
@RouteAlias(value = "")
public class PasswordGeneratorView extends HorizontalLayout {
    VerticalLayout page;
    VerticalLayout title;
    Image logo;
    VerticalLayout field;
    List<String> pswType;
    ComboBox<String> passwordType;
    IntegerField length;
    int len;
    Button generate;
    Button newPassword;
    Button clearAll;
    Button info;
    Button close;
    Dialog information = new Dialog();
    TextField generatedPassword;
    ArrayList<String> allPassword = new ArrayList<>();
    Generator pg = new Generator();

    public PasswordGeneratorView() {

        //Component configuration
        page = new VerticalLayout();

        title = new VerticalLayout();
        logo = new Image();
        logo.setSrc("images/logo-pg.svg");
        logo.setWidth("500px");
        logo.setHeight("300px");
        title.add(logo);
        title.setJustifyContentMode(JustifyContentMode.CENTER);
        title.setAlignItems(Alignment.CENTER);
        newPassword = new Button("Generate new password");
        newPassword.setVisible(false);
        newPassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newPassword.setIcon(VaadinIcon.REFRESH.create());
        generate = new Button("Generate password");
        generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        generate.setIcon(VaadinIcon.KEY.create());
        clearAll = new Button("Reset all");
        clearAll.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        clearAll.setIcon(VaadinIcon.TRASH.create());
        info = new Button();
        info.setIcon(VaadinIcon.INFO_CIRCLE.create());
        close = new Button();
        close.setIcon(VaadinIcon.CLOSE_CIRCLE.create());
        field = new VerticalLayout();
        pswType = Arrays.asList("Numeric","Text","Alphanumeric","Complete");
        passwordType = new ComboBox<>("Type of password",pswType);
        length = new IntegerField("Password length");
        length.setMax(20);
        length.setMin(8);
        length.setStep(1);
        length.setValue(8);
        length.setStepButtonsVisible(true);
        length.setRequired(true);
        generatedPassword = new TextField("Password");
        generatedPassword.setReadOnly(true);
        generatedPassword.setVisible(false);
        field.add(passwordType,length,generate,generatedPassword,newPassword,clearAll);



        newPassword.addClickListener(e->{
            len = length.getValue();
            if(len < 8 || len > 20) {
                Notification.show("Password length is not valid", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (passwordType.getValue() == null || length.getValue() == null || len == -1) {
                Notification.show("Check the entered data and try again", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else {
                int type = passwordType(passwordType.getValue());
                passwordGenerator(type, len);
            }

        });

        generate.addClickListener(e ->{
            len = length.getValue();
            if(len < 8 || len > 20) {
                Notification.show("Password length is not valid", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else if (passwordType.getValue() == null || length.getValue() == null || len == -1) {
                Notification.show("Check the entered data and try again", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else {
                int type = passwordType(passwordType.getValue());
                passwordGenerator(type, len);
                generatedPassword.setVisible(true);
                newPassword.setVisible(true);
                generate.setVisible(false);
            }
        });

        clearAll.addClickListener(e->{
            generate.setVisible(true);
            allPassword.clear();
            newPassword.setVisible(false);
            generatedPassword.clear();
            generatedPassword.setVisible(false);
            length.clear();
            passwordType.clear();
            length.setValue(8);
        });

        info.addClickListener(e-> information.open());

        close.addClickListener(e-> information.close());

        configureField();
        configureDialog();

        page.add(title,field);

        add(info,page);
    }

    private int passwordType(String passwordType){
        return switch (passwordType) {
            case "Numeric" -> 1;
            case "Text" -> 2;
            case "Alphanumeric" -> 3;
            case "Complete" -> 4;
            default -> -1;
        };
    }

    private void passwordGenerator(int type, int length){
        if(type == 1){
            generatedPassword.setValue(pg.numeric(length));
            allPassword.add(pg.numeric(length));
        } else if (type ==2) {
            generatedPassword.setValue(pg.text(length));
            allPassword.add(pg.text(length));
        } else if (type ==3) {
            generatedPassword.setValue(pg.alphanumeric(length));
            allPassword.add(pg.alphanumeric(length));
        } else if (type == 4) {
            generatedPassword.setValue(pg.all(length));
            allPassword.add(pg.all(length));
        }
    }


    private void configureField(){
        passwordType.setWidth("500px");
        passwordType.addThemeVariants(ComboBoxVariant.LUMO_ALIGN_CENTER);

        length.setHelperText("Enter a value between 8 and 20");
        length.setWidth("500px");

        generatedPassword.setWidth("500px");
        generatedPassword.setHeight("50px");
        generatedPassword.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        generate.setWidth("500px");
        clearAll.setWidth("500px");
        newPassword.setWidth("500px");

        field.setJustifyContentMode(JustifyContentMode.CENTER);
        field.setAlignItems(Alignment.CENTER);

        page.setAlignItems(Alignment.CENTER);
        page.setJustifyContentMode(JustifyContentMode.CENTER);

    }

    private void configureDialog(){
        information.setCloseOnEsc(true);
        HorizontalLayout header = new HorizontalLayout();
        H2 headerTitle = new H2("INFO");
        header.setAlignItems(Alignment.CENTER);
        header.setJustifyContentMode(JustifyContentMode.START);
        header.add(headerTitle,close);
        Paragraph text = new Paragraph("Password Generator allows you to randomly generate numeric, text, alphanumeric and alphanumeric with symbols passwords from 8 to 20 characters.");
        information.add(header,text);
    }


}

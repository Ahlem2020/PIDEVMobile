/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import com.codename1.ui.Calendar;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Event;
import com.mycompany.services.ServiceEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author ahlem
 */
public class AddEvent extends BaseForm {

    Form current;

    public AddEvent(Resources res) {
        super("NewsFeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Evénement");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("event.png"), "", "", res);
        // deb 
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("List", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Categorie", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        partage.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           AddEvent a = new AddEvent(res);
            a.show();
            refreshTheme();
        });
        
        
            mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           ListEvent a = new ListEvent(res);
            a.show();
            refreshTheme();
        });

                   liste.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           AddCat a = new AddCat(res);
            a.show();
            refreshTheme();
        });
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonselection(mesListes, arrow);
        bindButtonselection(liste, arrow);
        bindButtonselection(partage, arrow);
        //   special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        //fin
        TextField nomEvent = new TextField("", "Nom Evénement");
        nomEvent.setUIID("TextFieldBlack");
        addStringValue("Nom : ", nomEvent);
        TextField descriptionEvent = new TextField("", "Description");
        descriptionEvent.setUIID("TextFieldBlack");
        addStringValue("Description : ", descriptionEvent);

        TextField isactive = new TextField("", "Status: 0-1 ");
        isactive.setUIID("TextFieldBlack");
        addStringValue("Status", isactive);
        
        Picker dateDeb = new Picker();
        dateDeb.setType(Display.PICKER_TYPE_DATE);
        
        dateDeb.setUIID("TextFieldBlack");
        addStringValue("Date Début ", dateDeb);

        Picker dateFin = new Picker();
        dateFin.setType(Display.PICKER_TYPE_DATE);
        
        dateFin.setUIID("TextFieldBlack");
        addStringValue("Date Fin ", dateFin);

        TextField nbrParticipant = new TextField("", "Nombre de Participant");
        nbrParticipant.setUIID("TextFieldBlack");
        addStringValue("Participant ", nbrParticipant);

        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        Button btnAnnuler = new Button("Annuler");
        addStringValue("", btnAnnuler);

        btnAnnuler.addActionListener((e) -> {
            Resources theme;
            theme = UIManager.initFirstTheme("/theme");
          
            new ListEvent(theme).show();
        });

        btnAjouter.addActionListener((e) -> {
            try {
                if (nomEvent.getText().equals("") || descriptionEvent.getText().equals("") || isactive.getText().equals("") || nbrParticipant.getText().equals("")) {
                    Dialog.show("Erreur", "Veuillez vérifier les données ", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    Event E = new Event();
                    int a = Integer.valueOf(isactive.getText());
                    int nbr = Integer.parseInt(nbrParticipant.getText());
                    E.setNomEvent(nomEvent.getText());
                    E.setDescriptionEvent(descriptionEvent.getText());
                    E.setIsactive(a);
                    E.setDateDebEvent(format.format(dateDeb.getDate()));
                    E.setDateFinEvent(format.format(dateFin.getDate()));

                    E.setImageevent("60885b06b595a.jpeg");
                    E.setNbrParticipant(nbr);
       
        TextField tfEmail = new TextField("","Votre adresse email",50,TextField.EMAILADDR);
                    System.out.println("Evénement Ajouter avec succés ");
//                    
//               Sms SMS=new Sms();
//     SMS.SendSMS("un nouvel Evénement a été ajouté, veuillez consulter notre application pour plus de détails");
//                   

                    ServiceEvent.getInstance().AddEvent(E);
                    iDialog.dispose();
                    new ListEvent(res).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        });

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> current.showBack()); // Revenir vers l'interface précédente

    }

    private void addStringValue(String s, Component V) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, V));
        add(createLineSeparator(0xeeeeee));

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }

        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);

        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");
        Container page1
                = LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );
        swipe.addTab("", res.getImage("event.png"), page1);
    }

    private void bindButtonselection(Button mesListes, Label arrow) {
        mesListes.addActionListener(e -> {
            if (mesListes.isSelected()) {
                updateArrowPosition(mesListes, arrow);
            }

        });
    }

    private void updateArrowPosition(Button partage, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, partage.getX() + partage.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

}

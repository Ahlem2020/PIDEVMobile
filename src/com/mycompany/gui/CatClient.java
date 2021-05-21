
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Categorieevent;
import com.mycompany.services.ServiceCategory;
import java.util.ArrayList;

/**
 *
 * @author ahlem
 */
public class CatClient extends BaseForm {

    Form current;

    public CatClient(Resources res) {

        super("NewsFeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        add(new Label("List Catégorie"));
        getTitleArea().setUIID("Container");
        setTitle("List Catégorie");
        getContentPane().setScrollVisible(false);
        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("cat.png"), "", "", res);
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
        RadioButton mesListes = RadioButton.createToggle("List Event", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("List Categorie", barGroup);
        liste.setUIID("SelectBar");
   
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

    
        
        
            mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           ListEventC a = new ListEventC(res);
            a.show();
            refreshTheme();
        });

                   liste.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

           CatClient a = new CatClient(res);
            a.show();
            refreshTheme();
        });
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, liste),
                FlowLayout.encloseBottom(arrow)
        ));
        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
        });
        bindButtonselection(mesListes, arrow);
        bindButtonselection(liste, arrow);
        //   special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        ArrayList<Categorieevent> ListE = ServiceCategory.getInstance().getAllCat();
        for (Categorieevent E : ListE) {
            String urlImage = "cat.png";
            Image PlaceHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(PlaceHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addButton(urlim, E, res);

            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container contImage = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        }

    }

    private void addTab(Tabs swipe, Label s1, Image image, String string, String string0, Resources res) {
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
                                        new SpanLabel(string0, "LargeWhiteText"),
                                        s1
                                )
                        )
                );
        swipe.addTab("", res.getImage("cat.png"), page1);
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
     private void addButton(Image img, Categorieevent E,Resources res) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");

        Container cont = BorderLayout.west(image);

        Label Nomtxt = new Label("" + E.getNamecat(), "NewsTopLine2");
        Label DDtxt = new Label("" + E.getDescatevent(), "NewsTopLine");
             //supprimer
        Label lsup = new Label("");
        lsup.setUIID("NewsTopLine");
        Style supStyle = new Style(lsup.getUnselectedStyle());
        supStyle.setFgColor(0xf21f1f);

        FontImage suppImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supStyle);
        lsup.setIcon(suppImage);
        lsup.setTextPosition(RIGHT);
        lsup.addPointerPressedListener(l -> { 
              
        Dialog dig = new Dialog("Supprimer"); 

        if (dig.show("Suppression", "Vous Voulez Supprimer Catégorie ? ", "Non", "Oui")) {
            dig.dispose();
        } else {
            dig.dispose();
            if(ServiceCategory.getInstance().deleteevent(E.getCategoriee())){
            new ListCat(res).show();
        }
        }
        });
    
cont.add(BorderLayout.CENTER, BoxLayout.encloseY(
                BoxLayout.encloseX(Nomtxt),
                BoxLayout.encloseX(DDtxt)));
add(cont);
     }

}
  


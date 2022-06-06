package fri.uv.vajenzanaltri;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField znamka;
    public TextField oznaka;
    public TextField moc;
    public TextField prostornina;
    public ChoiceBox vrstavozila;
    public ColorPicker barva;
    public Spinner stsedezev;
    public ChoiceBox gorivo;
    public TextField ime;
    public TextField priimek;
    public DatePicker rojstvo;
    public TextField ulica;
    public TextField hisnast;
    public TextField postnast;
    public TextField kraj;
    public DatePicker prvaregistracija;
    public TextField krajprveregistracije;
    public VBox registrtska;
    public Label status;
    public CheckBox zavStekel;
    public CheckBox zavPark;
    public CheckBox zavPotnik;
    public CheckBox zavTretjaOs;
    public CheckBox zavGume;
    public CheckBox zavKraja;
    public CheckBox zavToca;
    public ToggleGroup izkusnje;
    public ToggleGroup osnovnoZav;
    public ToggleGroup kaskoZav;
    public TextField registrska;


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void CBshrani(ActionEvent actionEvent) {
        if (znamka.getText().equals("")) {
            status.setText("Status: Prosim vpisite znamko vozila");
            return;
        }

        if (oznaka.getText().equals("")) {
            status.setText("Status: Prosim vpisite oznako vozila");
            return;
        }

        if (moc.getText().equals("")) {
            status.setText("Status: Prosim vpisite moc vozila");
            return;
        }

        try {
            if(Integer.parseInt(moc.getText()) < 0) {
                status.setText("Status: Prosim vpisite moc vozila");
                return;
            }
        } catch (Exception e) {
            status.setText("Status: Prosim vpisite moc vozila v obliki stevilke");
            return;
        }

        if (prostornina.getText().equals("")) {
            status.setText("Status: Prosim vpisite prostornino vozila");
            return;
        }

        try {
            if (Integer.parseInt(prostornina.getText()) < 0) {
                status.setText("Status: Prosim vpisite prostornino vozila");
                return;
            }
        } catch (Exception e) {
            status.setText("Status: Prosim vpisite prostornino vozila v obliki stevilke");
            return;
        }

        if(gorivo.getValue() == null) {
            status.setText("Status: Prosim vnesite tip goriva");
            return;
        }

        if(barva.getValue() == null) {
            status.setText("Status: Prosim vnesite barvo vozila");
            return;
        }

        if(vrstavozila.getValue() == null) {
            status.setText("Status: Prosim vnesite vrsto vozila");
            return;
        }

        if(ime.getText().equals("")) {
            status.setText("Status: Prosim vpisite ime zavarovanca");
            return;
        }

        if(priimek.getText().equals("")) {
            status.setText("Status: Prosim vpisite priimek zavarovanca");
            return;
        }

        if(rojstvo.getValue() == null) {
            status.setText("Status: Prosim vnesite datum rojstva zavarovanca (in pritisnite tipko enter)");
            return;
        }

        if (rojstvo.getValue().getYear() > java.time.LocalDate.now().getYear() - 14) {
            status.setText("Status: Preden se zavarujete morate dopolniti starost, kjer vam je dovoljeno da pričnete voziti motorna vozila");
            return;
        }

        if(rojstvo.getValue().getYear() > java.time.LocalDate.now().getYear() || rojstvo.getValue().getYear() < 1900) {
            status.setText("Status: Prosim vstavite pravilen datum rojstva (in pritisnite tipko enter)");
            return;
        }

        if(ulica.getText().equals("")) {
            status.setText("Status: Prosim vpisite ulico zavarovanca");
            return;
        }

        if(hisnast.getText().equals("")) {
            status.setText("Status: Prosim vpisite hisno stevilko zavarovanca");
            return;
        }

        if(kraj.getText().equals("")) {
            status.setText("Status: Prosim vpisite kraj zavarovanca");
            return;
        }

        try {
            if (Integer.parseInt(postnast.getText()) < 0 || Integer.parseInt(postnast.getText()) > 10000) {
                status.setText("Status: Prosim vpisite pravilno postno stevilko");
                return;
            }
        } catch (Exception e) {
            status.setText("Status: Prosim vnesite postno stevilko zavarovanca v obliki stevilke");
            return;
        }

        if(izkusnje.getSelectedToggle() == null) {
            status.setText("Status: Prosim izberite izkusnjo zavarovanca");
            return;
        }

        if(osnovnoZav.getSelectedToggle() == null) {
            status.setText("Status: Prosim izberite eno izmed osnovnih zavarovanj");
            return;
        }

        if(kaskoZav.getSelectedToggle() == null) {
            status.setText("Status: Prosim izberite eno izmed kasko zavarovanj");
            return;
        }

        if(prvaregistracija.getValue() == null) {
            status.setText("Prosim vnesite datum prve registracije (in pritisnite tipko enter)");
            return;
        }
        if(prvaregistracija.getValue().getYear() > java.time.LocalDate.now().getYear() || prvaregistracija.getValue().getYear() < 1950) {
            status.setText("Prosim vstavite pravilen datum prve registracije (in pritisnite tipko enter)");
            return;
        }

        if(krajprveregistracije.getText().equals("")) {
            status.setText("Prosim vpisite kraj prve registracije");
            return;
        }

        if(registrska.getText().equals("")) {
            status.setText("Prosim vpisite oznako registracije");
            return;
        }

        status.setText("Status: OK");

        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi datoteko za shranjevanje");
        File f = fc.showSaveDialog(null);

        if (f != null)  {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write("Vozilo= " + vrstavozila.getValue().toString() + "\n");
                System.out.println("Log1");

                bw.write("Znamka= " + znamka.getText() + "\n");
                bw.write("Model= " + oznaka.getText() + "\n");
                bw.write("Prostornina= " + prostornina.getText() + "\n");
                bw.write("Moc= " + moc.getText() + "\n");

                bw.write("Gorivo= " + gorivo.getValue().toString() + "\n");
                bw.write("Barva= " + barva.getValue().toString() + "\n");
                bw.write("St_sedezev= " + stsedezev.getValue() + "\n");

                // Podatki o zavarovancu
                bw.write("Ime= " + ime.getText() + "\n");
                bw.write("Priimek= " + priimek.getText() + "\n");
                bw.write("Datum_rojstva= " + rojstvo.getValue() + "\n");
                bw.write("Ulica= " + ulica.getText() + "\n");
                bw.write("Hisna_stevilka= " + hisnast.getText() + "\n");
                bw.write("Kraj= " + kraj.getText() + "\n");
                bw.write("Postna_stevilka= " + postnast.getText() + "\n");
                bw.write("Izkusnje= " + izkusnje.getSelectedToggle().toString().split("]")[1] + "\n");
                System.out.println("Log2");

                // Podatki o zavarovanju
                bw.write("Osnovno_zavarovanje= " + osnovnoZav.getSelectedToggle().toString().split("]")[1] + "\n");
                System.out.println("Log3");
                bw.write("Kasko_zavarovanje= " + kaskoZav.getSelectedToggle().toString().split("]")[1] + "\n");
                System.out.println("Log4");

                bw.write("Zavarovanje_stekel= " + zavStekel.isSelected() + "\n");
                bw.write("Zavarovanje_na_parkiriscu= " + zavPark.isSelected() + "\n");
                bw.write("Zavarovanje_potnikov= " + zavPotnik.isSelected() + "\n");
                bw.write("Zavarovanje_tretje_osebe= " + zavTretjaOs.isSelected() + "\n");
                bw.write("Zavarovanje_gum= " + zavGume.isSelected() + "\n");
                bw.write("Zavarovanje_proti_kraji= " + zavKraja.isSelected() + "\n");
                bw.write("Zavarovanje_proti_toci= " + zavToca.isSelected() + "\n");

                // Podatki o registraciji
                bw.write("Datum_prve_registracije= " + prvaregistracija.getValue() + "\n");
                bw.write("Kraj_prve_registracije= " + krajprveregistracije.getText() + "\n");
                bw.write("Registrska_oznacba= " + registrska.getText() + "\n");

                status.setText("Datoteka je bila shranjena v: " + f.getAbsolutePath());

                bw.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void CBizhod(ActionEvent actionEvent) {

        System.exit(0);
    }

    public void CBpobrisiVse(ActionEvent actionEvent) {



        znamka.setText("");
        moc.setText("");
        oznaka.setText("");
        prostornina.setText("");
        ime.setText("");
        priimek.setText("");
        ulica.setText("");
        postnast.setText("");
        kraj.setText("");
        krajprveregistracije.setText("");
        hisnast.setText("");
        oznaka.setText("");

        stsedezev.getValueFactory().setValue(1);
        barva.setValue(null);
        gorivo.setValue(null);
        vrstavozila.setValue(null);
        registrska.setText("");
        rojstvo.setValue(null);
        prvaregistracija.setValue(null);

        izkusnje.selectToggle(null);

        osnovnoZav.selectToggle(null);
        kaskoZav.selectToggle(null);

        zavStekel.setSelected(false);
        zavPark.setSelected(false);
        zavPotnik.setSelected(false);
        zavTretjaOs.setSelected(false);
        zavGume.setSelected(false);
        zavKraja.setSelected(false);
        zavToca.setSelected(false);


        status.setText("Status: Vnosi počiščeni");
    }

    public void CBabout(ActionEvent actionEvent) {
        status.setText("Status: Avtor aplikacije je Gregor Volcanjk");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gorivo.getItems().addAll("Bencin","Dizel","Elektrika","Hibrid");
        vrstavozila.getItems().addAll("Osebni avtomobil","Motorno kolo","Avtobus","Tovorno vozilo","Traktor");
        stsedezev.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
    }
}
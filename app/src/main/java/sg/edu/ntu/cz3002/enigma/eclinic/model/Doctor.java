package sg.edu.ntu.cz3002.enigma.eclinic.model;

/**
 * Doctor model
 */
public class Doctor {
    private String clinic;
    private String gender;
    private String description;
    private String user;

    public Doctor(String user, String gender, String clinic, String description) {
        this.user = user;
        this.gender = gender;
        this.clinic = clinic;
        this.description = description;
    }
}

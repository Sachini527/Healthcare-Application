import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Doctor extends Person{
    private final int doctorID;
    private String birthday;
    private final String specialization;
    private final ArrayList<Date> availabilities;
    // created a ArrayList to store doctors available dates
    private final HashMap<Date, ArrayList<Appointment>> allAppointments = new HashMap<>();
    // created a HashMap to store each doctor's appointment list. same doctor can have appointments on different dates

    // constructor
    public Doctor(int doctorId, String name, String birthday, String specialization, String contactNumber) {
        super(name, contactNumber);
        this.doctorID = doctorId;
        this.birthday = birthday;
        this.specialization = specialization;
        availabilities = new ArrayList<>();
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public ArrayList<Date> getAvailabilities() {
        return availabilities;
    }

    public HashMap<Date, ArrayList<Appointment>> getAllAppointments() {
        return allAppointments;
    }

    // method to a selected doctor is a Physician
    public boolean isPhysician() {
        boolean specializationEndsWithPhysician;
        specializationEndsWithPhysician = this.specialization.endsWith("physician");
        return specializationEndsWithPhysician;
    }

    // method to add a doctor's availability
    public void setAvailability(Date availableDate) {
        this.availabilities.add(availableDate);
    }

    public void setAppointment(Date date, Appointment appointment) {
        // check if there are already appointments exist in the given date
        /* To check it, we create a new arrayList of Appointment objects, and retrieve the existing appointments from
         the allAppointments arrayList for the given date, and pass them to the new arrayList we created.*/
        ArrayList<Appointment> currentAppointments = this.allAppointments.get(date);
        /* if there are no appointments for given date the ArrayList will return null(if you pass a non-existing
        key to a Hashmap it will return 'null')*/
        if (currentAppointments == null) {
            // we create a new empty arrayList of Appointment objects and pass the new Appointment to it.
            ArrayList<Appointment> tempArrayList = new ArrayList<>();
            tempArrayList.add(appointment);
            /* pass the tempArrayList(value) which holds our new appointment, to the allAppointments HashMap for the
               given date(key).*/
            this.allAppointments.put(date, tempArrayList);
        } else {
            // there are already appointments exist in the arrayList for the given date
            // so, we add the new appointment to the existing currentAppointments arrayList
            currentAppointments.add(appointment);
            // pass the updated arrayList to the allAppointments HashMap as the value of the given date key
            // below line is an extra line.It's just repasting the arrayList
            this.allAppointments.put(date, currentAppointments);
        }
    }
}


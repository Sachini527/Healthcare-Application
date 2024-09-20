import java.util.Date;

public class GeneralAppointments extends Appointment{
    private final String patientNotes;

    public GeneralAppointments (Doctor doctor, Patient patient, Date date,String time, String patientNotes){
        super(doctor, patient, date, time);
        this.patientNotes = patientNotes;
    }

    public String getPatientNotes(){
        return this.patientNotes;
    }
}

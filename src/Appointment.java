import java.util.Date;

abstract class Appointment {
    private final Doctor doctor;
    private final Patient patient;
    private final Date date;
    private final String time;

    public Appointment(Doctor doctor, Patient patient, Date date, String time) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Date getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }
}
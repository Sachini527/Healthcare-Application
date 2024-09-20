import java.util.Date;

public class ReferralAppointments extends Appointment{
    private final Doctor referralDoctor;
    private String referralDoctorNotes;
    private final String notes;

    public ReferralAppointments(Doctor doctor, Patient patient, Date date, String time, Doctor referralDoctor, String notes) {
        super(doctor, patient, date, time);
        this.referralDoctor = referralDoctor;
        this.notes = notes;
    }

    // method to set referral doctor's notes(String)
    public void setReferralDoctorNotes(String referralDoctorNotes){
        this.referralDoctorNotes = referralDoctorNotes;
    }

    // method to set referral doctor's notes(Array of Strings)
    public void setReferralDoctorNotes(String[] referralDoctorNotes){
        this.referralDoctorNotes = String.join(" ", referralDoctorNotes);
    }
}
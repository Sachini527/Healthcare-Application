public class Patient extends Person{
    private final String patientID;

    public Patient(String patientId, String name, String contactNumber) {
        super(name, contactNumber);
        this.patientID = patientId;
    }

    public String getPatientID() {
        return this.patientID;
    }

    public char getPatientType() {
        char findPatientType;
        findPatientType = this.patientID.charAt(0);
        return findPatientType; // Get the first letter of the patient ID
    }
}

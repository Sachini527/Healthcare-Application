import java.util.*;

public class Controller {
    // Create empty Arraylist that holds doctor objects
    public static ArrayList<Doctor> allDoctors = new ArrayList<>();
    // Create empty Arraylist that holds patient objects
    public static ArrayList<Patient> allPatients = new ArrayList<>();
    public static int NUMBER_OF_SLOTS = 5;
    // Each slot represents a 1-hour block starting from 5 PM.The maximum slots that can be per day is 5 slots

    public static void addDoctor() {
        // get the relevant data about doctors
        Scanner input = new Scanner(System.in);
        System.out.println("Enter doctor's name: ");
        String name = input.nextLine();
        System.out.println("Enter doctor's birthday: ");
        String birthday = input.nextLine();
        System.out.println("Enter doctor's specialization: ");
        String specialization = input.nextLine();
        System.out.println("Enter doctor's contact number: ");
        String contactNumber = input.nextLine();
        // create a random ID number
        Random random = new Random();
        // generate a positive random integer without a specific range
        int randomId = random.nextInt(Integer.MAX_VALUE);
        // Create a doctor instance
        Doctor tempDoctor = new Doctor(randomId, name, birthday, specialization, contactNumber);
        allDoctors.add(tempDoctor); // Add the new doctor object to the ArrayList
        System.out.println("Doctor is added successfully.");
        System.out.println("Doctor " + tempDoctor.getName() + "'s ID is: " + tempDoctor.getDoctorID());
        System.out.println();
    }

    public static void addDoctorAvailability() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the doctor ID you want to add availability: ");
        int doctorId = input.nextInt();
        Doctor selectedDoctor = null;
        // fetch the doctor from the array list
        for (Doctor doc : allDoctors) {
            if (doctorId == doc.getDoctorID()) {
                selectedDoctor = doc;
            }
        }
        // if the doctor is not existing display "No Doctor found"
        if (selectedDoctor == null) {
            System.out.println("No Doctor found.");
            System.out.println();
            return;
        }
        // if the doctor is existing, take the available date
        System.out.println("Enter the Day you want to add availability: ");
        int day = input.nextInt();
        System.out.println("Enter the Month you want to add availability: ");
        int month = input.nextInt();
        System.out.println("Enter the Year you want to add availability: ");
        int year = input.nextInt();
        Date avaialbleDate = new Date(year, month, day);
        /* call the checkAvailability method to check if the selected doctor's availability on the given date is
        already added to the system.*/
        boolean isDateAlreadyAdded = checkAvailability(selectedDoctor, avaialbleDate);
        if (isDateAlreadyAdded) {
            System.out.println("Doctor " + selectedDoctor.getName() + "'s availability on this date is already added to the system.");
            System.out.println();
            return;
        }
        // add the selected doctor's available date in the Availabilities ArrayList
        selectedDoctor.setAvailability(avaialbleDate);
        System.out.println("Availability is added.");
        System.out.println();
    }

    public static void viewDoctors() {
        if (allDoctors.isEmpty()) {
            System.out.println("No Doctor is added yet.");
            System.out.println();
            return;
        }
        // Create a for each loop to View all doctors in allDoctors ArrayList
        for (Doctor doc : allDoctors) {
            System.out.println("Doctor " + doc.getName() + " has a specialization of " + doc.getSpecialization() + " has a id of " + doc.getDoctorID() + " and has a availability of " + doc.getAvailabilities().toString());
        }
        System.out.println();
    }

    public static void registerPatient() {
        Scanner input = new Scanner(System.in);
        String id = generatePatientId();
        System.out.println("Enter patient's name: ");
        String name = input.nextLine();
        System.out.println("Enter patient's contact information: ");
        String contactInfo = input.nextLine();
        // create a Patient object
        Patient tempPatient = new Patient(id, name, contactInfo);
        // add the patient to the Arraylist
        allPatients.add(tempPatient);
        System.out.println("Patient is added successfully.");
        System.out.println("* Patient's ID is: " + id);
        System.out.println();
    }

    private static String generatePatientId() {
        // method to generate unique patient IDs
        String prefix = Math.random() < 0.5 ? "T" : "D";
        int number = (int) (Math.random() * 10000);
        return String.format("%s-%04d", prefix, number);
    }

    public static void bookAnAppointment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Doctor's ID you want to make an appointment: ");
        int doctorId = input.nextInt();
        System.out.println("Enter Patient's ID: ");
        String patientId = input.next();
        // get the selected Doctor and Patient
        Doctor selectedDoc = getDoctorById(doctorId);
        Patient selectedPatient = getPatientById(patientId);
        // if either the patient or doctor not found print "Patient/Doctor not found."
        if (selectedDoc == null || selectedPatient == null) {
            System.out.println("Invalid Doctor or Patient ID.");
            System.out.println();
            return;
        }
        // check the appointment type
        try {
            System.out.println("Enter the type of the Appointment (Enter 'G' for General and 'R' for Referral): ");
            // read the input and convert to upper case
            char bookingType = Character.toUpperCase(input.next().charAt(0));
            if (bookingType == 'G') {
                handleGeneralAppointment(selectedDoc, selectedPatient);
            } else if (bookingType == 'R') {
                handleReferralAppointment(selectedDoc, selectedPatient);
            } else {
                System.out.println("Invalid input. Please enter 'G' for General and 'R' for Referral.");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private static Doctor getDoctorById(int id) {
        // method to fetch a selected doctor from allDoctors arrayList
        for (Doctor doctor : allDoctors) {
            if (doctor.getDoctorID() == id) {
                return doctor;
            }
        }
        return null;
    }

    private static Patient getPatientById(String id) {
        // method to fetch a selected patient from allPatients arrayList
        for (Patient patient : allPatients) {
            if (patient.getPatientID().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    private static void handleGeneralAppointment(Doctor selectedDoctor, Patient selectedPatient) {
        Scanner input = new Scanner(System.in);
        // ask for any notes
        System.out.println("Please enter any specific notes or concerns: ");
        String notes = input.next();
        // take the appointment date
        Date appointmentDate = getAppointmentDate();
        // call the checkAvailability method to check if the selected Doctor is available on the given date
        boolean isDocAvailable = checkAvailability(selectedDoctor, appointmentDate);
        if (isDocAvailable) {
            // doctor is available
            // check if the Patient has already booked the selected doctor on the selected Date
            boolean cannotBook = isPatientAlreadyBooked(selectedPatient, selectedDoctor, appointmentDate);
            if (cannotBook) {
                System.out.println("Can't do this. You have already booked the selected doctor on the given date.");
                System.out.println();
                return;
            }
            // then call the getTimeForBooking method to check the availability slots and calculate appointment time
            String appointmentTime = getTimeForBooking(selectedDoctor, appointmentDate);
            if (appointmentTime != null) {
                // if slots are left for this day, then make the appointment
                // upcast general object to the Appointment Data type
                Appointment generalAppointment = new GeneralAppointments(selectedDoctor, selectedPatient, appointmentDate, appointmentTime, notes);
                /* call the setAppointment method in the Doctor class to set the appointment. (we can't just pass the
                 appointmentDate and the appointment object to the HashMap directly to set the appointment, because
                 we have to pass an ArrayList of Appointment objects, not just an appointment to the HashMap.)*/
                selectedDoctor.setAppointment(appointmentDate, generalAppointment);
                // print appointment details
                printGeneralAppointmentDetails(selectedDoctor, appointmentDate, appointmentTime);
            } else {
                // if all slots are filled
                System.out.println("All slots are filled. Doctor is fully booked on this date.");
                System.out.println();
            }
        } else {
            // if the doctor is not available on the given date
            System.out.println("Doctor " + selectedDoctor.getName() + " is not available on the selected date.");
            System.out.println();
        }
    }

    private static void handleReferralAppointment(Doctor selectedDoctor, Patient selectedPatient) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the referred Doctor ID: ");
        int referredDocId = input.nextInt();
        // get the referred doctor
        Doctor referredDoctor = getDoctorById(referredDocId);
        // if doctor not found print "Doctor not found."
        if (referredDoctor == null) {
            System.out.println("Invalid referred doctor ID.");
            System.out.println();
            return;
        }
        // ask for any notes
        System.out.println("Please enter any specific notes or concerns: ");
        String notes = input.next();
        // take the appointment date
        Date appointmentDate = getAppointmentDate();
        boolean isDocAvailable = checkAvailability(selectedDoctor, appointmentDate);
        if (isDocAvailable) {
            boolean cannotBook = isPatientAlreadyBooked(selectedPatient, selectedDoctor, appointmentDate);
            if (cannotBook) {
                System.out.println("Can't do this. You have already booked the selected doctor on the given date.");
                System.out.println();
                return;
            }
            // then call the getTimeForBooking method to check the availability slots and calculate appointment time
            String appointmentTime = getTimeForBooking(selectedDoctor, appointmentDate);
            if (appointmentTime != null) {
                // if slots are left for this day, then make the appointment
                // upcast referral object to the Appointment Data type
                Appointment appointment = new ReferralAppointments(selectedDoctor, selectedPatient, appointmentDate, appointmentTime, referredDoctor, notes);
                // downcast the appointment object
                ReferralAppointments referralAppointment = (ReferralAppointments) appointment;
                // get the referral doctor's notes
                getReferralDoctorNotes(referralAppointment);
                selectedDoctor.setAppointment(appointmentDate, appointment);
                // print appointment details
                printReferralAppointmentDetails(selectedDoctor, referredDoctor, appointmentDate, appointmentTime);
            } else {
                // if all slots are filled
                System.out.println("All slots are filled. Doctor is fully booked on this date.");
                System.out.println();
            }
        } else {
            // if the doctor is not available on the given date
            System.out.println("Doctor " + selectedDoctor.getName() + " is not available on the selected date.");
            System.out.println();
        }
    }

    private static Date getAppointmentDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Day you want to add appointment: ");
        String day = input.next();
        System.out.println("Enter the Month you want to add appointment: ");
        String month = input.next();
        System.out.println("Enter the Year you want to add appointment: ");
        String year = input.next();
        Date appointmentDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return appointmentDate;
    }

    // method to check the availability slots and calculate appointment time
    private static String getTimeForBooking(Doctor selectedDoctor, Date dateOfBooking) {
        // loop through the allAppointments HashMap to check there are already appointments exist on the given date
        for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoctor.getAllAppointments().entrySet()) {
            // check if the given date already exist in the HashMap(as a key)
            if (appointment.getKey().equals(dateOfBooking)) {
                // calculate how many appointment slots does the doctor have
                int numberOfSlots = appointment.getValue().size();
                // we check if all the slots are filled or not( maximum 5 slots on a day)
                if (numberOfSlots > NUMBER_OF_SLOTS - 1) {
                    // if all 5 slots are filled
                    return null;
                }
                // if there are slots available for the day
                System.out.println("Your booking is on progress..");
                // calculate the appointment time
                int calculateTime = 17 + numberOfSlots;
                // format the time
                String strTime = calculateTime + ":00";
                return strTime;
            }
        }
        /* given date is not in the HashMap, Means there are no appointments for the selected doctor on the given date,
        doctor is already available. So this is the first appointment slot on this day.*/
        return "17:00";
    }

    // Method to check if the doctor is available on the date given by the patient for booking the appointment.
    private static boolean checkAvailability(Doctor selectedDoctor, Date dateOfBooking) {
        // get the selected doctor and the date of booking and loop through the availabilities arrayList
        for (Date day : selectedDoctor.getAvailabilities()) {
            if (day.equals(dateOfBooking)) {
                return true;
            }
        }
        return false;
    }

    // method to check if the Patient has already booked the selected doctor on the selected Date
    private static boolean isPatientAlreadyBooked(Patient patient, Doctor selectedDoc, Date selectedDate) {
        for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoc.getAllAppointments().entrySet()) {
            if (appointment.getKey().equals(selectedDate)) {
                ArrayList<Appointment> appointments = appointment.getValue();
                for (Appointment appointmentSlot : appointments) {
                    if (appointmentSlot.getPatient().equals(patient)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void getReferralDoctorNotes(ReferralAppointments referralAppointment) {
        Scanner input = new Scanner(System.in);
        // ask for referral doctor's notes
        System.out.println("Please enter the referral doctor's notes (enter 'done' to finish): ");
        input.nextLine(); // consume newline
        List<String> referralNotesList = new ArrayList<>();
        while (true) {
            String line = input.nextLine();
            if (line.equalsIgnoreCase("done")) {
                break;
            }
            referralNotesList.add(line);
        }
        // check if the input for referral notes is a single or an array of Strings
        // call the appropriate set method based on the input type
        if (referralNotesList.size() == 1) {
            referralAppointment.setReferralDoctorNotes(referralNotesList.getFirst());
        } else {
            String[] referralNotesArray = referralNotesList.toArray(new String[0]);
            referralAppointment.setReferralDoctorNotes(referralNotesArray);
        }
    }

    private static void printGeneralAppointmentDetails(Doctor selectedDoctor, Date date, String time) {
        System.out.println("Your General appointment has been successfully scheduled. Please find the details below:");
        System.out.println("Doctor: " + selectedDoctor.getName());
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println();
    }

    private static void printReferralAppointmentDetails(Doctor selectedDoctor, Doctor referralDoctor, Date date, String time) {
        System.out.println("Your Referral appointment has been successfully scheduled. Please find the details below:");
        System.out.println("Doctor: " + selectedDoctor.getName());
        System.out.println("Referred Doctor: " + referralDoctor.getName());
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println();
    }

    public static void viewSelectedDoctorBookings() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Doctor's id to view the bookings: ");
        int docId = input.nextInt();
        // get the doctor
        Doctor selectedDoc = getDoctorById(docId);
        // check if a doctor with a given id exists.
        if (selectedDoc == null) {
            System.out.println("Invalid Doctor ID.");
            System.out.println();
            return;
        }
        // check if the doctor has any bookings
        if (selectedDoc.getAllAppointments().isEmpty()) {
            System.out.println("Doctor " + selectedDoc.getName() + " has no bookings yet.");
        } else {
            System.out.println("Bookings for Doctor " + selectedDoc.getName() + ": ");
            for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoc.getAllAppointments().entrySet()) {
                Date date = appointment.getKey();
                ArrayList<Appointment> appointments = appointment.getValue();
                // prints each date and the corresponding appointments
                System.out.println("Date: " + date);
                for (Appointment appointmentSlot : appointments) {
                    System.out.println(" - " + appointmentSlot);
                }
            }
        }
        System.out.println();
    }
}
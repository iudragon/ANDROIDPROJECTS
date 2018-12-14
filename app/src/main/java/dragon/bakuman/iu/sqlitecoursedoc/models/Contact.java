package dragon.bakuman.iu.sqlitecoursedoc.models;

//this is going to be the model for storing contact information
public class Contact {

    private String name;
    private String phoneNumber;
    private String device;
    private String email;
    //saving the profileImage as a String, because it will be stored as image url
    //in reality it will be file:// something
    private String profileImage;

    //constructor
    public Contact(String name, String phoneNumber, String device, String email, String profileImage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.device = device;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    //'toString' is meant for returning textual representation of an object. This is generally overridden by java classes to create a human readable string to represent that object.

    //Apart from many other uses, it is widely used for logging purpose so as to print the object in a readable format. Appending an object with string automatically calls the toString() of that object e.g. "abc" + myObject will invoke 'toString' of myObject and append the returned value to "abc"

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", device='" + device + '\'' +
                ", email='" + email + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}

package dragon.bakuman.iu.sqlitecoursedoc.models;


import android.os.Parcel;
import android.os.Parcelable;

//this is going to be the model for storing contact information
public class Contact implements Parcelable {

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

    protected Contact(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        device = in.readString();
        email = in.readString();
        profileImage = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(device);
        dest.writeString(email);
        dest.writeString(profileImage);
    }
}

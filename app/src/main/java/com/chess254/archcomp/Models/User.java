package com.chess254.archcomp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by chess on 10/23/2018.
 */
@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "user_id")
    private Integer id;

    private String userName;
    private String userPhone;
    private String userEmail;
    private String userAddress;
    private String userImage;
//    private boolean connected;   //connecte == connected
//        private ArrayList<Appointment> appointments; //appointment


    public User(@NonNull Integer id,
                String userName,
                String userPhone,
                String userEmail,
                String userAddress,
                String userImage) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userImage = userImage;
    }
@Ignore
    public User(String userName, String userPhone, String userEmail, String userAddress, String userImage) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userImage = userImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String telUser) {
        this.userPhone = telUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

//        public boolean isConnected() {
//            return connected;
//        }

//        public void setConnected(boolean connected) {
//            this.connected = connected;
//        }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

//        public ArrayList<Appointment> getAppointments() {
//            return appointments;
//        }

//        public void setAppointments(ArrayList<Appointment> appointments) {
//            this.appointments = appointments;
//        }


}


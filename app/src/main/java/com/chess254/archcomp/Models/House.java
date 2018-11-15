package com.chess254.archcomp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/**
 * Created by chess on 10/23/2018.
 */
@Entity(tableName = "house_table", foreignKeys =
@ForeignKey(entity = User.class,
            parentColumns = "user_id",
            childColumns = "user_Id",
            onDelete = CASCADE,
            onUpdate = CASCADE))

public class House implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "house_id")
    private Integer id;

    private String typeHouse;
    private String priceHouse;
    private String areaHouse;
    private String roomsHouse;
    private String locationHouse;
    private String descriptionHouse;
    private String availabilityHouse;
    private String imageHouse;

    @ColumnInfo(name = "user_Id")
    private int ownerHouse;

    private String ratingHouse;
    private int commentsHouse;

    public House() {
    }

    public House(@NonNull Integer id,
                 String typeHouse,
                 String priceHouse,
                 String areaHouse,
                 String roomsHouse,
                 String locationHouse,
                 String descriptionHouse,
                 String availabilityHouse,
                 String imageHouse,
                 int ownerHouse,
                 String ratingHouse,
                 int commentsHouse) {
        this.id = id;
        this.typeHouse = typeHouse;
        this.priceHouse = priceHouse;
        this.areaHouse = areaHouse;
        this.roomsHouse = roomsHouse;
        this.locationHouse = locationHouse;
        this.descriptionHouse = descriptionHouse;
        this.availabilityHouse = availabilityHouse;
        this.imageHouse = imageHouse;
        this.ownerHouse = ownerHouse;
        this.ratingHouse = ratingHouse;
        this.commentsHouse = commentsHouse;
    }

    protected House(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        typeHouse = in.readString();
        priceHouse = in.readString();
        areaHouse = in.readString();
        roomsHouse = in.readString();
        locationHouse = in.readString();
        descriptionHouse = in.readString();
        availabilityHouse = in.readString();
        imageHouse = in.readString();
        ownerHouse = in.readInt();
        ratingHouse = in.readString();
        commentsHouse = in.readInt();
    }

    public static final Creator<House> CREATOR = new Creator<House>() {
        @Override
        public House createFromParcel(Parcel in) {
            return new House(in);
        }

        @Override
        public House[] newArray(int size) {
            return new House[size];
        }
    };

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(String typeHouse) {
        this.typeHouse = typeHouse;
    }

    public String getPriceHouse() {
        return priceHouse;
    }

    public void setPriceHouse(String priceHouse) {
        this.priceHouse = priceHouse;
    }

    public String getAreaHouse() {
        return areaHouse;
    }

    public void setAreaHouse(String areaHouse) {
        this.areaHouse = areaHouse;
    }

    public String getRoomsHouse() {
        return roomsHouse;
    }

    public void setRoomsHouse(String roomsHouse) {
        this.roomsHouse = roomsHouse;
    }

    public String getLocationHouse() {
        return locationHouse;
    }

    public void setLocationHouse(String locationHouse) {
        this.locationHouse = locationHouse;
    }

    public String getDescriptionHouse() {
        return descriptionHouse;
    }

    public void setDescriptionHouse(String descriptionHouse) {
        this.descriptionHouse = descriptionHouse;
    }

    public String getAvailabilityHouse() {
        return availabilityHouse;
    }

    public void setAvailabilityHouse(String availabilityHouse) {
        this.availabilityHouse = availabilityHouse;
    }

    public String getImageHouse() {
        return imageHouse;
    }

    public void setImageHouse(String imageHouse) {
        this.imageHouse = imageHouse;
    }

    public int getOwnerHouse() {
        return ownerHouse;
    }

    public void setOwnerHouse(int ownerHouse) {
        this.ownerHouse = ownerHouse;
    }

    public String getRatingHouse() {
        return ratingHouse;
    }

    public void setRatingHouse(String ratingHouse) {
        this.ratingHouse = ratingHouse;
    }

    public int getCommentsHouse() {
        return commentsHouse;
    }

    public void setCommentsHouse(int commentsHouse) {
        this.commentsHouse = commentsHouse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(typeHouse);
        dest.writeString(priceHouse);
        dest.writeString(areaHouse);
        dest.writeString(roomsHouse);
        dest.writeString(locationHouse);
        dest.writeString(descriptionHouse);
        dest.writeString(availabilityHouse);
        dest.writeString(imageHouse);
        dest.writeInt(ownerHouse);
        dest.writeString(ratingHouse);
        dest.writeInt(commentsHouse);
    }



//    public House(@NonNull Integer id,
//                 String typeHouse,
//                 String priceHouse,
//                 String areaHouse,
//                 String roomsHouse,
//                 String locationHouse,
//                 String descriptionHouse,
//                 String availabilityHouse,
//                 String imageHouse,
//                 int ownerHouse,
//                 String ratingHouse,
//                 int commentsHouse) {
//        this.id = id;
//        this.userName = userName;
//        this.userPhone = userPhone;
//        this.userEmail = userEmail;
//        this.userAddress = userAddress;
//        this.userImage = userImage;
//    }
//    @Ignore
//    public House(String userName, String userPhone, String userEmail, String userAddress, String userImage) {
//        this.userName = userName;
//        this.userPhone = userPhone;
//        this.userEmail = userEmail;
//        this.userAddress = userAddress;
//        this.userImage = userImage;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserPhone() {
//        return userPhone;
//    }
//
//    public void setUserPhone(String telUser) {
//        this.userPhone = telUser;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getUserAddress() {
//        return userAddress;
//    }
//
//    public void setUserAddress(String userAddress) {
//        this.userAddress = userAddress;
//    }
//
////        public boolean isConnected() {
////            return connected;
////        }
//
////        public void setConnected(boolean connected) {
////            this.connected = connected;
////        }
//
//    public String getUserImage() {
//        return userImage;
//    }
//
//    public void setUserImage(String userImage) {
//        this.userImage = userImage;
//    }
//
////        public ArrayList<Appointment> getAppointments() {
////            return appointments;
////        }
//
////        public void setAppointments(ArrayList<Appointment> appointments) {
////            this.appointments = appointments;
////        }


}


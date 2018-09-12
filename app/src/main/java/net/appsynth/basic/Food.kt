package net.appsynth.basic

import android.os.Parcel
import android.os.Parcelable

class Food() : Parcelable {

    var name: String? = null
    var thumb: Int? = null
    var desc: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        thumb = parcel.readValue(Int::class.java.classLoader) as? Int
        desc = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(thumb)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}
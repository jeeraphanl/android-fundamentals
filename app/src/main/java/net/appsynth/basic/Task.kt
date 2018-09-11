package net.appsynth.basic

import android.os.Parcel
import android.os.Parcelable

class Task() : Parcelable {

    var name: String? = null
    var position: Int? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        position = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}
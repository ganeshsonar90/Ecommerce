package com.task.data.models.db


import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
class Category() : Parcelable {

    @PrimaryKey
    var categoryId: Int = 0

    @ColumnInfo
    var categoryName: String = ""

    constructor(parcel: Parcel) : this() {
        categoryId = parcel.readInt()
        categoryName = parcel.readString()!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(categoryId)
        dest.writeString(categoryName)
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(`in`: Parcel): Category {
            return Category(`in`)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}
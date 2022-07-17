package com.example.dbaccessv2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class DataBaseHandler(private var context: Context) : SQLiteOpenHelper(context, "LocationDataBase.db", null, 10) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + "CountryList" + " (" + "CID" + " INTEGER, " + "Country" + " VARCHAR(100))")
        db?.execSQL("CREATE TABLE " + "StateList" + " (" + "SID" + " INTEGER, " + "State" + " VARCHAR(100), " + "CID" + " INTEGER)")
        db?.execSQL("CREATE TABLE " + "PlaceList" + " (" + "PID" + " INTEGER, " + "Place" + " VARCHAR(100), " + "SID" + " INTEGER)")
    }

    fun insertData() {
        try {
            val resultCountry = this.readableDatabase.rawQuery("Select * from CountryList", null)

            if(resultCountry.count == 0) {
                this.writableDatabase.execSQL("Insert into CountryList(CID,Country) values ('1','INDIA'),('2','USA'),('3','AUSTRALIA')")
            } else {
                this.writableDatabase.delete("CountryList", null, null)
                this.writableDatabase.execSQL("Insert into CountryList(CID,Country) values ('1','INDIA'),('2','USA'),('3','AUSTRALIA')")
            }

            val resultState = this.readableDatabase.rawQuery("Select * from StateList", null)

            if(resultState.count == 0) {
                this.writableDatabase.execSQL("Insert into StateList(SID,State,CID) values ('1','Madhya Pradesh','1'),('2','Victoria','3'),('3','Gujarat','1'),('4','Texas','2'),('5','Tasmania','3'),('6','Rajasthan','1'),('7','Florida','2'),('8','Queensland','3'),('9','California','2'),('10','Maharashtra','1')")
            } else {
                this.writableDatabase.delete("StateList", null, null)
                this.writableDatabase.execSQL("Insert into StateList(SID,State,CID) values ('1','Madhya Pradesh','1'),('2','Victoria','3'),('3','Gujarat','1'),('4','Texas','2'),('5','Tasmania','3'),('6','Rajasthan','1'),('7','Florida','2'),('8','Queensland','3'),('9','California','2'),('10','Maharashtra','1')")
            }

            val resultPlace = this.readableDatabase.rawQuery("Select * from PlaceList", null)

            if(resultPlace.count == 0) {
                this.writableDatabase.execSQL("Insert into PlaceList(PID,Place,SID) values ('1','Dewas','1'),('2','Gir National Park','3'),('3','Yosemite National Park','9'),('4','Miami','7'),('5','Indore','1'),('6','Stanford University','9'),('7','Hobart','5'),('8','Dallas','4'),('9','Walt Disney World Resort','7'),('10','Rann of Kutch','3'),('11','Grampians National Park','2'),('12','Thar Desert','6'),('13','Ellora Caves','10'),('14','Gold Coast','8'),('15','Ranthambore National Park','6'),('16','Austin','4'),('17','Lonavla','10'),('18','Great Otway National Park','2'),('19','Fraser Island','8'),('20','Cradle Mountain','5')")
            } else {
                this.writableDatabase.delete("PlaceList", null, null)
                this.writableDatabase.execSQL("Insert into PlaceList(PID,Place,SID) values ('1','Dewas','1'),('2','Gir National Park','3'),('3','Yosemite National Park','9'),('4','Miami','7'),('5','Indore','1'),('6','Stanford University','9'),('7','Hobart','5'),('8','Dallas','4'),('9','Walt Disney World Resort','7'),('10','Rann of Kutch','3'),('11','Grampians National Park','2'),('12','Thar Desert','6'),('13','Ellora Caves','10'),('14','Gold Coast','8'),('15','Ranthambore National Park','6'),('16','Austin','4'),('17','Lonavla','10'),('18','Great Otway National Park','2'),('19','Fraser Island','8'),('20','Cradle Mountain','5')")
            }

            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun getCountryUser() : ArrayList<UserCountry> {

        val result = this.readableDatabase.rawQuery("Select * from CountryList", null)
        val userList = ArrayList<UserCountry>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Country To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserCountry()
                    user.cid = result.getInt(result.getColumnIndex("CID"))
                    user.country = result.getString(result.getColumnIndex("Country"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun getCountryId(country : String) : ArrayList<UserCountryID> {

        val result = this.readableDatabase.rawQuery("Select * from CountryList where Country = '$country'", null)
        val userList = ArrayList<UserCountryID>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Country To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserCountryID()
                    user.cid = result.getInt(result.getColumnIndex("CID"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun getStateUser(cid : Int) : ArrayList<UserState> {

        val result = this.readableDatabase.rawQuery("Select * from StateList where CID = $cid", null)
        val userList = ArrayList<UserState>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No State To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserState()
                    user.state = result.getString(result.getColumnIndex("State"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun getStateId(state : String) : ArrayList<UserStateID> {

        val result = this.readableDatabase.rawQuery("Select * from StateList where State = '$state'", null)
        val userList = ArrayList<UserStateID>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No State To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserStateID()
                    user.sid = result.getInt(result.getColumnIndex("SID"))
                    user.state = result.getString(result.getColumnIndex("State"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun getPlaceUser(sid : Int) : ArrayList<UserPlace> {

        val result = this.readableDatabase.rawQuery("Select * from PlaceList where SID = $sid", null)
        val userList = ArrayList<UserPlace>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Place To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserPlace()
                    user.place = result.getString(result.getColumnIndex("Place"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }

        return userList
    }

    fun getPlaceList() : ArrayList<UserPlace> {

        val result = this.readableDatabase.rawQuery("Select * from PlaceList", null)
        val userList = ArrayList<UserPlace>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Place To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserPlace()
                    user.place = result.getString(result.getColumnIndex("Place"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }

        return userList
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
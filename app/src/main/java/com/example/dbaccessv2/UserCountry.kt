package com.example.dbaccessv2

class UserCountry {
    var cid : Int = 0
    var country : String = ""

    constructor(cid: Int, country: String){
        this.cid = cid
        this.country = country
    }

    constructor(){
    }

    override fun toString(): String {
        return ("$country")
    }
}
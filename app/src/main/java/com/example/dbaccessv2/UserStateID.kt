package com.example.dbaccessv2

class UserStateID {
    var cid : Int = 0
    var sid : Int = 0
    var state : String = ""

    constructor(cid: Int, sid: Int, state: String){
        this.cid = cid
        this.sid= sid
        this.state = state
    }

    constructor(){
    }

    override fun toString(): String {
        return "$sid"
    }
}
package com.tecra.cloverly.model

class note  {
    var id : Int? = null
    var color : String? = null
    var title : String? = null
    var des : String? = null
    var date : String? = null
    var type : String? = null

    constructor(id : Int,color : String,title : String,des :String,date :String,type : String){
        this.id = id
        this.color = color
        this.title = title
        this.des = des
        this.date = date
        this.type = type

    }
}
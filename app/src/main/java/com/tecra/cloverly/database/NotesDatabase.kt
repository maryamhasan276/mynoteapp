package com.tecra.cloverly.database
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import com.tecra.cloverly.model.User

class NotesDatabase {
        val dbName = "MyNotes"
        val dbTable = "Notes"
        private val colID = "ID"
        private val color ="Color"
        private val colTitle ="Title"
        private val colDes = "Description"
        private val colDate = "Date"
        private val colType = "Type"
        private val isFav = "isFav"

    val dbVersion=1
        //CREATE TABLE IF NOT EXISTS MyNotes (ID INTEGER PRIMARY KEY,title TEXT, Description TEXT);"
        val sqlCreateTable=" CREATE TABLE  IF NOT EXISTS " + dbTable +" (" + colID + " INTEGER PRIMARY KEY,"+
                color +" Text NOT NULL DEFAULT 'white'," + colTitle + " TEXT NOT NULL DEFAULT 'Title',"+ colDes +" TEXT NOT NULL DEFAULT 'Description', "+colDate + " TEXT, " + colType + " TEXT ,"+isFav+ " INT"+");"

        var sqlDB: SQLiteDatabase?  = null

        constructor(context: Context,isReadable: Boolean){
            var db=DatabaseHelperNotes(context)
            if (isReadable){
                sqlDB=db.writableDatabase
            }else{
                sqlDB=db.readableDatabase
            }
        }

        inner class  DatabaseHelperNotes: SQLiteOpenHelper {
            var context: Context?=null
            constructor(context: Context):super(context,dbName,null,dbVersion){
                this.context=context
            }
            override fun onCreate(p0: SQLiteDatabase?) {
                p0!!.execSQL(sqlCreateTable)
                p0!!.execSQL(User.TABLE_CREATE)

            }
            override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
                p0!!.execSQL("Drop table IF EXISTS $dbTable")
                p0!!.execSQL("Drop table if exists ${User.TABLE_NAME}")

            }
        }
        fun Insert(values: ContentValues):Long{

            val ID= sqlDB!!.insert(dbTable,"",values)
            return ID
        }
        fun  Query(projection:Array<String>,selection:String,selectionArgs:Array<String>,sorOrder:String): Cursor {

            val qb= SQLiteQueryBuilder()
            qb.tables=dbTable
            val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sorOrder)
            return cursor
        }
        fun Delete(selection:String,selectionArgs:Array<String>):Int{

            val count=sqlDB!!.delete(dbTable,selection,selectionArgs)
            return  count
        }
        fun Update(values: ContentValues, selection:String, selectionargs:Array<String>):Int{

            val count=sqlDB!!.update(dbTable,values,selection,selectionargs)
            return count
        }
    fun getUser(id: Int): User {
        val c =
            sqlDB!!.rawQuery("select * from ${User.TABLE_NAME} where ${User.COL_ID} = $id", null)
        c.moveToFirst()
        val s = User(
            c.getInt(0),
            c.getString(1),
            c.getString(2),
            c.getString(3),
            c.getString(4))
        c.close()
        return s
    }
    fun insertUser(user: User): Boolean {
        val cv = ContentValues()
        cv.put(User.COL_FULL_NAME, user.fullName)
        cv.put(User.COL_USERNAME, user.userName)
        cv.put(User.COL_EMAIL, user.email)
        cv.put(User.COL_PASSWORD, user.password)
        return sqlDB!!.insert(User.TABLE_NAME, null, cv) > 0
    }

    //Actions

    fun deleteUser(id: Int): Boolean {
        return sqlDB!!.delete(User.TABLE_NAME, "${User.COL_ID} = $id", null) > 0
    }

    fun updateUser(user: User): Boolean {
        val cv = ContentValues()
        cv.put(User.COL_FULL_NAME, user.fullName)
        cv.put(User.COL_USERNAME, user.userName)
        cv.put(User.COL_EMAIL, user.email)
        cv.put(User.COL_PASSWORD, user.password)
        return sqlDB!!.update(User.TABLE_NAME, cv, "${User.COL_ID} = ${user.id}", null)  > 0
    }

    fun checkLogin(usename: String, password: String): User? {
        var user: User
        val query_params =
            "SELECT * FROM ${User.TABLE_NAME} WHERE ${User.COL_USERNAME} = '$usename' and ${User.COL_PASSWORD} = '$password'"
        val c = sqlDB!!.rawQuery(query_params, null)
        if (c.moveToFirst()) {
            user = User(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4)
            )
            c.close()
        } else {
            return null
        }
        return user
    }
    }


package com.widiarifki.screentest.model

import com.google.gson.annotations.Expose

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by widiarifki on 15/03/2018.
 */

@RealmClass
open class User(@Expose open var name: String = "") : RealmObject() {

    @PrimaryKey
    @Expose
    @Index
    var id: Int = 0

}

package com.widiarifki.screentest.model

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by widiarifki on 24/02/2018.
 */

@RealmClass
open class Guest : RealmObject() {

    @PrimaryKey
    @Expose
    @Index
    var id: Int = 0

    @Expose
    var name: String? = null

    @Expose
    var birthdate: String? = null
}

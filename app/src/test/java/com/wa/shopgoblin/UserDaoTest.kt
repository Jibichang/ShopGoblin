package com.wa.shopgoblin

import com.wa.shopgoblin.data.database.AppDatabase
import com.wa.shopgoblin.data.database.user.User
import com.wa.shopgoblin.data.database.user.UserDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import java.io.IOException

//@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    // can't run
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context, AppDatabase::class.java).build()
//        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Throws(Exception::class)
    fun testSave() {
        val firstName = "Warunee"
        val lastName = "Khammak"

        val entities = User(firstName = firstName, lastName = lastName)

        userDao.insertAll()

        val requestedEntities = userDao.findByName(firstName, lastName)

        // compare result
        assertEquals(entities.id, requestedEntities.id)
    }
}
package com.orafaaraujo.depuis

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.orafaaraujo.depuis.core.database.FactDao
import com.orafaaraujo.depuis.core.database.FactDatabase
import com.orafaaraujo.depuis.core.model.Fact
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class DatabaseUnitTest {

    private lateinit var factDao: FactDao
    private lateinit var database: FactDatabase

    private val fact = Fact(1,
            "title test",
            "desc test",
            LocalDateTime.now().toLocalTime().toNanoOfDay(),
            null,
            true)

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, FactDatabase::class.java).build()
        factDao = database.factDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertFact() {

        factDao.insert(fact)

        val allFacts = factDao.getAllFacts()
        Assert.assertTrue(allFacts.isNotEmpty())
        allFacts.contains(fact)
    }

    @Test
    fun findById() {

        factDao.insert(fact)

        val newFact = factDao.getFactById(fact.id)
        Assert.assertEquals(newFact, fact)
    }

    @Test
    fun findAll() {

        factDao.insert(fact)
        factDao.insert(fact.copy(id = 2))
        factDao.insert(fact.copy(id = 3))

        val allFacts = factDao.getAllFacts()
        Assert.assertTrue(allFacts.isNotEmpty())
        Assert.assertEquals(3, allFacts.size)
    }

    @Test
    fun deleteFact() {

        factDao.insert(fact)
        factDao.delete(fact)

        val emptyList = factDao.getAllFacts()
        Assert.assertTrue(emptyList.isEmpty())

        val newFact = factDao.getFactById(fact.id)
        Assert.assertNotEquals(newFact, fact)
    }
}
package com.kguard.indiary.core.data.repositoryimpl

import com.kguard.indiary.core.database.dao.PersonDAO
import com.kguard.indiary.core.database.entity.Person
import com.kguard.indiary.core.database.entity.toDomainPerson
import com.kguard.indiary.core.data.repository.PersonRepository
import com.kguard.indiary.core.model.DomainPerson
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val dao: PersonDAO
) : PersonRepository {
    override suspend fun getPersons(): List<DomainPerson> {
        return dao.getPersons().map { it.toDomainPerson() }
    }

    override suspend fun getPersonId(name: String): Int {
        return dao.getPersonId(name)
    }

    override suspend fun getPersonsName(): List<String> {
        return dao.getPersons().map { it.name }
    }

    override suspend fun getPerson(person_id: Int): DomainPerson {
        return dao.getPerson(person_id).toDomainPerson()
    }

    override suspend fun updatePerson(person: DomainPerson) {
        val personEntity = Person(
            person.person_id,
            person.name,
            person.birth,
            person.gender,
            person.memo,
            person.make,
            person.favorite
        )
        dao.updatePerson(personEntity)
    }

    override suspend fun insertPerson(person: DomainPerson) {
        val personEntity = Person(
            person.person_id,
            person.name,
            person.birth,
            person.gender,
            person.memo,
            person.make,
            person.favorite
        )
        dao.insertPerson(personEntity)
    }

    override suspend fun deletePerson(person: DomainPerson) {
        val personEntity = Person(
            person.person_id,
            person.name,
            person.birth,
            person.gender,
            person.memo,
            person.make,
            person.favorite
        )
        dao.deletePerson(personEntity)
    }
}
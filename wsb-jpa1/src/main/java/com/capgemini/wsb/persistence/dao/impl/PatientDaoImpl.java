package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) { // TODO - napisac query
        String query = "SELECT DISTINCT v.patient FROM VisitEntity v WHERE v.doctor.firstName = :firstName AND v.doctor.lastName = :lastName";
        return entityManager.createQuery(query,PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) { // TODO - napisac query
        String query = "SELECT DISTINCT v.patient FROM VisitEntity v JOIN v.medicalTreatments m WHERE m.type = :treatmentType";
        return entityManager.createQuery(query,PatientEntity.class)
                .setParameter("treatmentType", treatmentType)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) { // TODO - napisac query
        String query = "SELECT DISTINCT p FROM PatientEntity p JOIN p.addresses a JOIN a.doctors d WHERE d.firstName = :firstName AND d.lastName = :lastName";
        return entityManager.createQuery(query,PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() { // TODO - napisac query
        String query = "SELECT p FROM PatientEntity p WHERE p.addresses IS EMPTY";
        return entityManager.createQuery(query,PatientEntity.class)
                .getResultList();
    }
}

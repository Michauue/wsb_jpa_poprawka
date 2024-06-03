package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) { // TODO - napisac query
        String query = "SELECT d FROM DoctorEntity d WHERE d.specialization = :specialization";
        return entityManager.createQuery(query, DoctorEntity.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) { // TODO - napisac query
        String query = "SELECT COUNT(v) FROM VisitEntity v " +
                "JOIN v.doctor d " +
                "JOIN v.patient p " +
                "WHERE d.firstName = :docFirstName " +
                "AND d.lastName = :docLastName " +
                "AND p.firstName = :patientFirstName " +
                "AND p.lastName = :patientLastName";
        return entityManager.createQuery(query, Long.class)
                .setParameter("docFirstName", docFirstName)
                .setParameter("docLastName", docLastName)
                .setParameter("patientFirstName", patientFirstName)
                .setParameter("patientLastName", patientLastName)
                .getSingleResult();
    }
}

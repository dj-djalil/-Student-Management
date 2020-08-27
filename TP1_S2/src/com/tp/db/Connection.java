package com.tp.db;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tp.model.Domaine;
import com.tp.model.Etudiant;
import com.tp.model.Filiere;
import com.tp.model.Specialite;

public class Connection {

	private static SessionFactory factory;
	private static Session session;

	public SessionFactory getFactory() {
		return factory;
	}

	public Session getSession() {
		return session;
	}

	// Retrieve All Students
	public static List<Etudiant> getListEtudiant() {
		// sessionFactory
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Etudiant.class)
				.buildSessionFactory();
		// session
		session = factory.getCurrentSession();

		// begin transaction
		session.beginTransaction();

		// get All student

		List<Etudiant> listDesEtudiant = session.createQuery("from Etudiant").getResultList();

		// commit transaction
		session.getTransaction().commit();

		factory.close();

		// sort collections
		Collections.sort(listDesEtudiant, new Comparator<Etudiant>() {

			@Override
			public int compare(Etudiant o1, Etudiant o2) {
				if (o1.getMoyenneE() > o2.getMoyenneE())
					return -1;
				else if (o1.getMoyenneE() < o2.getMoyenneE())
					return 1;
				else
					return 0;

			}

		});
		return listDesEtudiant;
	}

	// Retrieve Domaine
	public static List<Domaine> getListDomaine() {
		// sessionFactory
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Domaine.class)
				.buildSessionFactory();
		// session
		session = factory.getCurrentSession();

		// begin transaction
		session.beginTransaction();

		// get All Domaine

		List<Domaine> listDesDomaine = session.createQuery("from Domaine").getResultList();

		// commit transaction
		session.getTransaction().commit();

		factory.close();

		return listDesDomaine;
	}

	// Retrieve Filiere
	public static List<Filiere> getListFiliere() {
		// sessionFactory
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Filiere.class)
				.buildSessionFactory();
		// session
		session = factory.getCurrentSession();

		// begin transaction
		session.beginTransaction();

		// get All Filiere

		List<Filiere> listDesFiliere = session.createQuery("from Filiere").getResultList();

		// commit transaction
		session.getTransaction().commit();

		// close
		factory.close();

		return listDesFiliere;
	}

	// Retrieve Specialite
	public static List<Specialite> getListSpecialite() {
		// sessionFactory
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Specialite.class)
				.buildSessionFactory();
		// session
		session = factory.getCurrentSession();

		// begin transaction
		session.beginTransaction();

		// get All Sprecialite

		List<Specialite> listDesSpecialite = session.createQuery("from Specialite").getResultList();

		// commit transaction
		session.getTransaction().commit();

		factory.close();

		return listDesSpecialite;
	}

	// ajouter etudiant
	public static void ajouterEtudiant(Etudiant e) {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Etudiant.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();

		// begin transaction
		session.beginTransaction();
		// add etudiant
		session.save(e);
		session.getTransaction().commit();
		factory.close();

	}

	// supprimmer Etudaint
	public static void supprimerEtudiant(Etudiant e) {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Etudiant.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();
		// begin transaction
		session.beginTransaction();
		session.delete(e);
		// commit
		session.getTransaction().commit();
		// close factory
		factory.close();
	}

	// modifier etudiant
	public static void modifier(Etudiant e) {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Etudiant.class)
				.buildSessionFactory();
		session = factory.getCurrentSession();
		// begin transaction
		session.beginTransaction();
		// update
		Etudiant etud = new Etudiant();
		etud = session.get(Etudiant.class, e.getNumE());
		etud.setNomE(e.getNomE());
		etud.setPrenomE(e.getPrenomE());
		etud.setDateN(e.getDateN());
		etud.setMoyenneE(e.getMoyenneE());
//		session.createQuery("update Etudiant set nomE ="+ e.getNomE() + ", prenomE ="+ e.getPrenomE()
//				+ " ,  moyenneE ="+ e.getMoyenneE() +  " ,  dateN ="+ e.getDateN()+ " where numE ="+e.getNumE() ).executeUpdate();
        // commit 
		session.getTransaction().commit();
		factory.close();
	}

}

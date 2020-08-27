package com.tp.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tp.db.Connection;

public class test {

	public static void main(String[] args) {

		// // session factory
		// SessionFactory factory = new
		// Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Etudiant.class)
		// .addAnnotatedClass(Domaine.class)
		// .addAnnotatedClass(Specialite.class)
		// .addAnnotatedClass(Filiere.class)
		// .buildSessionFactory();
		//
		// // session
		// Session session = factory.getCurrentSession();
		//
		// try {
		//
		// Etudiant e=new Etudiant("test1","test1",0,"2020-02-02",11);
		//
		// //begin
		//// session.beginTransaction();
		//// session.save(e);
		//// //commit
		//// session.getTransaction().commit();
		////
		//// System.out.println("done !");
		//
		// session.beginTransaction();
		// List<Specialite> d=session.createQuery(" from Specialite ").getResultList();
		//
		// d.forEach(System.out::println);
		// session.getTransaction().commit();
		//
		// System.out.println("done !");
		//
		//
		//
		//
		//
		//
		// } finally {
		// factory.close();
		// }

		Connection x = new Connection();
		List <Filiere>a=  (List<Filiere>) x.getListFiliere().stream().filter(f->f.getNumD()==3).map(f->f);

	}

}

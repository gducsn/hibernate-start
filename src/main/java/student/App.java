package student;

import org.hibernate.Session;

public class App {

	public static void main(String[] args) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Student student = new Student("first", "insert", "test");
		session.save(student);
		session.getTransaction().commit();
		System.out.print("Successfully added!");

	}

}

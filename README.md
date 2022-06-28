![output](https://user-images.githubusercontent.com/94108883/175955558-86bd02e6-64c2-4504-b743-df521aad69cf.gif)

# Hibernate - first

Un semplice esempio per iniziare a capire il funzionamento 
di hibernate, maven e JPA.

Creaiamo un nuovo studente con alcuni 
attributi (nome, email…) e li inviamo al database.

Questo tipo di procedimento senza hibernate e le notazioni JPA 
richiederebbe molte righe di codice. Hibernate e JPA ci permettono di 
semplificare queste azioni.

La struttura:

- pom.xml
- hibernate.cfg.xml
- Student.java
- HibernateUtils.java
- App.java

---

pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.exe</groupId>
	<artifactId>Student_01</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>

	<dependencies>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.28</version>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.6.5.Final</version>
		</dependency>

	</dependencies>
</project>
```

Maven è uno strumento che ci permette di gestire al meglio i nostri 
progetti. Il file pom.xml descrive il nostro progetto, le sue dipendenze e 
tutte le configurazioni più utili per ogni evenienza.

La  parte iniziale del pom definisce alcuni gli aspetti principali del 
nostro progetto. Ad esempio il ‘gropupID’ identica in modo univoco il 
nostro progetto tra tutti i progetti. Un ID gruppo dovrebbe seguire le 
regole del nome come per i pacchetti di Java. Questo significa che 
dovrebbe seguire il nome di dominio invertito (com.example).

Abbiamo anche ‘artifactId’ che definisce il nome del nostro file finale e 
‘version’ in cui definiamo la versione del nostro progetto.

Inoltre abbiamo la possibilità di decidere quale versione del compilatore 
usare nel nostro progetto, in questo caso la versione 1.8.

Il file pom contiene principalmente la definizione delle nostre 
dipendenze, cioè tutte quelle librerie esterne che ci permettono di far 
funzionare il nostro progetto.

In questo caso sono installate due: mysql e hibernate.

In entrambe sono definiti alcuni aspetti, come il groupID, artifact e la 
versione. Ogni aspetto è importate.

---

hibernate.cfg.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD//EN"
    "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>

  	<session-factory>
		<property 
name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
		<property 
name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
		<property 
name="connection.url">jdbc:mysql://localhost:3306/****</property>
		<property name="connection.username">****</property>
		<property name="connection.password">****</property>
		<property name="show_sql">true</property>
		<mapping class="student.Student"></mapping>
	</session-factory>

</hibernate-configuration>
```

Il file hibernate.cfg.xml ci permette di configurare ogni parametro per la 
corretta connessione al database.

Per poterci connetter con hibernate al nostro database c’è 
bisogno prima di tutto di definire il dialect, il quale specifica che tipo 
di database stiamo usando e che tipo di query dovrà fare hibernate.

Successivamente c’è bisogno di selezione il giusto driver il quale cambia 
in base alla versione di mysql in uso.

Fatto questo dobbiamo definire l’url del database, username e password.

Possiamo anche decidere se far comparire in consolle la query fatta da 
hibernate al database con la proprietà 
‘[show_sql](https://mkyong.com/hibernate/hibernate-display-generated-sql-to-console-show_sql-format_sql-and-use_sql_comments/)’ 
attiva.

Infine dobbiamo mappare il nostro database collegandolo alla classe 
specifica per questo compito.

---

Qual è un aspetto importante? La persistenza.
Cosa si intende per persistenza? 

<<La persistenza è "la continuazione di un effetto dopo che la sua causa è 
stata rimossa". Nel contesto della memorizzazione dei dati in un sistema 
informatico, ciò significa che i dati sopravvivono al termine del processo 
con cui sono stati creati. In altre parole, affinché un archivio dati sia 
considerato persistente, deve scrivere in un archivio non volatile. >>

Cerchiamo di avere la possibilità di persiste i dati, quindi averli sempre 
a disposizione nel nostro database, anche quando l’applicazione non è in 
effettivo uso.

Hibernate ci permette di mappare una classe Java e di collegarla 
direttamente con un database.

Questo, unito alle notazione JPA ci permette di avere una classe 
persistente capace di gestire i dati e il database.

L’unica cosa che hibernate ci chiede è di avere un costruttore vuoto 
all’interno della classe.

---

Student.java

```java
package student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	public Student() {

	}

	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// get/set non inseriti

}
```

La classe Student detta POJO (Plain Old Java Objects), è la classe che ci 
permette di mappare il nostro database. Sono presenti al suo interno le 
notazioni JPA per facilitare ulteriormente il mapping.

```java
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
```

Per dichiarare una classe come entità si usa l’apposita annotation subito 
prima della dichiarazione della classe: “@Entity”.

Con una semplice notazione abbiamo definito la nostra classe come 
un’entità persistente.

Ora dobbiamo collegare la tabella del nostro database. Lo facciamo 
utilizzando la notazione “@Table” scrivendo il nome della tabella che 
vogliamo collegare, in questo caso student.

Ora possiamo mappare le colonne del nostro database utilizzando la 
notazione “@Column”. Quello che scriviamo nell’attributo ‘name’ risulta 
essere case sensitive, quindi “FirstName” è decisamente diverso da 
“firstname”.

Il nome della colonna nel table del database deve essere uguale a quello 
scritto nella notazione.

E’ importante definire l’id come chiave principale del nostro database. 
Infatti, non basterà utilizzare la notazione “@Id” per definire 
automaticamente la chiave principale del database. Per farlo dobbiamo 
utilizzare la notazione “@GeneratedValue” la quale ci mette a disposizione 
4 strategie per definire il tipo per la generazione della chiave 
principale:

- AUTO : hibernate decide autonomamente il tipo in base al dialect in 
utilizzo.
- IDENTITY : hibernate aumenterà automaticamente la colonna ID per noi. 
Dal punto di vista del database è molto efficace ma Hibernate richiede un 
valore di chiave primaria per ciascuna entità gestita e pertanto deve 
eseguire immediatamente l'istruzione di INSERT. Ciò gli impedisce di 
utilizzare diverse tecniche di ottimizzazione.
- SEQUENCE : è il tipo di generazione raccomandata nella documentazione di 
hibernate. C’è bisogno di un ulteriore configurazione.
- TABLE : tipo di generazione usata meno. Questo tipo mantiene una tabella 
separata con i valori della chiave primaria.

---

HibernateUtils.java

```java
public class HibernateUtils {

	private static SessionFactory sessionFactory = 
buildSessionFactory();

	
private static SessionFactory buildSessionFactory() {

		try {
			if (sessionFactory == null) {
				StandardServiceRegistry standardRegistry = 
new StandardServiceRegistryBuilder().configure().build();

				Metadata metadata = new 
MetadataSources(standardRegistry).getMetadataBuilder().build();

				sessionFactory = 
metadata.getSessionFactoryBuilder().build();
			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	};

	
public static SessionFactory getSessionFactory() {
		return sessionFactory;
	};

}
```

C’è bisogno di poter utilizzare il file di configurazione per creare una 
nuova sessione.

Come posso utilizzare l’API nativa di hibernate per il bootstrap?

È necessario creare uno StandardServiceRegistry, creare un oggetto 
Metadata e utilizzarlo per creare un'istanza di SessionFactory.

Utilizziamo StandardServiceRegistryBuilder per creare un'istanza di 
StandardServiceRegistry, successivamente carichiamo una configurazione da 
un file di risorse, quindi il nostro hibernate.cfg.xml e infine invochiamo 
il metodo build() per ottenere un'istanza di StandardServiceRegistry.

```java
StandardServiceRegistry standardRegistry = new 
StandardServiceRegistryBuilder().configure().build();
```

In questo modo l'implementazione è semplificata e ci consente di 
modificare la configurazione senza modificare il codice sorgente. 
Hibernate carica automaticamente il file di configurazione dal percorso di 
classe quando chiama il metodo configure su 
StandardServiceRegistryBuilder. 

Dopo aver creato un'istanza di un ServiceRegistry configurato, è 
necessario creare una rappresentazione dei metadati del modello di 
dominio.

Per prima cosa utilizziamo ServiceRegistry creato nel passaggio precedente 
per creare un'istanza di un nuovo oggetto MetadataSources. 

```java
Metadata metadata = new 
MetadataSources(standardRegistry).getMetadataBuilder().build();

sessionFactory = metadata.getSessionFactoryBuilder().build();
```

Una volta istanziato un nuovo oggetto possiamo utilizzarlo per istanziare 
una nuova sessione.

In breve:

Abbiamo creato un nuovo servizio al quale colleghiamo il nostro file di 
configurazione.

Una volta creato dobbiamo inserirlo nell’oggetto metadata. Questo oggetto 
ci permetterà di istanziare una nuova sessione e avere l’effettiva 
connessione.

Infine possiamo creare un nuovo metodo che ci permette di ritornare la 
sessione quando ne abbiamo bisogno:

```java
public static SessionFactory getSessionFactory() {
		return sessionFactory;
	};
```

---

Modello di dominio / [domain 
model](https://stackoverflow.com/questions/1863537/what-is-a-domain-model):

<<Basically, it's the "model" of the objects required for your business 
purposes.

Say you were making a sales tracking website - you'd potentially have 
classes such as Customer, Vendor, Transaction, etc. That entire set of 
classes, as well as the relationships between them, would constitute your 
Domain Model.>>

Il modo in cui gli oggetti/classi del progetto sono costruiti e come sono 
pensati per interagire tra di loro formano, insieme, il modello di 
dominio.

---

App.java

```java
package student;

import org.hibernate.Session;

public class App {

	public static void main(String[] args) {

		Session session = 
HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		Student student = new Student("", "", "");
		session.save(student);
		session.getTransaction().commit();
		System.out.print("Successfully added!");

	}

}
```

La classe ‘App’ contiene il metodo main nel quale è presente tutto il 
necessario per avere una connessione, avviarla, istanziare un nuovo 
studente, inviarlo al database, avviare la transazione e ritornare il 
messaggio di avvenuto successo.

Il metodo .openSession() ci permette di creare la connessione al database 
e aprire un sessione su di esso.

Il metodo .beginTransaction() inizia una nuova operazione.

Istanziamo un nuovo utente.

Inseriamo nel database l’oggetto istanziato con il metodo .save().

Infine rendiamo effettiva la connessione.

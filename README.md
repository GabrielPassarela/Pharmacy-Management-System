Pharmacy Management System 
A web-based pharmacy management system built with Java (MVC architecture) and MySQL, developed as a high school technical project. The app allows users to register and list medications, including details such as name, price, expiration date, prescription type, and description. It also checks expiration status, prescription requirements, and calculates price discounts.

 Technologies
Java • Jakarta Servlet • JSP • MySQL • Maven • HTML • CSS

 Getting Started
Prerequisites

Apache Tomcat 10.1+
MySQL
Maven
Java 21+

Installation

Clone the repository

bash   git clone https://github.com/GabrielPassarela/pharmacy-management.git

Create the database and table in MySQL:

sql   CREATE DATABASE farmacia;

   USE farmacia;

   CREATE TABLE farmacia (
     id INT AUTO_INCREMENT PRIMARY KEY,
     nome VARCHAR(100) NOT NULL,
     descricao TEXT,
     preco DOUBLE NOT NULL,
     tarja VARCHAR(50),
     data_vencimento DATE
   );

Create a context.xml file in src/main/webapp/META-INF/ with your database credentials:

xml   <Context>
     <Resource name="bancoFarmacia"
       auth="Container" type="javax.sql.DataSource"
       username="your_user" password="your_password"
       driverClassName="com.mysql.cj.jdbc.Driver"
       url="jdbc:mysql://localhost:3306/farmacia"
     />
   </Context>

Build and deploy the project, then start Tomcat and access:

   http://localhost:8080/FarmaciaTMaven/

 Screenshots

Coming soon


 Author
Gabriel Passarela
LinkedIn • GitHub

📄 License
This project is for educational purposes.

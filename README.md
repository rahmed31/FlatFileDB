# Flat File Database System
## Introduction

This repository details the creation of a primitive flat file database system using scheduled threads and how to use it.

## What it does

As of now, the database accepts four basic commands:

`CREATE TABLE <table_name> (column1,column 2,column 3,....)`

`SELECT column1,column2,column3,.... FROM <table_name>`

`INSERT INTO <table_name> (value1,value2,....)`

`DROP TABLE <table_name>`

Therefore, the user can create as many tables as s/he wants, insert values for whatever table s/he wants, and can select any number of columns from an individual table.

For example, two tables can be created, and the user can insert and select information in a sequence such as the one below:

1. `CREATE TABLE person (name,phone,age)`

2. `INSERT INTO person (john,7734566474,35)`

3. `INSERT INTO person (mary,3124567343,28)`

4. `CREATE TABLE company (name,phone,address)`

5. `INSERT INTO company (Pepsico,37927392,12 Quaker St.)`

6. `INSERT INTO company (M&M,23423422,987 Mars st.)`

7. `INSERT INTO person (kim,8475675849,30)`

8. `SELECT name,address FROM company`

9. `DROP TABLE person`

Keep in mind, that this is an example and the queries can be executed in whichever order the user likes.

As the user continues to add information to different tables - or remove tables - within their database, the database is continuously rebuilt and written into a file in the same directory as the project and saved under `MyDatabase.txt`. This is done through the use of a scheduled thread. 

Finally, the user interacts with this program via their command line interface.

## Project Design

`Dependency Injection` is used as the primary programming technique for this software. 

The design is described as follows:

A `Database` object depends on different `Tables`, and each `Table` consists of an *n* amount `Rows`. Each row is inherently able to hold an *n* amount of elements according to how the user specifies his or her tables.

The advantages of this are:

- Decoupling the creation of objects (in other word, separate usage from the creation of object).
- Ability to replace dependencies (i.e., Table or Row) without changing the class that uses it (Database).
- Promotes the "code to interface not to implementation" principle if applicable.
- Ability to create and use mock dependency during test.

An `inner class` called `Projection` is utilized inside of the `Table` class as a mechanism of security. Like a normal relational database management system (RDBMS) such as MySQL, users cannot explicitly create their own projections of certain columns within a table. This can only be done through the use of the "SELECT" keyword. Thus, the when a user selects certain columns of a table to be viewed, a `Projection` object is created within the `Table` class, which is then printed out to the console. 

Finally, a number of `lambda` and `stream` expressions are used within this project. The scheduled thread in the `Main` class and the overridden toString() method in the `Database` class were viable candidates to utilize lambdas and/or streams within the project.

## How to Run the Program

The easiest way to run this program is to clone the project and run the main method of `Main.java` through an integrated development environment like IntelliJ. Then, you will be able to interact with the program through its terminal.

Here's the updated README with the PostgreSQL output included as requested:

---

# Inspirahub

Inspirahub is a simple Java-based login and registration system. This README will guide you through the steps to install the necessary dependencies, set up the PostgreSQL database, start the server, and access the application.

## Prerequisites

Before you start, make sure you have the following installed:

- **Java Development Kit (JDK) 11 or later**
- **Maven** (for managing Java project dependencies)
- **PostgreSQL** (for the database)

## Installation

### 1. Install PostgreSQL

If PostgreSQL is not already installed, you can download and install it from [PostgreSQL's official website](https://www.postgresql.org/download/). Follow the instructions for your operating system.

### 2. Set Up PostgreSQL Database

1. After installation, check if your PostgreSQL database connection is running by using the following command:

   ```bash
   sudo systemctl status postgresql@16-main
   ```

   If PostgreSQL is running, you should see output similar to the following:

   ```
   [sudo] password for cybersecurity: 
   ● postgresql@16-main.service - PostgreSQL Cluster 16-main
        Loaded: loaded (/usr/lib/systemd/system/postgresql@.service; enabled; preset: disabled)
       Drop-In: /usr/lib/systemd/system/postgresql@.service.d
                └─kali_postgresql.conf
        Active: active (running) since Tue 2024-09-03 01:46:58 EDT; 7h ago
    Invocation: 72b333a455204168b31bdc4dcde62a57
       Process: 910 ExecStartPre=/usr/share/kali-defaults/postgresql_reduce_shared_buffers 16/main (code=exited, status=0/SUCCESS)
       Process: 919 ExecStart=/usr/bin/pg_ctlcluster --skip-systemctl-redirect 16-main start (code=exited, status=0/SUCCESS)
      Main PID: 969 (postgres)
         Tasks: 6 (limit: 4391)
        Memory: 14.3M (peak: 43.9M swap: 17.3M swap peak: 18.2M)
           CPU: 7.752s
        CGroup: /system.slice/system-postgresql.slice/postgresql@16-main.service
                ├─969 /usr/lib/postgresql/16/bin/postgres -D /var/lib/postgresql/16/main -c config_file=/etc/postgresql/16/main/postgresql.conf
                ├─984 "postgres: 16/main: checkpointer "
                ├─985 "postgres: 16/main: background writer "
                ├─987 "postgres: 16/main: walwriter "
                ├─988 "postgres: 16/main: autovacuum launcher "
                └─989 "postgres: 16/main: logical replication launcher "

   Sep 03 01:46:52 cybersecurity systemd[1]: Starting postgresql@16-main.service - PostgreSQL Cluster 16-main...
   Sep 03 01:46:58 cybersecurity systemd[1]: Started postgresql@16-main.service - PostgreSQL Cluster 16-main.
   ```

   If it’s not running, start the PostgreSQL server with:

   ```bash
   sudo systemctl start postgresql@16-main
   ```

   Then, check the status again.

2. Open the PostgreSQL command-line tool (psql) by typing:


 ```bash
 cybersecurity@cybersecurity:~/insipirahub$ sudo -i -u postgres                     
┏━(Message from Kali developers)
┃
┃ This is a minimal installation of Kali Linux, you likely
┃ want to install supplementary tools. Learn how:
┃ ⇒ https://www.kali.org/docs/troubleshooting/common-minimum-setup/
┃
┗━(Run: “touch ~/.hushlogin” to hide this message)
postgres@cybersecurity:~$ psql
psql (16.4 (Debian 16.4-1))
Type "help" for help.

postgres=# 

```
3. Create a new database:

   ```sql
   CREATE DATABASE inspirahub;
   ```

4. Create a user and grant privileges:

   ```sql
   CREATE USER postgres WITH PASSWORD 'password';
   GRANT ALL PRIVILEGES ON DATABASE inspirahub TO postgres;
   ```

   With these steps, PostgreSQL should be running correctly, and your app is ready to run successfully.

### 3. Clone the Repository

Clone the Inspirahub repository to your local machine:

```bash
git clone https://github.com/yourusername/inspirahub.git
cd inspirahub
```

### 4. Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### 5. Start the Server

Start the application by running:

```bash
mvn spring-boot:run
```

The server should now be running on `http://localhost:8080`.

### 6. Access the Application

Open your web browser and go to:

```
http://localhost:8080
```

You should see the login and registration pages of the Inspirahub application.

## Contributing

If you would like to contribute to this project, feel free to fork the repository and submit a pull request.

## License

This project is licensed under the MIT License.

---

This README now includes the specific output you requested for checking the status of the PostgreSQL service.
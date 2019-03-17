# health-tracker

Short project description.

## Development

### Prerequisites

Install the following:

* Java JRE & JDK
* MySQL
* Netbeans IDE

### Database

Make sure you have MySQL installed.

#### Connecting Your Database In Netbeans

1. First, ensure your MySQL local server is running.

   ```
   mysqladmin -u root -p ping
   ```
   
   Enter your password. You should get the message `mysqld is alive` if it's running.
   
2. Open Netbeans.

3. In the Services tab, expand Databases. You should see `MySQL Server at localhost:3306` (assuming your MySQL server is on the default port 3306).

4. Right-click `MySQL Server at localhost:3306` and click Connect. A new item should show up under Databases that looks like `jdbc:mysql://localhost:3306/healthtracker...`.

5. That's it!

#### Importing The Database Schema

Open a terminal in your `health-tracker` directory. Run the following command to import the database schema (`db/schema.sql`) into your current/local MySQL instance.

```
mysql -u root -p < db/schema.sql
```

If you have Netbeans open when you run this import, you'll need to refresh the database for the changes to show up:

* In the Services tab, under Databases, under `jdbc:mysql://...`, right-click on the `healthtracker` database and click Refresh.

#### Exporting The Database Schema

Open a terminal in your `health-tracker` directory. Run the following command to dump your current `healthtracker` database schema (under the user `root` - if you're using a different MySQL user, change `root` accordingly in the command below) to the `db/schema.sql` file.

```
mysqldump -u root -p --no-data --databases healthtracker > db/schema.sql
```

Only do this if you've made changes to the tables/columns. This is not necessary if you've changed data within your database.

### Running The App

## About The App

### Initial Setup

### Caloric Intake

### BMI Calculator

### Recipe Builder

## Built With

## Authors

* **April** - *---* - [bookwyrm12](https://github.com/bookwyrm12)
* **Duncan** - *---* - [DuncanClelland](https://github.com/DuncanClelland)
* **Maninder** - *---* - [maninderkaur97](https://github.com/maninderkaur97)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* [README template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)

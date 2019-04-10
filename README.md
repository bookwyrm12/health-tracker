# health-tracker

This health tracking app is being developed as a school project. The section below outlines the main features of this application.

## About The App

### Initial Setup

Upon opening up the app for the first time, you will be prompted to enter your user information. This information is used by the system to give BMI, calorie, and nutritional recommendations.

### Food Diary

This feature is where your caloric intake is tracked. You can enter foods consumed and view reports of your daily, monthly, or yearly calorie & nutritional intake.

### Recipe Builder - *Future feature.*

This feature allows foods (with their nutritional information) to be combined, summing their nutritional information into a new food that can then be used in the Food Diary feature.

### Activity Log - *Future feature.*

This feature is where your daily activities can be recorded & tracked, and you can view reports of your daily, monthly, or yearly calories burned based on your activities.

### BMI Calculator - *Future feature.*

This feature calculates your BMI based on your height & weight, and displays where your BMI falls within the recommended BMI range.

## Development

### Prerequisites

Install the following:

* Java JRE & JDK
* MySQL (version 5.7.25)
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

#### Setting Up Your Database Configuration

1. Create a `config/config.properties` file (copy the `config/config.properties.template` file).

2. Update the values for your MySQL username & password (and host if different from the default).

### Running The App

This application can be built and run within Netbeans.

* Under Run in the menu bar, select Run Project to run the project within Netbeans.

* Alternatively, select Build Project to generate the `dist/` directory with the executable `.jar` file. Then, open a console in the directory containing the new `.jar` file, and run the following command:

```
java -jar health-tracker.jar
```

## Authors

* **April** - *Project Manager & Chief Programmer* - [bookwyrm12](https://github.com/bookwyrm12)
* **Duncan** - *Idea Generator & Designer* - [DuncanClelland](https://github.com/DuncanClelland)
* **Maninder** - *Use Case & Database Diagrammer* - [maninderkaur97](https://github.com/maninderkaur97)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* [README template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)

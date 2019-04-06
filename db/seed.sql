-- This file contains data seeds for the development/testing environment

/*
 * Seed test user
 */
INSERT INTO person (name, email, gender, birth_date, goal_weight, goal_calories, activity_level)
    VALUES ('Default User', 'default@this_is_a_fake_email.ca', 'female',
    TO_DATE('01/04/1989', 'DD/MM/YYYY'), 130, 1800, 'low');

SELECT @person_id := LAST_INSERT_ID();

/*
 * Seed test user's weight & height logs
 */
INSERT INTO height_log (person_id, unit, amount, updated_at)
    VALUES (@person_id, 'cm', 162, '2019-03-01 14:35:00');
SELECT @height_log_id := LAST_INSERT_ID();

INSERT INTO weight_log (person_id, unit, amount, updated_at)
    VALUES (@person_id, 'lb', 148, '2019-01-01 17:10:00');
INSERT INTO weight_log (person_id, unit, amount, updated_at)
    VALUES (@person_id, 'lb', 142, '2019-02-01 13:25:00');
INSERT INTO weight_log (person_id, unit, amount, updated_at)
    VALUES (@person_id, 'lb', 140, '2019-03-01 14:40:00');
SELECT @weight_log_id := LAST_INSERT_ID();

/*
 * Set test user's current weight & height
 */
UPDATE person SET current_height_id = @height_log_id WHERE person_id = @person_id;
UPDATE person SET current_weight_id = @weight_log_id WHERE person_id = @person_id;

/*
 * Seed test foods & food logs
 */
INSERT INTO food (name, calories, carbs, proteins, fats)
    VALUES ('apple', 52, 13.8, 0.3, 0.2);
SELECT @food_id := LAST_INSERT_ID();
INSERT INTO food_log (food_id, person_id, quantity, updated_at)
    VALUES (@food_id, @person_id, 1, '2019-04-01 08:55:00');

INSERT INTO food (name, calories, carbs, proteins, fats)
    VALUES ('grilled cheese sandwich', 366, 28, 12, 23);
SELECT @food_id := LAST_INSERT_ID();
INSERT INTO food_log (food_id, person_id, quantity, updated_at)
    VALUES (@food_id, @person_id, 1, '2019-04-01 12:30:00');

INSERT INTO food (name, calories, carbs, proteins, fats)
    VALUES ('quaker granola bar', 98, 19, 1, 2);
SELECT @food_id := LAST_INSERT_ID();
INSERT INTO food_log (food_id, person_id, quantity, updated_at)
    VALUES (@food_id, @person_id, 2, '2019-04-01 15:00:00');

/*
 * Seed test activities
 */

/*
 * Seed test user's activity logs
 */
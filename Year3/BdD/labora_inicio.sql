SELECT *
FROM jersey;

SELECT cnum, name
FROM cyclist
WHERE age <= 25;

SELECT climbname, height
FROM climb
WHERE category = 'E';

SELECT stagenum
FROM stage
WHERE departure = arrival;

SELECT COUNT ( name)
FROM cyclist;

SELECT COUNT ( name)
FROM cyclist
WHERE age > 25;

SELECT COUNT (teamname)
FROM team;

SELECT AVG (age)
FROM cyclist;

SELECT MAX(height), MIN(height)
FROM climb;


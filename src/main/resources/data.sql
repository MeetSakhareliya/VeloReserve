
-- Create 100,000 Users
INSERT INTO users (name, email, mobile, password)
SELECT 'User_' || i, 'user' || i || '@gmail.com', '98' || i, 'Pass_' || i
FROM generate_series(100511, 1000000) AS i;

-- Create 100,000 Passengers linked to those Users
INSERT INTO master_passenger (user_id, name, age, gender)
SELECT i, 'Passenger_' || i, 30, 'MALE'
FROM generate_series(100001, 1000000) AS i;

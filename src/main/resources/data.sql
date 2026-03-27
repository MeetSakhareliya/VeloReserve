-- Insert 10 sample users
-- Insert 10 sample users with unique passwords
INSERT INTO users (name, email, mobile, password) VALUES
('Arjun Mehta', 'arjun.mehta@example.com', '9876543210', 'Arj@Mehta2026'),
('Priya Sharma', 'priya.s@example.com', '9823456789', 'Priya#Secure99'),
('Rohan Das', 'rohan.das@example.com', '9123456780', 'Rohan_Pass_123'),
('Ananya Iyer', 'ananya.i@example.com', '9988776655', 'Ananya@Iyer!'),
('Vikram Singh', 'vikram.s@example.com', '8877665544', 'Vikram99_Strong'),
('Sanya Malhotra', 'sanya.m@example.com', '7766554433', 'Sanya#M_2026'),
('Kabir Bakshi', 'kabir.b@example.com', '6655443322', 'Kabir@Secure!'),
('Ishani Verma', 'ishani.v@example.com', '5544332211', 'Ishani_Verma_88'),
('Aditya Rao', 'aditya.r@example.com', '4433221100', 'Aditya@Rao_Pass'),
('Zara Khan', 'zara.k@example.com', '3322110099', 'Zara#Khan_Security');
--ON CONFLICT (email) DO NOTHING;

-------------------------------------------------------------------------------------------------------

INSERT INTO train (id, name, source_station, destination_station, total_capacity) VALUES
-- Scenario 1: Multiple trains for the SAME Source and SAME Destination
(10000, 'Mumbai-Pune Intercity Exp', 'Mumbai', 'Pune', 1200),
(10001, 'Deccan Queen', 'Mumbai', 'Pune', 1100),
(10002, 'Sinhagad Express', 'Mumbai', 'Pune', 1500),

-- Scenario 2: Same Source, Different Destinations
(10003, 'Howrah Mail', 'Mumbai', 'Kolkata', 1800),
(10004, 'Punjab Mail', 'Mumbai', 'Firozpur', 1700),
(10005, 'Konkan Kanya Exp', 'Mumbai', 'Madgaon', 1300),

-- Scenario 3: Different Sources, Same Destination (New Delhi)
(10006, 'Rajdhani Express', 'Mumbai', 'New Delhi', 900),
(10007, 'Tamil Nadu Express', 'Chennai', 'New Delhi', 1100),
(10008, 'Karnataka Express', 'Bengaluru', 'New Delhi', 1250),
(10009, 'Purushottam Express', 'Puri', 'New Delhi', 1600),

-- Scenario 4: Same Source (Bengaluru), Different Destinations
(10010, 'Brindavan Express', 'Bengaluru', 'Chennai', 1400),
(10011, 'Lalbagh Express', 'Bengaluru', 'Chennai', 1400),
(10012, 'Udyan Express', 'Bengaluru', 'Mumbai', 1200),

-- Scenario 5: Same Destination (Kolkata/Howrah), Different Sources
(10013, 'Gitanjali Express', 'Mumbai', 'Kolkata', 1500),
(10014, 'Coromandel Express', 'Chennai', 'Kolkata', 1650),
(10015, 'Saraighat Express', 'Guwahati', 'Kolkata', 1350),

-- Scenario 6: Unique Routes (No common source/destination with others)
(10016, 'Lucknow Mail', 'Lucknow', 'New Delhi', 1400),
(10017, 'Sabarmati Express', 'Ahmedabad', 'Varanasi', 1600),
(10018, 'Sanghamitra Express', 'Bengaluru', 'Patna', 1800),
(10019, 'Himsagar Express', 'Kanyakumari', 'Jammu Tawi', 1500),
(10020, 'Golden Temple Mail', 'Amritsar', 'Mumbai', 1750),

-- Scenario 7: High Capacity Short Distance
(10021, 'Chennai-Mysuru Shatabdi', 'Chennai', 'Mysuru', 800),
(10022, 'Vande Bharat Exp', 'New Delhi', 'Varanasi', 1128),
(10023, 'Kashi Vishwanath Exp', 'Varanasi', 'New Delhi', 1400),
(10024, 'Gatimaan Express', 'Hazrat Nizamuddin', 'Agra', 750),
(10025, 'Gujrat Mail', 'Ahmedabad', 'Surat', 200);

-------------------------------------------------------------------------------------------------------

INSERT INTO master_passenger (user_id, name, age, gender) VALUES
-- Users 1, 2, and 3: Left empty as requested.

-- User 4: Single Master Passenger (Same as User Name: Ananya Iyer)
(4, 'Ananya Iyer', 28, 'FEMALE'),

-- User 5: Single Master Passenger (Same as User Name: Vikram Singh)
(5, 'Vikram Singh', 35, 'MALE'),

-- User 6: Single Master Passenger (Different name than User: Sanya Malhotra)
(6, 'Rahul Malhotra', 31, 'MALE'),

-- User 7: Single Master Passenger (Different name than User: Kabir Bakshi)
(7, 'Simran Bakshi', 29, 'FEMALE'),

-- User 8: Single Master Passenger (Different name than User: Ishani Verma)
(8, 'Aavya Verma', 5, 'FEMALE'),

-- User 9: Multiple Master Passengers (4 Records)
(9, 'Aditya Rao', 40, 'MALE'),
(9, 'Sneha Rao', 38, 'FEMALE'),
(9, 'Arnav Rao', 12, 'MALE'),
(9, 'Isha Rao', 8, 'FEMALE'),

-- User 10: Multiple Master Passengers (11 Records - Large group/Family)
(10, 'Zara Khan', 26, 'FEMALE'),
(10, 'Yusuf Khan', 28, 'MALE'),
(10, 'Omar Khan', 55, 'MALE'),
(10, 'Fatima Khan', 50, 'FEMALE'),
(10, 'Hassan Ali', 30, 'MALE'),
(10, 'Saira Ali', 27, 'FEMALE'),
(10, 'Zayaan Ali', 4, 'MALE'),
(10, 'Meher Khan', 22, 'FEMALE'),
(10, 'Imran Khan', 24, 'MALE'),
(10, 'Rizwan Sheikh', 32, 'MALE'),
(10, 'Farida Sheikh', 29, 'FEMALE');

---------------------------------------------------------------------------------
INSERT INTO train_trip (train_id, departure_time, arrival_time, available_capacity, version) VALUES
-- Single Trips (Capacity matched to Train total_capacity)
(10003, '2026-04-01 08:00:00', '2026-04-02 14:00:00', 1800, 0), -- Howrah Mail
(10004, '2026-04-01 10:30:00', '2026-04-02 06:00:00', 1700, 0), -- Punjab Mail
(10005, '2026-04-01 23:00:00', '2026-04-02 09:00:00', 1300, 0), -- Konkan Kanya
(10006, '2026-04-02 16:00:00', '2026-04-03 10:00:00', 900, 0),  -- Rajdhani
(10007, '2026-04-02 22:00:00', '2026-04-03 18:30:00', 1100, 0), -- Tamil Nadu Exp
(10008, '2026-04-03 19:30:00', '2026-04-04 15:00:00', 1250, 0), -- Karnataka Exp

-- Multiple Trips: Same Day (Train 10009: 1600 capacity)
(10009, '2026-04-05 05:00:00', '2026-04-06 01:00:00', 1600, 0),
(10009, '2026-04-05 21:00:00', '2026-04-06 17:00:00', 1600, 0),

-- Multiple Trips: Different Days (Train 10010: 1400 capacity)
(10010, '2026-04-01 06:00:00', '2026-04-01 11:30:00', 1400, 0),
(10011, '2026-04-02 06:00:00', '2026-04-02 11:30:00', 1400, 0), -- Train 10011 also 1400
(10010, '2026-04-03 06:00:00', '2026-04-03 11:30:00', 1400, 0),

-- Frequent Service (Capacities: 10021=800, 10022=1128, 10024=750)
(10021, '2026-04-01 06:00:00', '2026-04-01 13:00:00', 800, 0),
(10021, '2026-04-02 06:00:00', '2026-04-02 13:00:00', 800, 0),
(10022, '2026-04-01 06:00:00', '2026-04-01 14:00:00', 1128, 0),
(10022, '2026-04-02 06:00:00', '2026-04-02 14:00:00', 1128, 0),
(10024, '2026-04-01 08:10:00', '2026-04-01 09:50:00', 750, 0),
(10024, '2026-04-01 17:00:00', '2026-04-01 18:40:00', 750, 0),
(10025, '2026-04-01 17:00:00', '2026-04-01 18:40:00', 200, 0),

-- Long Distance/Weekly Service
(10012, '2026-04-01 20:00:00', '2026-04-02 18:00:00', 1200, 0), -- Udyan: 1200
(10013, '2026-04-04 12:00:00', '2026-04-05 20:00:00', 1500, 0), -- Gitanjali: 1500
(10014, '2026-04-01 15:30:00', '2026-04-02 23:00:00', 1650, 0), -- Coromandel: 1650
(10015, '2026-04-02 11:00:00', '2026-04-03 14:00:00', 1350, 0), -- Saraighat: 1350
(10016, '2026-04-01 22:00:00', '2026-04-02 07:00:00', 1400, 0), -- Lucknow Mail: 1400
(10017, '2026-04-03 21:00:00', '2026-04-05 06:00:00', 1600, 0), -- Sabarmati: 1600
(10018, '2026-04-04 09:00:00', '2026-04-05 22:00:00', 1800, 0), -- Sanghamitra: 1800
(10019, '2026-04-01 16:00:00', '2026-04-04 11:00:00', 1500, 0), -- Himsagar: 1500
(10020, '2026-04-05 18:00:00', '2026-04-06 20:00:00', 1750, 0), -- Golden Temple: 1750
(10016, '2026-04-02 22:00:00', '2026-04-03 07:00:00', 1400, 0),
(10017, '2026-04-10 21:00:00', '2026-04-12 06:00:00', 1600, 0),
(10018, '2026-04-11 09:00:00', '2026-04-12 22:00:00', 1800, 0);

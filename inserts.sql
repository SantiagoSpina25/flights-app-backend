INSERT INTO roles (name) VALUES
("ROLE_USER"),
("ROLE_ADMIN");

INSERT INTO countries (name, iso_code, continent) VALUES
('Argentina', 'ARG', 'SOUTH_AMERICA'),
('Brazil', 'BRA', 'SOUTH_AMERICA'),
('Chile', 'CHL', 'SOUTH_AMERICA'),
('Uruguay', 'URY', 'SOUTH_AMERICA'),
('Paraguay', 'PRY', 'SOUTH_AMERICA'),
('Bolivia', 'BOL', 'SOUTH_AMERICA'),
('Peru', 'PER', 'SOUTH_AMERICA'),
('Colombia', 'COL', 'SOUTH_AMERICA'),
('Ecuador', 'ECU', 'SOUTH_AMERICA'),
('Venezuela', 'VEN', 'SOUTH_AMERICA'),

('United States', 'USA', 'NORTH_AMERICA'),
('Canada', 'CAN', 'NORTH_AMERICA'),
('Mexico', 'MEX', 'NORTH_AMERICA'),
('Guatemala', 'GTM', 'NORTH_AMERICA'),
('El Salvador', 'SLV', 'NORTH_AMERICA'),
('Honduras', 'HND', 'NORTH_AMERICA'),
('Nicaragua', 'NIC', 'NORTH_AMERICA'),
('Costa Rica', 'CRI', 'NORTH_AMERICA'),
('Panama', 'PAN', 'NORTH_AMERICA'),
('Cuba', 'CUB', 'NORTH_AMERICA'),

('Spain', 'ESP', 'EUROPE'),
('France', 'FRA', 'EUROPE'),
('Italy', 'ITA', 'EUROPE'),
('Germany', 'DEU', 'EUROPE'),
('United Kingdom', 'GBR', 'EUROPE'),
('Portugal', 'PRT', 'EUROPE'),
('Netherlands', 'NLD', 'EUROPE'),
('Belgium', 'BEL', 'EUROPE'),
('Switzerland', 'CHE', 'EUROPE'),
('Sweden', 'SWE', 'EUROPE'),

('South Africa', 'ZAF', 'AFRICA'),
('Nigeria', 'NGA', 'AFRICA'),
('Egypt', 'EGY', 'AFRICA'),
('Morocco', 'MAR', 'AFRICA'),
('Kenya', 'KEN', 'AFRICA'),
('Ethiopia', 'ETH', 'AFRICA'),
('Ghana', 'GHA', 'AFRICA'),
('Algeria', 'DZA', 'AFRICA'),
('Tunisia', 'TUN', 'AFRICA'),
('Senegal', 'SEN', 'AFRICA'),

('China', 'CHN', 'ASIA'),
('Japan', 'JPN', 'ASIA'),
('South Korea', 'KOR', 'ASIA'),
('India', 'IND', 'ASIA'),
('Thailand', 'THA', 'ASIA'),
('Vietnam', 'VNM', 'ASIA'),
('Philippines', 'PHL', 'ASIA'),
('Indonesia', 'IDN', 'ASIA'),
('Malaysia', 'MYS', 'ASIA'),
('Saudi Arabia', 'SAU', 'ASIA'),

('Australia', 'AUS', 'OCEANIA'),
('New Zealand', 'NZL', 'OCEANIA'),
('Fiji', 'FJI', 'OCEANIA'),
('Papua New Guinea', 'PNG', 'OCEANIA'),
('Samoa', 'WSM', 'OCEANIA');

INSERT INTO airports (name, iata_code, city, latitude, longitude, country_id) VALUES
-- SOUTH AMERICA
('Ministro Pistarini International Airport', 'EZE', 'Buenos Aires', -34.8222, -58.5358, 1),
('São Paulo/Guarulhos International Airport', 'GRU', 'São Paulo', -23.4356, -46.4731, 2),
('Comodoro Arturo Merino Benítez International Airport', 'SCL', 'Santiago', -33.3929, -70.7858, 3),
('Carrasco International Airport', 'MVD', 'Montevideo', -34.8384, -56.0308, 4),
('Jorge Chávez International Airport', 'LIM', 'Lima', -12.0219, -77.1143, 7),

-- NORTH AMERICA
('Los Angeles International Airport', 'LAX', 'Los Angeles', 33.9416, -118.4085, 11),
('John F. Kennedy International Airport', 'JFK', 'New York', 40.6413, -73.7781, 11),
('Toronto Pearson International Airport', 'YYZ', 'Toronto', 43.6777, -79.6248, 12),
('Benito Juárez International Airport', 'MEX', 'Mexico City', 19.4361, -99.0719, 13),
('Tocumen International Airport', 'PTY', 'Panama City', 9.0714, -79.3835, 19),

-- EUROPE
('Adolfo Suárez Madrid–Barajas Airport', 'MAD', 'Madrid', 40.4983, -3.5676, 21),
('Charles de Gaulle Airport', 'CDG', 'Paris', 49.0097, 2.5479, 22),
('Leonardo da Vinci–Fiumicino Airport', 'FCO', 'Rome', 41.8003, 12.2389, 23),
('Frankfurt Airport', 'FRA', 'Frankfurt', 50.0379, 8.5622, 24),
('Heathrow Airport', 'LHR', 'London', 51.4700, -0.4543, 25),

-- ASIA
('Beijing Capital International Airport', 'PEK', 'Beijing', 40.0799, 116.6031, 41),
('Haneda Airport', 'HND', 'Tokyo', 35.5494, 139.7798, 42),
('Indira Gandhi International Airport', 'DEL', 'New Delhi', 28.5562, 77.1000, 44),
('Dubai International Airport', 'DXB', 'Dubai', 25.2532, 55.3657, 50),
('Suvarnabhumi Airport', 'BKK', 'Bangkok', 13.6900, 100.7501, 45);

INSERT INTO airlines (name, description) VALUES
('LATAM Airlines', 'Leading airline in South America, operating flights across Latin America, North America, Europe and Oceania.'),
('Air France', 'Flag carrier of France, offering global routes with a focus on comfort and premium service.'),
('Lufthansa', 'Germany’s largest airline, part of the Lufthansa Group, serving destinations worldwide.'),
('British Airways', 'United Kingdom’s flagship carrier, providing extensive international and domestic routes.'),
('Emirates', 'Dubai-based airline known for luxury service and long-haul international flights.');

INSERT INTO flights (id, date, hour, destination_airport_id, origin_airport_id, airline_id) VALUES
('LAT001', '2025-11-10', '08:30:00', 6, 1, 1),   -- LATAM: Buenos Aires → Los Angeles
('AFR002', '2025-11-10', '09:45:00', 11, 7, 2),  -- Air France: New York → Madrid
('LUF003', '2025-11-11', '14:00:00', 13, 12, 3), -- Lufthansa: Paris → Rome
('EMI004', '2025-11-12', '22:15:00', 19, 10, 5), -- Emirates: Panama City → Dubai
('LAT005', '2025-11-13', '06:20:00', 2, 3, 1),   -- LATAM: Santiago → São Paulo
('LUF006', '2025-11-13', '12:50:00', 8, 9, 3),   -- Lufthansa: Mexico City → Toronto
('BAW007', '2025-11-14', '17:40:00', 14, 15, 4), -- British Airways: London → Frankfurt
('LAT008', '2025-11-14', '20:10:00', 4, 5, 1),   -- LATAM: Lima → Montevideo
('EMI009', '2025-11-15', '07:25:00', 17, 18, 5), -- Emirates: Tokyo → Beijing
('AFR010', '2025-11-15', '15:55:00', 16, 11, 2); -- Air France: Madrid → Haneda (Tokyo)

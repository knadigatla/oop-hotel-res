INSERT INTO room_type (id, room_type, price, details) VALUES (1, 'Guest Room King', 200, '1 King, Sofa bed');
INSERT INTO room_type (id, room_type, price, details) VALUES (2, 'Guest Room Queen', 200, '2 Queen, Sofa bed');
INSERT INTO room_type (id, room_type, price, details) VALUES (3, 'Bedroom Junior Suite King', 400, '1 King, Sofa bed, kitchen, living room');
INSERT INTO room_type (id, room_type, price, details) VALUES (4, 'Bedroom Junior Suite Queen', 400, '2 Queen, Sofa bed, kitchen, living room');
INSERT INTO room_type (id, room_type, price, details) VALUES (5, 'Bedroom Presidential Suite', 800, '2 King, Sofa bed, kitchen, living room');

INSERT INTO room_info (room_number, room_type_id) VALUES (100, 1);
INSERT INTO room_info (room_number, room_type_id) VALUES (102, 1);
INSERT INTO room_info (room_number, room_type_id) VALUES (104, 1);
INSERT INTO room_info (room_number, room_type_id) VALUES (106, 1);
INSERT INTO room_info (room_number, room_type_id) VALUES (108, 1);
INSERT INTO room_info (room_number, room_type_id) VALUES (101, 2);
INSERT INTO room_info (room_number, room_type_id) VALUES (103, 2);
INSERT INTO room_info (room_number, room_type_id) VALUES (105, 2);
INSERT INTO room_info (room_number, room_type_id) VALUES (107, 2);
INSERT INTO room_info (room_number, room_type_id) VALUES (109, 2);
INSERT INTO room_info (room_number, room_type_id) VALUES (200, 3);
INSERT INTO room_info (room_number, room_type_id) VALUES (202, 3);
INSERT INTO room_info (room_number, room_type_id) VALUES (204, 3);
INSERT INTO room_info (room_number, room_type_id) VALUES (201, 4);
INSERT INTO room_info (room_number, room_type_id) VALUES (203, 4);
INSERT INTO room_info (room_number, room_type_id) VALUES (205, 4);
INSERT INTO room_info (room_number, room_type_id) VALUES (333, 5);
INSERT INTO room_info (room_number, room_type_id) VALUES (444, 5);

INSERT INTO user_info (id, first_name, last_name, address, email) VALUES (1, 'Chris', 'Fields', 'Tucson, AZ', 'chris@gmail.com');
INSERT INTO user_info (id, first_name, last_name, address, email) VALUES (2, 'Roger', 'Federer', 'Newyork City, NY', 'feddy@gmail.com');
INSERT INTO user_info (id, first_name, last_name, address, email) VALUES (3, 'Sachin', 'Tendulkar', 'Atlanta, GA', 'god@gmail.com');
INSERT INTO user_info (id, first_name, last_name, address, email) VALUES (4, 'Tom', 'Brady', 'NewEngland, NE', 'tom@gmail.com');
INSERT INTO user_info (id, first_name, last_name, address, email) VALUES (5, 'Lebron', 'James', 'Akron, OH', 'kingjames@gmail.com');

INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-21345-CF', 1, '2016-04-28', 107, 1);
INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-21345-CF', 1, '2016-04-29', 107, 1);
INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-21345-CF', 1, '2016-04-30', 107, 1);
INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-12452-TB', 4, '2016-05-01', 444, 1);
INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-12452-TB', 4, '2016-05-02', 444, 1);
INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-65343-LJ', 5, '2016-04-29', 333, 1);
INSERT INTO reservation_details (confirmation_code, user_id, booking_for_date, room_number, status) VALUES ('1-65343-LJ', 5, '2016-04-30', 333, 1);

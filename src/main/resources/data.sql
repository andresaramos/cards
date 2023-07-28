insert into CARDS (id, marca, numero, holder, fecha_vencimiento) values
    (1, 'VISA', '2736498724537654', 'Juan Perez', '2025-10-19'),
    (2, 'NARA', '3647587349582635', 'Roberto Lopez', '2023-07-01'),
    (3, 'AMEX', '3004876535498252', 'Daniel Jimenez', '2030-01-03');

insert into OPERATIONS (id, importe, card_id) values
    (1, 750, 1),
    (2, 5000, 1),
    (3, 10000, 1),
    (4, 1450, 2),
    (5, 890, 2),
    (6, 9800, 3),
    (7, 3200, 3),
    (8, 5000, 3),
    (9, 5000, 3),
    (10, 5000, 3);
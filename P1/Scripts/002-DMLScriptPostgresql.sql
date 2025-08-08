-- Insertar datos en la tabla Usuarios
INSERT INTO "public"."Usuarios" ("nombre", "apellido", "nombre_usuario", "email", "hash_contrasena", "fecha_registro") VALUES
('Ana', 'García', 'anagarcia', 'ana.garcia@example.com', 'hash_simulado_12345', '2025-08-01 10:00:00'),
('Carlos', 'Martínez', 'carlosm', 'carlos.martinez@example.com', 'hash_simulado_67890', '2025-08-02 11:30:00'),
('Laura', 'López', 'lauralopez', 'laura.lopez@example.com', 'hash_simulado_abcde', '2025-08-03 14:15:00'),
('Javier', 'Rodríguez', 'javierr', 'javier.rodriguez@example.com', 'hash_simulado_fghij', '2025-08-04 09:05:00');

-- Insertar datos en la tabla Etiquetas
INSERT INTO "public"."Etiquetas" ("nombre") VALUES
('Tecnología'),
('Ciencia'),
('Viajes'),
('Programación'),
('Cocina'),
('APIs RESTFul');

-- Insertar datos en la tabla Publicaciones
-- Nota: Los IDs se auto-incrementarán desde 1
INSERT INTO "public"."Publicaciones" ("autor_id", "titulo", "contenido", "fecha_creacion") VALUES
(1, 'Introducción a SQL', 'En este artículo, exploraremos los fundamentos de SQL, el lenguaje estándar para gestionar bases de datos relacionales', '2025-08-05 15:00:00'),
(2, 'Mi Viaje sincelejo', 'Una aventura por una ciudad comunmente confundida con un pueblo', '2025-08-06 18:20:00'),
(1, 'Desarrollo de APIs con Java', 'Aprende a construir APIs RESTful robustas y escalables utilizando Java con SpringBoot', '2025-08-07 11:45:00'),
(3, 'La Receta de pollo frito', 'Descubre los secretos de la abuela para un buen pollo casero que sorprenderá a todos tus invitados', '2025-08-07 16:00:00');

-- Insertar datos en la tabla Publicacion_Etiquetas
-- Asumiendo que las publicaciones tienen IDs 1, 2, 3, 4 y las etiquetas 1-6
INSERT INTO "public"."Publicacion_Etiquetas" ("publicacion_id", "etiqueta_id") VALUES
-- Publicación 1:
(1, 1), -- Tecnología
(1, 4), -- Programación

-- Publicación 2: 
(2, 3), -- Viajes

-- Publicación 3: 
(3, 1), -- Tecnología
(3, 4), -- Programación
(3, 6), -- Inteligencia Artificial

-- Publicación 4:
(4, 5); -- Cocina

-- Insertar datos en la tabla Comentarios
INSERT INTO "public"."Comentarios" ("publicacion_id", "autor_id", "contenido", "fecha_creacion") VALUES
-- Comentarios en Publicación 1 ('Introducción a SQL')
(1, 2, 'Excelente articulo.', '2025-08-05 16:30:00'),
(1, 4, 'Gracias por compartir. Me ha servido para repasar', '2025-08-06 09:10:00'),

-- Comentarios en Publicación 2 ('Mi Viaje...')
(2, 1, 'Que buenas fotos', '2025-08-06 20:05:00'),

-- Comentarios en Publicación 3 ('Desarrollo de APIs...')
(3, 2, '¡Muy bien explicado!', '2025-08-07 12:00:00'),
(3, 1, 'Consideraré añadir ejemplos con WebSockets en una futura actualización', '2025-08-07 12:30:00'),

-- Comentarios en Publicación 4 ('La Receta de Lasaña')
(4, 2, 'La preparé el fin de semana y fue buenisimo', '2025-08-09 19:55:00');
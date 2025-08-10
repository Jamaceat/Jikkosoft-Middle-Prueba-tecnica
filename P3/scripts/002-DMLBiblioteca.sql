INSERT INTO public.roles (nombre_rol, descripcion_rol)
SELECT 'ROLE_ADMIN', 'Rol con permisos totales para gestionar el sistema.'
WHERE NOT EXISTS (SELECT 1 FROM public.roles WHERE nombre_rol = 'ROLE_ADMIN');

INSERT INTO public.roles (nombre_rol, descripcion_rol)
SELECT 'ROLE_USER', 'Rol con permisos básicos para usuarios regulares.'
WHERE NOT EXISTS (SELECT 1 FROM public.roles WHERE nombre_rol = 'ROLE_USER');

INSERT INTO public.roles (nombre_rol, descripcion_rol)
SELECT 'ROLE_SUPPLIER', 'Rol para usuarios encargados de abastecer y gestionar el inventario de libros.'
WHERE NOT EXISTS (SELECT 1 FROM public.roles WHERE nombre_rol = 'ROLE_SUPPLIER');


INSERT INTO public.miembros (nombre, email, telefono, hash_contrasenia)
SELECT 'Johan Mendez', 'admin@biblioteca.com', '3004450638', '$2a$12$po6sUNBdroEo6f2B.s/lbe.zXkEgkS6snx4VJ.AjzRRMnkzgqdBre'
WHERE NOT EXISTS (SELECT 1 FROM public.miembros WHERE email = 'admin@biblioteca.com');

INSERT INTO public.roles_miembros (miembro_id, rol_id)
SELECT
    (SELECT id FROM public.miembros WHERE email = 'admin@biblioteca.com'),
    (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_ADMIN')
WHERE NOT EXISTS (
    -- Se asegura de no insertar la relación si ya existe.
    SELECT 1 FROM public.roles_miembros
    WHERE miembro_id = (SELECT id FROM public.miembros WHERE email = 'admin@biblioteca.com')
    AND rol_id = (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_ADMIN')
);


INSERT INTO public.miembros (nombre, email, telefono, hash_contrasenia)
SELECT 'Andres Macea', 'user@biblioteca.com', '3101234567', '$2a$12$po6sUNBdroEo6f2B.s/lbe.zXkEgkS6snx4VJ.AjzRRMnkzgqdBre'
WHERE NOT EXISTS (SELECT 1 FROM public.miembros WHERE email = 'user@biblioteca.com');

INSERT INTO public.roles_miembros (miembro_id, rol_id)
SELECT
    (SELECT id FROM public.miembros WHERE email = 'user@biblioteca.com'),
    (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_USER')
WHERE NOT EXISTS (
    SELECT 1 FROM public.roles_miembros
    WHERE miembro_id = (SELECT id FROM public.miembros WHERE email = 'user@biblioteca.com')
    AND rol_id = (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_USER')
);

INSERT INTO public.miembros (nombre, email, telefono, hash_contrasenia)
SELECT 'Carlos Perez', 'supplier@biblioteca.com', '3209876543', '$2a$12$po6sUNBdroEo6f2B.s/lbe.zXkEgkS6snx4VJ.AjzRRMnkzgqdBre'
WHERE NOT EXISTS (SELECT 1 FROM public.miembros WHERE email = 'supplier@biblioteca.com');

INSERT INTO public.roles_miembros (miembro_id, rol_id)
SELECT
    (SELECT id FROM public.miembros WHERE email = 'supplier@biblioteca.com'),
    (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_SUPPLIER')
WHERE NOT EXISTS (
    SELECT 1 FROM public.roles_miembros
    WHERE miembro_id = (SELECT id FROM public.miembros WHERE email = 'supplier@biblioteca.com')
    AND rol_id = (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_SUPPLIER')
);

INSERT INTO public.parametros (nombre_parametro, valor_parametro) VALUES ('PRESTAMO_DIAS_HASTA_RETORNO', '15');
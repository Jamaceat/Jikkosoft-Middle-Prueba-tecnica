INSERT INTO public.roles (nombre_rol, descripcion_rol)
SELECT 'ROLE_ADMIN', 'Rol con permisos totales para gestionar el sistema.'
WHERE NOT EXISTS (SELECT 1 FROM public.roles WHERE nombre_rol = 'ROLE_ADMIN');

INSERT INTO public.miembros (nombre, email, telefono, hash_contrasenia)
SELECT 'Admin Principal', 'admin@biblioteca.com', '3004450638', '$2a$12$po6sUNBdroEo6f2B.s/lbe.zXkEgkS6snx4VJ.AjzRRMnkzgqdBre'
WHERE NOT EXISTS (SELECT 1 FROM public.miembros WHERE email = 'admin@biblioteca.com');

INSERT INTO public.roles_miembros (id_miembro, id_rol)
SELECT
    (SELECT id FROM public.miembros WHERE email = 'admin@biblioteca.com'),
    (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_ADMIN')
WHERE NOT EXISTS (
    -- Se asegura de no insertar la relación si ya existe.
    SELECT 1 FROM public.roles_miembros
    WHERE id_miembro = (SELECT id FROM public.miembros WHERE email = 'admin@biblioteca.com')
    AND id_rol = (SELECT id FROM public.roles WHERE nombre_rol = 'ROLE_ADMIN')
);

SELECT
    m.id AS miembro_id,
    m.nombre,
    m.email,
    r.nombre_rol
FROM
    public.miembros m
JOIN
    public.roles_miembros rm ON m.id = rm.id_miembro
JOIN
    public.roles r ON rm.id_rol = r.id
WHERE
    m.email = 'admin@biblioteca.com';
-- ========= CREACIÓN DE TIPOS PERSONALIZADOS (ENUMS) =========
-- Se crea un tipo ENUM para manejar los estados de los prestamos de forma controlada.
CREATE TYPE public.estado_prestamo AS ENUM ('Prestado', 'Devuelto', 'Atrasado');


-- ========= CREACIÓN DE TABLAS PRINCIPALES (SIN DEPENDENCIAS) =========

-- Tabla para almacenar las sucursales de la biblioteca.
CREATE TABLE public.sucursales (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    direccion TEXT
);

-- Tabla para definir los roles de los usuarios (ej. Administrador, Miembro).
CREATE TABLE public.roles (
    id SERIAL PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL,
    descripcion_rol VARCHAR(300)
);

-- Tabla para almacenar la información de los miembros de la biblioteca.
CREATE TABLE public.miembros (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    hash_contrasenia VARCHAR(200) NOT NULL
);


-- ========= CREACIÓN DE TABLAS DEPENDIENTES =========

-- Tabla para el catálogo de libros, depende de 'sucursales'.
CREATE TABLE public.libros (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(150),
    ano_publicacion INT,
    genero VARCHAR(50),
    sucursal_id INT,
    cantidad_total INT NOT NULL,
    cantidad_disponible INT NOT NULL,
    fecha_registro TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sucursal
        FOREIGN KEY(sucursal_id)
        REFERENCES public.sucursales(id)
        ON DELETE SET NULL -- Si se borra una sucursal, el libro queda sin asignación.
);


-- ========= CREACIÓN DE TABLAS DE UNION (MUCHOS A MUCHOS) =========

-- Tabla de union para asignar roles a los miembros.
CREATE TABLE public.roles_miembros (
    id BIGSERIAL PRIMARY KEY,
    miembro_id BIGINT NOT NULL,
    rol_id INT NOT NULL,

    CONSTRAINT fk_miembro
        FOREIGN KEY(miembro_id)
        REFERENCES public.miembros(id)
        ON DELETE CASCADE, -- Si se borra un miembro, sus roles se eliminan.

    CONSTRAINT fk_rol
        FOREIGN KEY(rol_id)
        REFERENCES public.roles(id)
        ON DELETE CASCADE, -- Si se borra un rol, se quita de todos los miembros.

    -- Restricción para asegurar que un miembro no tenga el mismo rol dos veces.
    UNIQUE (miembro_id, rol_id)
);

-- Tabla para gestionar los préstamos de libros a miembros.
CREATE TABLE public.prestamos (
    id BIGSERIAL PRIMARY KEY,
    libro_id BIGINT NOT NULL,
    miembro_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_devolucion_esperada DATE NOT NULL,
    fecha_devolucion_real DATE,
    estado public.estado_prestamo NOT NULL,

    CONSTRAINT fk_libro
        FOREIGN KEY(libro_id)
        REFERENCES public.libros(id)
        ON DELETE RESTRICT, -- No permitir borrar un libro si tiene préstamos activos.

    CONSTRAINT fk_miembro
        FOREIGN KEY(miembro_id)
        REFERENCES public.miembros(id)
        ON DELETE RESTRICT -- No permitir borrar un miembro si tiene préstamos activos.
);
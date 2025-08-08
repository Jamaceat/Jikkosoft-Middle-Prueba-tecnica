CREATE SCHEMA IF NOT EXISTS "public";

CREATE TABLE "public"."Comentarios" (
    "id" SERIAL,
    "publicacion_id" integer NOT NULL,
    "autor_id" integer NOT NULL,
    "contenido" text NOT NULL,
    "fecha_creacion" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "fecha_actualizacion" timestamp,
    PRIMARY KEY ("id")
);
-- Indexes
CREATE INDEX "Comentarios_idx_comentarios_publicacion" ON "public"."Comentarios" ("publicacion_id");
CREATE INDEX "Comentarios_idx_comentarios_autor" ON "public"."Comentarios" ("autor_id");

CREATE TABLE "public"."Publicacion_Etiquetas" (
    "publicacion_id" integer NOT NULL,
    "etiqueta_id" integer NOT NULL,
    PRIMARY KEY ("publicacion_id", "etiqueta_id")
);

CREATE TABLE "public"."Usuarios" (
    "id" SERIAL,
    "nombre" varchar(50) NOT NULL,
    "apellido" varchar(50) NOT NULL,
    "nombre_usuario" varchar(50) NOT NULL UNIQUE,
    "email" varchar(100) NOT NULL UNIQUE,
    "hash_contrasena" varchar(255) NOT NULL,
    "fecha_registro" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "fecha_actualizacion" timestamp,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."Publicaciones" (
    "id" SERIAL,
    "autor_id" integer NOT NULL,
    "titulo" varchar(255) NOT NULL,
    "contenido" text NOT NULL,
    "fecha_creacion" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "fecha_actualizacion" timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id")
);
-- Indexes
CREATE INDEX "Publicaciones_idx_publicaciones_autor" ON "public"."Publicaciones" ("autor_id");

CREATE TABLE "public"."Etiquetas" (
    "id" SERIAL,
    "nombre" varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

-- Restricciones clave primaria
-- Schema: public
ALTER TABLE "public"."Comentarios" ADD CONSTRAINT "fk_Comentarios_autor_id_Usuarios_id" FOREIGN KEY("autor_id") REFERENCES "public"."Usuarios"("id");
ALTER TABLE "public"."Comentarios" ADD CONSTRAINT "fk_Comentarios_publicacion_id_Publicaciones_id" FOREIGN KEY("publicacion_id") REFERENCES "public"."Publicaciones"("id");
ALTER TABLE "public"."Publicacion_Etiquetas" ADD CONSTRAINT "fk_Publicacion_Etiquetas_etiqueta_id_Etiquetas_id" FOREIGN KEY("etiqueta_id") REFERENCES "public"."Etiquetas"("id");
ALTER TABLE "public"."Publicacion_Etiquetas" ADD CONSTRAINT "fk_Publicacion_Etiquetas_publicacion_id_Publicaciones_id" FOREIGN KEY("publicacion_id") REFERENCES "public"."Publicaciones"("id");
ALTER TABLE "public"."Publicaciones" ADD CONSTRAINT "fk_Publicaciones_autor_id_Usuarios_id" FOREIGN KEY("autor_id") REFERENCES "public"."Usuarios"("id");
-- Dataset de prueba para la aplicación GestionComercial
-- Inserciones ordenadas respetando dependencias

-- 1) Departamentos
INSERT INTO departamento (id, nombre) VALUES
(1, 'Antioquia'),
(2, 'Cundinamarca'),
(3, 'Valle del Cauca');

-- 2) Ciudades (FK departamento)
INSERT INTO ciudad (id, nombre, departamento_id_fk) VALUES
(1, 'Medellin', 1),
(2, 'Envigado', 1),
(3, 'Bogota', 2),
(4, 'Cali', 3),
(5, 'Palmira', 3);

-- 3) Almacenes
INSERT INTO almacen (id, nombre) VALUES
(1, 'Almacen Central'),
(2, 'Almacen Secundario');

-- 4) Productos
-- (fechaCreacion y fechaModificacion usan formato 'YYYY-MM-DD HH:MM:SS')
-- Note: Hibernate Physical NamingStrategy suele convertir camelCase a snake_case
INSERT INTO producto (id, nombre, precio, descripcion, fecha_creacion, fecha_modificacion) VALUES
(1, 'Camiseta', 19.99, 'Camiseta algodón unisex', '2025-01-10 09:00:00', NULL),
(2, 'Pantalon', 39.50, 'Pantalón jean', '2025-01-11 10:15:00', NULL),
(3, 'Zapatos', 59.99, 'Zapatos deportivos', '2025-02-05 14:30:00', NULL),
(4, 'Gorra', 12.00, 'Gorra con logo', '2025-03-01 11:00:00', NULL),
(5, 'Bufanda', 8.75, 'Bufanda de lana', '2025-03-02 12:00:00', NULL),
(6, 'Chaqueta', 89.90, 'Chaqueta impermeable', '2025-04-12 08:45:00', NULL);

-- 5) Categorias
INSERT INTO categoria (id, nombre) VALUES
(1, 'Ropa'),
(2, 'Calzado'),
(3, 'Accesorios');

-- 6) Relación producto-categoria (tabla producto_categoria)
INSERT INTO producto_categoria (categoria_id_fk, producto_id_fk) VALUES
(1, 1), -- Camiseta -> Ropa
(1, 2), -- Pantalon -> Ropa
(2, 3), -- Zapatos -> Calzado
(3, 4), -- Gorra -> Accesorios
(3, 5), -- Bufanda -> Accesorios
(1, 6); -- Chaqueta -> Ropa

-- 7) Roles de usuario
INSERT INTO rol_usuario (id, role) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'MODERATOR');

-- 8) Usuarios (tabla users)
-- Nota: email y telefono son únicos según la entidad
INSERT INTO users (id, nombre, apellido, username, password, email, fecha_registro, telefono, rol_usuario_id_fk) VALUES
(1, 'Juan', 'Perez', 'juanp', 'password123', 'juan.perez@example.com', '2025-01-15 09:30:00', '3001112222', 2),
(2, 'Maria', 'Gomez', 'mariag', 'password123', 'maria.gomez@example.com', '2025-02-20 10:45:00', '3003334444', 1),
(3, 'Carlos', 'Lopez', 'carlosl', 'password123', 'carlos.lopez@example.com', '2025-03-03 12:00:00', '3005556666', 2),
(4, 'Ana', 'Martinez', 'anam', 'password123', 'ana.martinez@example.com', '2025-04-01 08:20:00', '3007778888', 3);

-- 9) Almacen-Producto (tabla almacen_producto)
-- Esta entidad tiene el campo id sin GeneratedValue, por eso asignamos id explícitos
INSERT INTO almacen_producto (id, stock, producto_id_fk, almacen_id_fk) VALUES
(1, 100, 1, 1),
(2, 50, 2, 1),
(3, 25, 3, 1),
(4, 200, 4, 2),
(5, 150, 5, 2),
(6, 40, 6, 1);

-- 10) Ventas
INSERT INTO venta (id, cliente_id_fk, fecha_venta) VALUES
(1, 1, '2025-05-10 15:10:00'),
(2, 3, '2025-05-11 16:20:00');

-- 11) Venta-Producto
INSERT INTO venta_producto (id, cantidad, producto_id_fk, venta_id_fk) VALUES
(1, 2, 1, 1), -- 2 Camisetas en venta 1
(2, 1, 3, 1), -- 1 Par de Zapatos en venta 1
(3, 3, 2, 2); -- 3 Pantalones en venta 2

-- FIN dataset.sql
